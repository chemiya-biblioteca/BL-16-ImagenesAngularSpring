package com.uva.users.modelo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer productId;

    private String productName;
    private String productDescription;
    private Double productDiscountedPrice;
    private Double productActualPrice;

    public Integer getProductId() {
        return this.productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return this.productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Double getProductDiscountedPrice() {
        return this.productDiscountedPrice;
    }

    public void setProductDiscountedPrice(Double productDiscountedPrice) {
        this.productDiscountedPrice = productDiscountedPrice;
    }

    public Double getProductActualPrice() {
        return this.productActualPrice;
    }

    public void setProductActualPrice(Double productActualPrice) {
        this.productActualPrice = productActualPrice;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "product_images",
    joinColumns = {
    @JoinColumn(name = "product_id")
    },
    inverseJoinColumns = {
    @JoinColumn(name = "image_id")
    })
    private Set<ImageModel> productImages;

    public Set<ImageModel> getProductImages() {
        return this.productImages;
    }

    public void setProductImages(Set<ImageModel> productImages) {
        this.productImages = productImages;
    }

    public Product(){

    }

    public Product(Integer id, String name, String description, double productActualPrice, double productDiscountedPrice){
        this.productId=id;
        this.productName=name;
        this.productDescription=description;
        this.productActualPrice=productActualPrice;
        this.productDiscountedPrice=productDiscountedPrice;
    }

}

