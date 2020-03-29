package com.gmail.yauhen2012.service.model;

import java.math.BigDecimal;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class AddItemDTO {

    @NotNull(message = "is required")
    @Size(min = 3, max = 40, message = "three characters or more")
    private String name;

    @NotNull(message = "is required")
    @Size(min = 3, max = 100, message = "three characters or more")
    private String description;

    @NotNull(message = "is required")
    @Positive(message = "price should be positive")
    private BigDecimal price;
    private List<AddShopDTO> addShopDTOS;

    public List<AddShopDTO> getAddShopDTOS() {
        return addShopDTOS;
    }

    public void setAddShopDTOS(List<AddShopDTO> addShopDTOS) {
        this.addShopDTOS = addShopDTOS;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
