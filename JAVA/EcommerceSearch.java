import java.util.Arrays;
import java.util.Comparator;

class Product {
    int productId;
    String productName;
    String category;

    public Product(int productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }

    public void display() {
        System.out.println(productId + " " + productName + " " + category);
    }
}

public class EcommerceSearch {

    // Linear Search
    public static Product linearSearch(Product[] products, int key) {
        for (Product product : products) {
            if (product.productId == key) {
                return product;
            }
        }
        return null;
    }

    // Binary Search
    public static Product binarySearch(Product[] products, int key) {
        int low = 0;
        int high = products.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (products[mid].productId == key)
                return products[mid];
            else if (products[mid].productId < key)
                low = mid + 1;
            else
                high = mid - 1;
        }

        return null;
    }

    public static void main(String[] args) {

        Product[] products = {
                new Product(105, "Laptop", "Electronics"),
                new Product(101, "Mouse", "Electronics"),
                new Product(103, "Keyboard", "Electronics"),
                new Product(102, "Shoes", "Fashion"),
                new Product(104, "Watch", "Accessories")
        };

        // Linear Search
        System.out.println("Linear Search:");
        Product result1 = linearSearch(products, 103);

        if (result1 != null)
            result1.display();
        else
            System.out.println("Product Not Found");

        // Sort array for Binary Search
        Arrays.sort(products, Comparator.comparingInt(p -> p.productId));

        System.out.println("\nBinary Search:");
        Product result2 = binarySearch(products, 103);

        if (result2 != null)
            result2.display();
        else
            System.out.println("Product Not Found");
    }
}