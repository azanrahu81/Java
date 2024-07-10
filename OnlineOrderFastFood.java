import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OnlineOrderFastFood {

    // Nested class for FoodItem
    static class FoodItem {
        private String name;
        private double price;
        private int quantity;

        public FoodItem(String name, double price, int quantity) {
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
            System.out.println("Food Item: " + name);
            System.out.println("Price: $" + price);
            System.out.println("Quantity: " + quantity);
        }
    }

    // Main class containing food menu and main methods
    private ArrayList<FoodItem> menu;
    private Map<String, Integer> cart;

    public OnlineOrderFastFood() {
        menu = new ArrayList<>();
        cart = new HashMap<>();
    }

    public void addFoodItem(String name, double price, int quantity) {
        FoodItem foodItem = new FoodItem(name, price, quantity);
        menu.add(foodItem);
        System.out.println("Food item added successfully!");
    }

    public void displayMenu() {
        System.out.println("\nMenu:");
        for (FoodItem foodItem : menu) {
            foodItem.displayDetails();
            System.out.println();
        }
    }

    public void addToCart(String name, int quantity) {
        boolean found = false;
        for (FoodItem foodItem : menu) {
            if (foodItem.getName().equalsIgnoreCase(name)) {
                if (foodItem.getQuantity() >= quantity) {
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
            System.out.println("Food item \"" + name + "\" not found in the menu.");
        }
    }

    public void viewCart() {
        System.out.println("\nCart:");
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            String name = entry.getKey();
            int quantity = entry.getValue();
            System.out.println("Food Item: " + name + ", Quantity: " + quantity);
        }
    }

    public void calculateTotalBill() {
        double totalBill = 0;
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            String name = entry.getKey();
            int quantity = entry.getValue();
            for (FoodItem foodItem : menu) {
                if (foodItem.getName().equalsIgnoreCase(name)) {
                    totalBill += foodItem.getPrice() * quantity;
                    break;
                }
            }
        }
        System.out.println("Total Bill: $" + totalBill);
    }

    public static void main(String[] args) {
        OnlineOrderFastFood store = new OnlineOrderFastFood();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Food Item to Menu");
            System.out.println("2. Display Menu");
            System.out.println("3. Add to Cart");
            System.out.println("4. View Cart");
            System.out.println("5. Calculate Total Bill");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter food item name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    store.addFoodItem(name, price, quantity);
                    break;
                case 2:
                    store.displayMenu();
                    break;
                case 3:
                    System.out.print("Enter food item name to add to cart: ");
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
