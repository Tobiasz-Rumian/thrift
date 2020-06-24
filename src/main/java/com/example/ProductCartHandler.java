package com.example;

import java.util.HashMap;
import java.util.Map;

public class ProductCartHandler implements ProductCart.Iface {
    Map<Integer, OrderItem> basket = new HashMap<>();

    @Override
    public void addItem(OrderItem item) {
        basket.put(item.id, item);
    }

    @Override
    public void removeItem(int id) {
        basket.remove(id);
    }

    @Override
    public void updateItem(OrderItem item) {
        basket.replace(item.id, item);
    }

    @Override
    public void placeOrder() throws NotEnoughProductsException {
        for (Map.Entry<Integer, OrderItem> entry : basket.entrySet()) {
            if (ProductManagerHandler.products.get(entry.getKey()).amountInStoreage < entry.getValue().amount) {
                throw new NotEnoughProductsException();
            }
        }
        for (Map.Entry<Integer, OrderItem> entry : basket.entrySet()) {
            ProductManagerHandler.products.get(entry.getKey()).amountInStoreage -= entry.getValue().amount;
        }
    }
}
