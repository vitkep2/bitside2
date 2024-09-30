package bitside.basket.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PriceServiceTest {

    private final PriceService priceService = new PriceService();

    @Test
    public void isProductAvailableShouldReturnTrue() {
        var product = "A0001";

        var result = priceService.isProductAvailable(product);

        assertTrue(result);
    }

    @Test
    public void isProductAvailableShouldReturnFalse() {
        var product = "AAA";

        var result = priceService.isProductAvailable(product);

        assertFalse(result);
    }

    @Test
    public void totalPriceWithDiscount() {
        var products = List.of("A0001","A0004");
        double expected = 14.98;

        var result = priceService.getTotalPrice(products);

        assertEquals(expected, result);
    }

    @Test
    public void totalPriceWith2for1Buy1() {
        var products = List.of("A0001","A0004", "A0002");
        double expected = 18.97;

        var result = priceService.getTotalPrice(products);

        assertEquals(expected, result);
    }

    @Test
    public void totalPriceWith2for1Buy2() {
        var products = List.of("A0001", "A0004", "A0002", "A0002");
        double expected = 18.97;

        var result = priceService.getTotalPrice(products);

        assertEquals(expected, result);
    }

    @Test
    public void totalPriceWith2for1Buy3() {
        var products = List.of("A0001", "A0004", "A0002", "A0002", "A0002");
        double expected = 22.96;

        var result = priceService.getTotalPrice(products);

        assertEquals(expected, result);
    }

    @Test
    public void totalPriceWith2for1Buy4() {
        var products = List.of("A0001", "A0004", "A0002", "A0002", "A0002", "A0002");
        double expected = 22.96;

        var result = priceService.getTotalPrice(products);

        assertEquals(expected, result);
    }

    @Test
    public void totalPriceWithTenPercentDiscount() {
        var products = List.of("A0001","A0003");
        double expected = 121.88;

        var result = priceService.getTotalPrice(products);

        assertEquals(expected, result);
    }
}
