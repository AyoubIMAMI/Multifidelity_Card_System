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
public class CatalogCommands {
    public static final String BASE_URI = "/catalog";
    public static final String DISCOUNTS_URI = "/discounts";
    public static final String STORE_URI = "/store";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private CliContext cliContext;

    @ShellMethod("Create a new Discount in the backend (create-discount NAME STOREID CASHPRICE POINTPRICE)")
    public DiscountDTO createDiscount(String name, Long storeId, double cashPrice, int pointPrice) {
        DiscountDTO res = restTemplate.postForObject(BASE_URI + DISCOUNTS_URI, new DiscountDTO(name, storeId, cashPrice, pointPrice), DiscountDTO.class);
        cliContext.getDiscounts().put(res.getId(), res);
        return res;
    }

    @ShellMethod("Get all the discounts from the catalog of the backend")
    public String discounts() {
        DiscountDTO[] res = restTemplate.getForObject(BASE_URI+DISCOUNTS_URI, DiscountDTO[].class);
        Arrays.stream(res).forEach(discount -> cliContext.getDiscounts().put(discount.getId(), discount));
        return cliContext.getDiscounts().toString();
    }

    @ShellMethod("Get a discount from the catalog of the backend with its id (get-discount-by-id DISCOUNTID)")
    public DiscountDTO getDiscountById(Long discountId) {
        DiscountDTO res = restTemplate.getForObject(BASE_URI+DISCOUNTS_URI+"/" + discountId, DiscountDTO.class);
        cliContext.getDiscounts().put(res.getId(), res);
        return res;
    }

    @ShellMethod("Get all the discounts from the catalog of the backend witch match with one store (get-discounts-by-store STOREID)")
    public String getDiscountsByStore(Long storeId) {
        DiscountDTO[] res = restTemplate.getForObject(BASE_URI + DISCOUNTS_URI + STORE_URI + "/" + storeId, DiscountDTO[].class);
        Arrays.stream(res).forEach(discount -> cliContext.getDiscounts().put(discount.getId(), discount));
        return Arrays.toString(res);
    }

    @ShellMethod("Update an existing discount in the backend (update-discount-point-price DISCOUNTID POINTPRICE)")
    public DiscountDTO updateDiscountPointPrice(Long discountId, int pointPrice) {
        HttpEntity<Integer> entity = new HttpEntity<Integer>(pointPrice);
        DiscountDTO res = restTemplate.exchange(BASE_URI + DISCOUNTS_URI + "/" + discountId, HttpMethod.PUT, entity, DiscountDTO.class).getBody();
        cliContext.getDiscounts().put(res.getId(), res);
        return res;
    }

    @ShellMethod("Delete a discount in the backend (delete-discount DISCOUNTID)")
    public String deleteDiscount(Long discountId) {
        DiscountDTO discount = getDiscountById(discountId);
        HttpEntity<DiscountDTO> entity = new HttpEntity<DiscountDTO>(discount);
        String res = restTemplate.exchange(BASE_URI + DISCOUNTS_URI + "/" + discountId, HttpMethod.DELETE, entity, String.class).getBody();
        cliContext.getDiscounts().remove(discount.getId());
        return res;
    }
}
