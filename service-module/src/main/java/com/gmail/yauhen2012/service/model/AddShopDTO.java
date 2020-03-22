package com.gmail.yauhen2012.service.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddShopDTO {

    private Long shopId;
    @NotNull(message = "is required")
    @Size(min = 3, max = 40, message = "three characters or more")
    private String name;

    @NotNull(message = "is required")
    @Size(min = 3, max = 40, message = "three characters or more")
    private String location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

}
