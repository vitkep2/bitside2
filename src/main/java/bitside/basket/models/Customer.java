package bitside.basket.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serial;
import java.io.Serializable;

public class Customer implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("customerId")
    private String customerId;

    public String getCustomerId() {
        return customerId;
    }
}
