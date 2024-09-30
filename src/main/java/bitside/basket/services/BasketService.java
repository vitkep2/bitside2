package bitside.basket.services;

import bitside.basket.models.Basket;
import bitside.basket.models.Item;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// Skip data layer implementation
@Component
public class BasketService {
    private final List<Basket> baskets;

    public BasketService(){
        baskets = new ArrayList<>();
    }

    public Basket getBasketByCustomerId(String customerId){
        return baskets.stream().filter(basket -> customerId.equals(basket.getCustomerId()))
                .findAny()
                .orElse(null);
    }

    public Basket getBasketById(String bucketId){
        return baskets.stream().filter(basket -> bucketId.equals(basket.getBasketId()))
                .findAny()
                .orElse(null);
    }

    public List<Basket> getAllBaskets(){
        return baskets;
    }

    public Basket createBasket(String customerId) {
        var newBasket = new Basket(customerId, new ArrayList<>());
        this.baskets.add(newBasket);
        return newBasket;
    }

    public Basket updateBasket(Item item) {
        var foundBasket = this.getBasketById(item.getBasketId());
        if(foundBasket != null) {
            var productsInBasket = foundBasket.getProductIds();
                productsInBasket.add(item.getItemId());
        }
        return foundBasket;
    }
}
