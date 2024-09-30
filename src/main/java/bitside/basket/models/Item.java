package bitside.basket.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serial;
import java.io.Serializable;

public class Item implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("basketId")
    private String basketId;
    @JsonProperty("itemId")
    private String itemId;

    public String getBasketId() {
        return this.basketId;
    }

    public String getItemId() {
        return this.itemId;
    }
}
