package bitside.basket.controllers;

import bitside.basket.models.Basket;
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
@RequestMapping("/api/price-service/v1")
public class PriceApiController {
    private final BasketService basketService;
    private final PriceService priceService;
    @Autowired
    public PriceApiController(BasketService basketService, PriceService priceService) {
        this.basketService = basketService;
        this.priceService = priceService;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/totalPrice",
            produces = { "application/json" },
            consumes = { "application/json" }
    )
    public ResponseEntity<Double> getTotalPrice(@RequestBody Basket basket) {
        if (basket == null ||
                basket.getCustomerId() == null ||
                basket.getCustomerId().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        var foundBasket = basketService.getBasketById(basket.getBasketId());
        if (foundBasket == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return ResponseEntity.ok().body(priceService.getTotalPrice(foundBasket.getProductIds()));
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

        var updatedBasket = basketService.updateBasket(item);
        if (updatedBasket == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.ok().body(basketService.updateBasket(item));
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/baskets",
            produces = { "application/json" },
            consumes = { "application/json" }
    )
    public ResponseEntity<List<Basket>> addItemToBasket() {
        return ResponseEntity.ok().body(basketService.getAllBaskets());
    }
}
