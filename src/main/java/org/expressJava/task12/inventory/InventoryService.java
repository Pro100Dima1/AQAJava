package org.expressJava.task12.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryService {
    boolean isInventoryOpem = false;
    public Map<String, List<Product>> products = new HashMap<>();

    public void addProduct(Product product) {
        if (!isInventoryOpem) {
            return;
        }
        else {
            products.putIfAbsent(product.getCategory(), new ArrayList<>());
        }
    }

    public Product getProductByCategory(){

    }
}
