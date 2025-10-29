package org.expressJava.task12.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InventoryService {
    boolean isInventoryOpen = false;
    public Map<String, List<Product>> products = new HashMap<>();

    public void addProduct(Product product) {
        if (!isInventoryOpen) {
            return;
        } else {
            products.computeIfAbsent(product.getCategory(), k -> new ArrayList<>())
                    .add(product);
        }
    }

    public synchronized Product getProductByCategory(String category) throws OutOfStockException {
        List<Product> product = products.get(category);
        if (products == null || products.isEmpty()) {
            throw new OutOfStockException("Товары в категории \"" + category + "\" отсутствуют");
        }
        return product.remove(0);
    }

    public List<Product> filterByPrice(double minPrice) {
        return products.values().stream().
                flatMap(List::stream).filter(p -> p.getPrice() >= minPrice)
                .collect(Collectors.toList());
    }

    public List<Product> getAllByCategory(String category) {
        return new ArrayList<>(products.getOrDefault(category, List.of()));
    }

    public void setInventoryOpen(boolean open) {
        this.isInventoryOpen = open;
    }
}
