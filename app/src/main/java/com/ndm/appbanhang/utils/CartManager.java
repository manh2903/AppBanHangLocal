package com.ndm.appbanhang.utils;

import com.ndm.appbanhang.enitities.Product;
import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static List<Product> cart = new ArrayList<>();

    public static boolean addProductToCart(Product product) {
        for (Product p : cart) {
            if (p.getId() == product.getId()) {
                // Nếu trùng ID, trả về false để báo lỗi
                return false;
            }
        }
        cart.add(product);
        return true; // Thành công
    }

    public static List<Product> getCart() {
        return cart;
    }

    public static int Count() {
        int count = 0;
        for (Product p : cart) {
            count += p.getQuantity();
        }
        return count;
    }

    public static void clearCart() {
        cart.clear();
    }
}

