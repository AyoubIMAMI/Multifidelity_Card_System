package fr.univcotedazur.simpletcfs.cli.commands;

import fr.univcotedazur.simpletcfs.cli.CliContext;
import fr.univcotedazur.simpletcfs.cli.model.DiscountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Arrays;

@ShellComponent
public class CatalogueCommands {
    public static final String BASE_URI = "/catalog";
    public static final String DISCOUNTS_URI = "/discounts";


    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private CliContext cliContext;

    @ShellMethod("Create a new Discount in the CoD backend (create NAME STOREID CASHPRICE POINTPRICE)")
    public DiscountDTO create(@RequestBody @Valid String name, Long storeId, double cashPrice, int pointPrice) {
        DiscountDTO res = restTemplate.postForObject(BASE_URI + DISCOUNTS_URI, new DiscountDTO(name, storeId, cashPrice, pointPrice), DiscountDTO.class);
        cliContext.getDiscounts().put(res.getName(), res);
        return res;
    }

    @ShellMethod("Get all the discounts from the catalogue of the CoD backend (discounts)")
    public DiscountDTO[] discounts() {
        DiscountDTO[] res = restTemplate.getForObject(BASE_URI+DISCOUNTS_URI, DiscountDTO[].class);
        Arrays.stream(res).forEach(discount -> cliContext.getDiscounts().put(discount.getName(), discount));
        Arrays.stream(res).forEach(discount -> System.out.println(res));
        return res;
    }

    @ShellMethod("Get a discount from the catalogue of the CoD backend with it id (getDiscountById DISCOUNTID)")
    public DiscountDTO getDiscountById(Long discountId) {
        DiscountDTO res = restTemplate.getForObject(BASE_URI+DISCOUNTS_URI+"/"+discountId, DiscountDTO.class);
        cliContext.getDiscounts().put(res.getName(), res);
        return res;
    }

    @ShellMethod("Get all the discounts from the catalogue of the CoD backend witch match with one store (getDiscountByStore STOREID)")
    public DiscountDTO[] getDiscountByStore(Long storeId) {
        DiscountDTO[] res = restTemplate.getForObject(BASE_URI+DISCOUNTS_URI+"/"+storeId, DiscountDTO[].class);
        Arrays.stream(res).forEach(discount -> cliContext.getDiscounts().put(discount.getName(), discount));
        return res;
    }

    @ShellMethod("Update an existing discount in the CoD backend (updatePointPrice DISCOUNTID POINTPRICE)")
    public DiscountDTO updatePointPrice(@RequestBody @Valid Long discountId, int pointPrice) {
        DiscountDTO discount = getDiscountById(discountId);
        discount.setPointPrice(pointPrice);
        HttpEntity<DiscountDTO> entity = new HttpEntity<DiscountDTO>(discount);
        DiscountDTO res = restTemplate.exchange(BASE_URI + DISCOUNTS_URI+"/"+discountId, HttpMethod.PUT, entity, DiscountDTO.class).getBody();
        cliContext.getDiscounts().put(res.getName(), res);
        return res;
    }

    @ShellMethod("Delete a discount in the CoD backend (delete DISCOUNTID)")
    public DiscountDTO delete(@RequestBody @Valid Long discountId) {
        HttpEntity<DiscountDTO> entity = new HttpEntity<DiscountDTO>(getDiscountById(discountId));
        DiscountDTO res = restTemplate.exchange(BASE_URI + DISCOUNTS_URI+"/"+discountId, HttpMethod.DELETE, entity, DiscountDTO.class).getBody();
        cliContext.getDiscounts().put(res.getName(), res);
        return res;
    }
}
