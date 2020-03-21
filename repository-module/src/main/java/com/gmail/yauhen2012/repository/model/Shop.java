package com.gmail.yauhen2012.repository.model;

import java.util.Objects;

public class Shop {

    private Long shopId;
    private String name;
    private String location;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Shop shop = (Shop) o;
        return Objects.equals(shopId, shop.shopId) &&
                Objects.equals(name, shop.name) &&
                Objects.equals(location, shop.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shopId, name, location);
    }

}
