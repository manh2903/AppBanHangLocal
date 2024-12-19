package com.ndm.appbanhang.enitities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable {
    private int userId;
    private List<CartItem> items;
    private double totalAmount;

    public Cart(int userId) {
        this.userId = userId;
        this.items = new ArrayList<>();
        this.totalAmount = 0.0;
    }


    public void addProduct(Product product, int quantity) {
        boolean exists = false;
        for (CartItem item : items) {
            if (item.getProduct().getId() == product.getId()) {
                item.setQuantity(item.getQuantity() + quantity);
                exists = true;
                break;
            }
        }
        if (!exists) {
            CartItem newItem = new CartItem(product, quantity);
            items.add(newItem);
        }
        updateTotalAmount();
    }


    public void removeProduct(int productId) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getProduct().getId() == productId) {
                items.remove(i);
                break;
            }
        }
        updateTotalAmount();
    }

    private void updateTotalAmount() {
        totalAmount = 0.0;
        for (CartItem item : items) {
            totalAmount += item.getTotalPrice();
        }
    }


    public double getTotalAmount() {
        return totalAmount;
    }


    public List<CartItem> getItems() {
        return items;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Giỏ hàng của người mua ID: ").append(userId).append("\n");
        for (CartItem item : items) {
            sb.append(item.toString()).append("\n");
        }
        sb.append("Tổng tiền: ").append(totalAmount);
        return sb.toString();
    }
}
