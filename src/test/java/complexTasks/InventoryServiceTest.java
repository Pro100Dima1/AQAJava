package complexTasks;

import org.expressJava.task12.inventory.InventoryService;
import org.expressJava.task12.inventory.OutOfStockException;
import org.expressJava.task12.inventory.Product;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InventoryServiceTest {
    @Test
    void testAddAndRetrieveProduct() throws OutOfStockException {
        InventoryService service = new InventoryService();
        Product phone = new Product("Phone", 799.99, "Electronics");
        service.addProduct(phone);
        Product retrieved = service.getProductByCategory("Electronics");
        assertEquals("Phone", retrieved.getName());
    }

    @Test
    void testAddProductWhenInventoryClosed() {
        InventoryService service = new InventoryService();
        service.setInventoryOpen(false);
        service.addProduct(new Product("TV", 1000, "Electronics"));
        assertThrows(OutOfStockException.class, () -> service.getProductByCategory("Electronics"));
    }

    @Test
    void testOutOfStock() {
        InventoryService service = new InventoryService();
        assertThrows(OutOfStockException.class, () -> service.getProductByCategory("Books"));
    }

    @Test
    void testFilterByPrice() {
        InventoryService service = new InventoryService();
        service.addProduct(new Product("Phone", 800, "Electronics"));
        service.addProduct(new Product("Book", 20, "Books"));
        List<Product> filtered = service.filterByPrice(100);
        assertEquals(1, filtered.size());
        assertEquals("Phone", filtered.get(0).getName());
    }
}
