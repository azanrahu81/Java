import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OnlineShopping {

    // Nested class for Product
    static class Product {
        private String name;
        private double price;
        private int quantity;

        public Product(String name, double price, int quantity) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public void displayDetails() {
            System.out.println("Product: " + name);
            System.out.println("Price: $" + price);
            System.out.println("Quantity: " + quantity);
        }
    }

    // Main class containing product catalog and main methods
    private ArrayList<Product> catalog;
    private Map<String, Integer> cart;

    public OnlineShopping() {
        catalog = new ArrayList<>();
        cart = new HashMap<>();
    }

    public void addProduct(String name, double price, int quantity) {
        Product product = new Product(name, price, quantity);
        catalog.add(product);
        System.out.println("Product added successfully!");
    }

    public void displayCatalog() {
        System.out.println("\nCatalog:");
        for (Product product : catalog) {
            product.displayDetails();
            System.out.println();
        }
    }

    public void addToCart(String name, int quantity) {
        boolean found = false;
        for (Product product : catalog) {
            if (product.getName().equalsIgnoreCase(name)) {
                if (product.getQuantity() >= quantity) {
                    cart.put(name, quantity);
                    found = true;
                    System.out.println("Added to cart successfully!");
                } else {
                    System.out.println("Insufficient stock for " + name + ".");
                }
                break;
            }
        }
        if (!found) {
            System.out.println("Product \"" + name + "\" not found in the catalog.");
        }
    }

    public void viewCart() {
        System.out.println("\nShopping Cart:");
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            String name = entry.getKey();
            int quantity = entry.getValue();
            System.out.println("Product: " + name + ", Quantity: " + quantity);
        }
    }

    public void calculateTotalBill() {
        double totalBill = 0;
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            String name = entry.getKey();
            int quantity = entry.getValue();
            for (Product product : catalog) {
                if (product.getName().equalsIgnoreCase(name)) {
                    totalBill += product.getPrice() * quantity;
                    break;
                }
            }
        }
        System.out.println("Total Bill: $" + totalBill);
    }

    public static void main(String[] args) {
        OnlineShopping store = new OnlineShopping();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Product to Catalog");
            System.out.println("2. Display Catalog");
            System.out.println("3. Add to Cart");
            System.out.println("4. View Cart");
            System.out.println("5. Calculate Total Bill");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter product name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    store.addProduct(name, price, quantity);
                    break;
                case 2:
                    store.displayCatalog();
                    break;
                case 3:
                    System.out.print("Enter product name to add to cart: ");
                    name = scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    quantity = scanner.nextInt();
                    store.addToCart(name, quantity);
                    break;
                case 4:
                    store.viewCart();
                    break;
                case 5:
                    store.calculateTotalBill();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
