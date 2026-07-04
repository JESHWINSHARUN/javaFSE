import java.util.HashMap;
import java.util.Map;

class Product {
    int productId;
    String productName;
    int quantity;
    double price;

    public Product(int productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public void display() {
        System.out.println("Product ID : " + productId);
        System.out.println("Product Name : " + productName);
        System.out.println("Quantity : " + quantity);
        System.out.println("Price : " + price);
        System.out.println("---------------------------");
    }
}

class Inventory {
    HashMap<Integer, Product> products = new HashMap<>();

    // Add Product
    public void addProduct(Product product) {
        products.put(product.productId, product);
        System.out.println("Product Added Successfully.");
    }

    // Update Product
    public void updateProduct(int id, String name, int quantity, double price) {
        if (products.containsKey(id)) {
            Product p = products.get(id);
            p.productName = name;
            p.quantity = quantity;
            p.price = price;
            System.out.println("Product Updated Successfully.");
        } else {
            System.out.println("Product Not Found.");
        }
    }

    // Delete Product
    public void deleteProduct(int id) {
        if (products.remove(id) != null) {
            System.out.println("Product Deleted Successfully.");
        } else {
            System.out.println("Product Not Found.");
        }
    }

    // Display Inventory
    public void displayInventory() {
        if (products.isEmpty()) {
            System.out.println("Inventory is Empty.");
            return;
        }

        System.out.println("\nInventory Details:");
        for (Map.Entry<Integer, Product> entry : products.entrySet()) {
            entry.getValue().display();
        }
    }
}

public class InventoryManagementSystem {
    public static void main(String[] args) {

        Inventory inventory = new Inventory();

        inventory.addProduct(new Product(101, "Laptop", 10, 65000));
        inventory.addProduct(new Product(102, "Mouse", 50, 500));
        inventory.addProduct(new Product(103, "Keyboard", 25, 1200));

        inventory.displayInventory();

        inventory.updateProduct(102, "Wireless Mouse", 40, 800);

        inventory.deleteProduct(103);

        inventory.displayInventory();
    }
}