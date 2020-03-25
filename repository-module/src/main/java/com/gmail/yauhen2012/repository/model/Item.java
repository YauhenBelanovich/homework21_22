package com.gmail.yauhen2012.repository.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String name;
    @Column
    private String description;

    @OneToOne(fetch = FetchType.LAZY,
            mappedBy = "item",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private ItemDetails itemDetails;

    @ManyToMany(fetch = FetchType.LAZY, cascade =
            {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "item_shop",
            joinColumns = {
                    @JoinColumn(name = "item_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "shop_id")
            })
    private List<Shop> shops = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public ItemDetails getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(ItemDetails itemDetails) {
        this.itemDetails = itemDetails;
    }

    public List<Shop> getShops() {
        return shops;
    }

    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return Objects.equals(id, item.id) &&
                Objects.equals(name, item.name) &&
                Objects.equals(description, item.description) &&
                Objects.equals(itemDetails, item.itemDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, itemDetails);
    }

}
