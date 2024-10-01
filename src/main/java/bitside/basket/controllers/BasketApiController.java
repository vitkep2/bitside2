package bitside.basket.controllers;

import bitside.basket.models.Basket;
import bitside.basket.models.Customer;
import bitside.basket.models.Item;
import bitside.basket.services.BasketService;
import bitside.basket.services.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/basket-service/v1")
public class BasketApiController {
    private final BasketService basketService;
    private final PriceService priceService;
    @Autowired
    public BasketApiController(BasketService basketService, PriceService priceService) {
        this.basketService = basketService;
        this.priceService = priceService;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/basket",
            produces = { "application/json" },
            consumes = { "application/json" }
    )
    public ResponseEntity<Basket> createBasket(@RequestBody Customer customer) {
        if (customer == null ||
                customer.getCustomerId() == null ||
                customer.getCustomerId().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        var basket = basketService.getBasketByCustomerId(customer.getCustomerId());
        if (basket != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return ResponseEntity.ok().body(basketService.createBasket(customer.getCustomerId()));
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/item",
            produces = { "application/json" },
            consumes = { "application/json" }
    )
    public ResponseEntity<Basket> addItemToBasket(@RequestBody Item item) {
        if (item == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        if(!priceService.isProductAvailable(item.getItemId())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        var updatedBasket = basketService.updateBasket(item);
        if (updatedBasket == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.ok().body(basketService.updateBasket(item));
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/baskets",
            produces = { "application/json" }
    )
    public ResponseEntity<List<Basket>> getAllBaskets() {
        return ResponseEntity.ok().body(basketService.getAllBaskets());
    }
}
