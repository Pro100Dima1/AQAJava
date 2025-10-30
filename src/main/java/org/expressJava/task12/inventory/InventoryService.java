package org.expressJava.task12.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InventoryService {
    private final Map<String, List<Product>> inventory = new ConcurrentHashMap<>();
    private boolean isInventoryOpen = true;

    public void setInventoryOpen(boolean open) {
        this.isInventoryOpen = open;
    }

    public synchronized void addProduct(Product product) {
        if (!isInventoryOpen) return;
        inventory.computeIfAbsent(product.getCategory(), k -> new ArrayList<>()).add(product);
    }

    public synchronized Product getProductByCategory(String category) throws OutOfStockException {
        List<Product> products = inventory.get(category);
        if (products == null || products.isEmpty()) {
            throw new OutOfStockException("Товары в категории \"" + category + "\" отсутствуют");
        }
        return products.remove(0);
    }

    public List<Product> filterByPrice(double minPrice) {
        return inventory.values().stream().flatMap(List::stream).filter(p -> p.getPrice() >= minPrice).collect(Collectors.toList());
    }

    public List<Product> getAllByCategory(String category) {
        return new ArrayList<>(inventory.getOrDefault(category, List.of()));
    }
}
