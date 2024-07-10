import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OnlineBuyClothes {

    // Nested class for ClothingItem
    static class ClothingItem {
        private String name;
        private String category;
        private double price;
        private int quantity;

        public ClothingItem(String name, String category, double price, int quantity) {
            this.name = name;
            this.category = category;
            this.price = price;
            this.quantity = quantity;
        }

        public String getName() {
            return name;
        }

        public String getCategory() {
            return category;
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
            System.out.println("Clothing Item: " + name);
            System.out.println("Category: " + category);
            System.out.println("Price: $" + price);
            System.out.println("Quantity: " + quantity);
        }
    }

    // Main class containing clothing catalog and main methods
    private ArrayList<ClothingItem> catalog;
    private Map<String, Integer> cart;

    public OnlineBuyClothes() {
        catalog = new ArrayList<>();
        cart = new HashMap<>();
    }

    public void addClothingItem(String name, String category, double price, int quantity) {
        ClothingItem item = new ClothingItem(name, category, price, quantity);
        catalog.add(item);
        System.out.println("Clothing item added successfully!");
    }

    public void displayCatalog() {
        System.out.println("\nCatalog:");
        for (ClothingItem item : catalog) {
            item.displayDetails();
            System.out.println();
        }
    }

    public void addToCart(String name, int quantity) {
        boolean found = false;
        for (ClothingItem item : catalog) {
            if (item.getName().equalsIgnoreCase(name)) {
                if (item.getQuantity() >= quantity) {
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
            System.out.println("Clothing item \"" + name + "\" not found in the catalog.");
        }
    }

    public void viewCart() {
        System.out.println("\nShopping Cart:");
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            String name = entry.getKey();
            int quantity = entry.getValue();
            System.out.println("Clothing Item: " + name + ", Quantity: " + quantity);
        }
    }

    public void calculateTotalBill() {
        double totalBill = 0;
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            String name = entry.getKey();
            int quantity = entry.getValue();
            for (ClothingItem item : catalog) {
                if (item.getName().equalsIgnoreCase(name)) {
                    totalBill += item.getPrice() * quantity;
                    break;
                }
            }
        }
        System.out.println("Total Bill: $" + totalBill);
    }

    public static void main(String[] args) {
        OnlineBuyClothes store = new OnlineBuyClothes();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Clothing Item to Catalog");
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
                    System.out.print("Enter clothing item name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    store.addClothingItem(name, category, price, quantity);
                    break;
                case 2:
                    store.displayCatalog();
                    break;
                case 3:
                    System.out.print("Enter clothing item name to add to cart: ");
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
