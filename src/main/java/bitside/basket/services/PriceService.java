package bitside.basket.services;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Component
public class PriceService {
    private final Map<String, Double> inventory;
    private final List<String> buy1Get1FreeDiscount;
    private final List<String> tenPercentDiscount;

    public PriceService() {
        // Keep initialization in constructor due to simplicity
        this.inventory = new HashMap<>();
        inventory.put("A0001", 12.99);
        inventory.put("A0002", 3.99);
        inventory.put("A0003", 120.99);
        inventory.put("A0004", 1.99);

        this.buy1Get1FreeDiscount = new ArrayList<>();
        buy1Get1FreeDiscount.add("A0002");

        tenPercentDiscount = new ArrayList<>();
        tenPercentDiscount.add("A0003");
    }

    public Double getTotalPrice(List<String> products) {
        Double total = 0.0;
        // Remove duplicate products with discount
        var productsWithBuy1Get1Discount = getBuy1Get1FreeDiscount(products);

        // Calculation total price with 10% discount
        for (String product : productsWithBuy1Get1Discount) {
            var basePrice = inventory.get(product);
            var tenPercentDiscountPrice = getTenPercentDiscount(product, basePrice);
            total += tenPercentDiscountPrice;
        }
        return (double) Math.round(total * 100) / 100;
    }

    public boolean isProductAvailable(String product) {
        return inventory.containsKey(product);
    }

    private Double getTenPercentDiscount(String product, Double basePrice) {
        if(tenPercentDiscount.contains(product)) {
            return basePrice - basePrice*0.1;
        }

        return basePrice;
    }

    private List<String> getBuy1Get1FreeDiscount(List<String> products) {
        var duplicates = new ArrayList<String>();
        var newProducts = new ArrayList<String>();

        for (String product : products) {
            if(buy1Get1FreeDiscount.contains(product)){
                if(!duplicates.contains(product)) {
                    duplicates.add(product);
                } else {
                    duplicates.remove(product);
                    newProducts.add(product);
                }
            } else {
                newProducts.add(product);
            }
        }

        return newProducts;
    }
}
