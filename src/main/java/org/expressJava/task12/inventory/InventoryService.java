package org.expressJava.task12.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InventoryService {
    private Map<String, List<Product>> inventory = new HashMap<>();
    boolean isInventoryOpen = true;

    public void setInventoryOpen(boolean inventoryOpen) {
        isInventoryOpen = inventoryOpen;
    }

    public void addProduct(Product product) {
        if (!isInventoryOpen) {
        }inventory.computeIfAbsent(product.getName(), k -> new ArrayList<>()).add(product);
    }

    public Product getProductByCategory(String category) throws OutOfStockException {
        List<Product> products = inventory.get(category);
        if (products == null || products.isEmpty()) {
            throw new OutOfStockException("Product with category " + category + " not found");
        }
        return products.remove(0);
    }

    public List<Product> getProductsByCategory(String category) {
        return new ArrayList<>(inventory.getOrDefault(category, List.of()));
    }

    public List<Product> filterProductsByCategory(double price) {
        return inventory.values().stream()
                .flatMap(List::stream)
                .filter(p -> p.getPrice() >= price)
                .collect(Collectors.toList());
    }
}
