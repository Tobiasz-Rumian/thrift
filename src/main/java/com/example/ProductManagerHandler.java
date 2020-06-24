package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductManagerHandler implements ProductManager.Iface {
    static Map<Integer, Product> products = new HashMap<>(Map.of(
            1, new Product(1, "Apple", 1.68, 2000),
            2, new Product(2, "Banana", 3.11, 12345)));

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }
}
