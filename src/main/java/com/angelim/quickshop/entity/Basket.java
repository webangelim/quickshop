package com.angelim.quickshop.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Document(collection = "basket")
public class Basket {

    @Id
    private String id;

    private Long client;

    private BigDecimal totalPrice;

    private List<Product> products;

    public String getId() {
        return id;
    }

    public Long getClient() {
        return client;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Status getStatus() {
        return status;
    }

    private Status status;

    public void setId(String id) {
        this.id = id;
    }

    public void setClient(Long client) {
        this.client = client;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Basket(Long client, List<Product> products, Status status) {
        this.client = client;
        this.products = products;
        this.status = status;
    }

    public Basket(String id, Long client, BigDecimal totalPrice, List<Product> products, Status status) {
        this.id = id;
        this.client = client;
        this.totalPrice = totalPrice;
        this.products = products;
        this.status = status;
    }

    public Basket() {
    }

    public void calculateTotalPrice() {
        this.totalPrice = products.stream()
                .map(product -> product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


}
