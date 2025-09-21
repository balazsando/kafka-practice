package com.balazsando.model;

public class Order {
    private String userId;
    Integer quantity;
    Float totalPrice;

    public Order(String userId, Integer quantity, Float totalPrice) {
        this.userId = userId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
