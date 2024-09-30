package bitside.basket.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class Basket implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("basketId")
    private final String basketId;
    @JsonProperty("customerId")
    private String customerId;
    @JsonProperty("productIds")
    private List<String> productIds;

    public Basket(String customerId, List<String> productIds) {
        this.customerId = customerId;
        this.productIds = productIds;
        this.basketId = UUID.randomUUID().toString();
    }

    public String getBasketId() {
        return this.basketId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<String> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<String> productIds) {
        this.productIds = productIds;
    }
}
