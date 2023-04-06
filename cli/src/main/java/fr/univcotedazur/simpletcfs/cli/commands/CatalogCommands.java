package fr.univcotedazur.simpletcfs.cli.commands;

import fr.univcotedazur.simpletcfs.cli.CliContext;
import fr.univcotedazur.simpletcfs.cli.model.CliAdvantage;
import fr.univcotedazur.simpletcfs.cli.model.CliDiscount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Map;

@ShellComponent
public class CatalogCommands {
    public static final String BASE_URI = "/catalog";
    public static final String DISCOUNTS_URI = "/discounts";
    public static final String STORE_URI = "/store";
    public static final String ADVANTAGE_URI = "/advantage";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private CliContext cliContext;

    @ShellMethod("List all the discounts")
    public String discounts() {
        StringBuilder discounts = new StringBuilder("List of discounts:\n");
        for (Map.Entry<Long, CliDiscount> entry : cliContext.getDiscounts().entrySet()) {
            discounts.append(entry.getKey()).append(" : ").append(entry.getValue()).append("\n");
        }
        return discounts.toString();
    }

    @ShellMethod("Create a new Discount in the backend (create-discount NAME STORE_ID POINT_PRICE)")
    public CliDiscount createDiscount(String name, Long storeId, int pointPrice) {
        CliDiscount res = restTemplate.postForObject(BASE_URI + DISCOUNTS_URI, new CliDiscount(name, storeId, pointPrice), CliDiscount.class);
        cliContext.getDiscounts().put(res.getId(), res);
        return res;
    }

    @ShellMethod("Get a discount from the catalog of the backend with its id (get-discount-by-id DISCOUNT_ID)")
    public CliDiscount getDiscountById(Long discountId) {
        CliDiscount res = restTemplate.getForObject(BASE_URI+DISCOUNTS_URI+"/" + discountId, CliDiscount.class);
        cliContext.getDiscounts().put(res.getId(), res);
        return res;
    }

    @ShellMethod("Get all the discounts from the catalog of the backend witch match with one store (get-discounts-by-store STORE_ID)")
    public String getDiscountsByStore(Long storeId) {
        CliDiscount[] res = restTemplate.getForObject(BASE_URI + DISCOUNTS_URI + STORE_URI + "/" + storeId, CliDiscount[].class);
        Arrays.stream(res).forEach(discount -> cliContext.getDiscounts().put(discount.getId(), discount));
        return Arrays.toString(res);
    }

    @ShellMethod("Update an existing discount in the backend (update-discount-point-price DISCOUNT_ID POINT_PRICE)")
    public CliDiscount updateDiscountPointPrice(Long discountId, int pointPrice) {
        HttpEntity<Integer> entity = new HttpEntity<Integer>(pointPrice);
        CliDiscount res = restTemplate.exchange(BASE_URI + DISCOUNTS_URI + "/" + discountId, HttpMethod.PUT, entity, CliDiscount.class).getBody();
        cliContext.getDiscounts().put(res.getId(), res);
        return res;
    }

    @ShellMethod("Delete a discount in the backend (delete-discount DISCOUNT_ID)")
    public String deleteDiscount(Long discountId) {
        CliDiscount discount = getDiscountById(discountId);
        HttpEntity<CliDiscount> entity = new HttpEntity<CliDiscount>(discount);
        String res = restTemplate.exchange(BASE_URI + DISCOUNTS_URI + "/" + discountId, HttpMethod.DELETE, entity, String.class).getBody();
        cliContext.getDiscounts().remove(discount.getId());
        return res;
    }

    @ShellMethod("Create an advantage (create-advantage NAME)")
    public CliAdvantage createAdvantage(String name) {
        System.out.println("Name before asking backend: " + name);
        CliAdvantage result = restTemplate.postForObject(BASE_URI + ADVANTAGE_URI, name, CliAdvantage.class);
        System.out.println("Name after asking backend: " + result.toString());
        cliContext.getAdvantages().put(result.getId(), result);
        return result;
    }

    @ShellMethod("Delete an advantage in the backend (delete-advantage ADVANTAGE_ID)")
    public String deleteAdvantage(Long advantageId) {
        CliAdvantage advantage = getAdvantageById(advantageId);
        HttpEntity<CliAdvantage> entity = new HttpEntity<CliAdvantage>(advantage);
            String result = restTemplate.exchange(BASE_URI + ADVANTAGE_URI + "/" + advantageId, HttpMethod.DELETE, entity, String.class).getBody();
        cliContext.getAdvantages().remove(advantage.getId());
        return result;
    }

    @ShellMethod("Get an advantage from the catalog of the backend with its id (get-advantage-by-id ADVANTAGE_ID)")
    public CliAdvantage getAdvantageById(Long advantageId) {
        CliAdvantage result = restTemplate.getForObject(BASE_URI + ADVANTAGE_URI + "/" + advantageId, CliAdvantage.class);
        cliContext.getAdvantages().put(result.getId(), result);
        return result;
    }


}
