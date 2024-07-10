import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RestaurantManagementSystem {

    // Nested class for MenuItem
    static class MenuItem {
        private String name;
        private double price;

        public MenuItem(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public void displayDetails() {
            System.out.println("Menu Item: " + name);
            System.out.println("Price: $" + price);
        }
    }

    // Nested class for Table
    static class Table {
        private int tableNumber;
        private Map<String, Integer> order;

        public Table(int tableNumber) {
            this.tableNumber = tableNumber;
            this.order = new HashMap<>();
        }

        public int getTableNumber() {
            return tableNumber;
        }

        public void addToOrder(String itemName, int quantity) {
            order.put(itemName, quantity);
            System.out.println("Added to order: " + itemName + " (x" + quantity + ")");
        }

        public void displayOrder() {
            System.out.println("Table " + tableNumber + " Order:");
            for (Map.Entry<String, Integer> entry : order.entrySet()) {
                String itemName = entry.getKey();
                int quantity = entry.getValue();
                System.out.println(itemName + " (x" + quantity + ")");
            }
        }

        public double calculateBill(ArrayList<MenuItem> menu) {
            double totalBill = 0;
            for (Map.Entry<String, Integer> entry : order.entrySet()) {
                String itemName = entry.getKey();
                int quantity = entry.getValue();
                for (MenuItem item : menu) {
                    if (item.getName().equalsIgnoreCase(itemName)) {
                        totalBill += item.getPrice() * quantity;
                        break;
                    }
                }
            }
            return totalBill;
        }
    }

    // Main class containing menu and tables
    private ArrayList<MenuItem> menu;
    private ArrayList<Table> tables;

    public RestaurantManagementSystem() {
        menu = new ArrayList<>();
        tables = new ArrayList<>();
    }

    public void addMenuItem(String name, double price) {
        MenuItem item = new MenuItem(name, price);
        menu.add(item);
        System.out.println("Menu item added successfully!");
    }

    public void displayMenu() {
        System.out.println("\nMenu:");
        for (MenuItem item : menu) {
            item.displayDetails();
            System.out.println();
        }
    }

    public void addTable(int tableNumber) {
        Table table = new Table(tableNumber);
        tables.add(table);
        System.out.println("Table " + tableNumber + " added successfully!");
    }

    public Table findTable(int tableNumber) {
        for (Table table : tables) {
            if (table.getTableNumber() == tableNumber) {
                return table;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        RestaurantManagementSystem restaurant = new RestaurantManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Menu Item");
            System.out.println("2. Display Menu");
            System.out.println("3. Add Table");
            System.out.println("4. Place Order");
            System.out.println("5. View Order");
            System.out.println("6. Calculate Bill");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter item name: ");
                    String itemName = scanner.nextLine();
                    System.out.print("Enter price: ");
                    double price = scanner.nextDouble();
                    restaurant.addMenuItem(itemName, price);
                    break;
                case 2:
                    restaurant.displayMenu();
                    break;
                case 3:
                    System.out.print("Enter table number: ");
                    int tableNumber = scanner.nextInt();
                    restaurant.addTable(tableNumber);
                    break;
                case 4:
                    System.out.print("Enter table number to place order: ");
                    tableNumber = scanner.nextInt();
                    Table table = restaurant.findTable(tableNumber);
                    if (table != null) {
                        scanner.nextLine(); // Consume newline
                        while (true) {
                            System.out.print("Enter item name to add to order (or 'done' to finish): ");
                            itemName = scanner.nextLine();
                            if (itemName.equalsIgnoreCase("done")) {
                                break;
                            }
                            System.out.print("Enter quantity: ");
                            int quantity = scanner.nextInt();
                            table.addToOrder(itemName, quantity);
                            scanner.nextLine(); // Consume newline
                        }
                    } else {
                        System.out.println("Table " + tableNumber + " not found.");
                    }
                    break;
                case 5:
                    System.out.print("Enter table number to view order: ");
                    tableNumber = scanner.nextInt();
                    table = restaurant.findTable(tableNumber);
                    if (table != null) {
                        table.displayOrder();
                    } else {
                        System.out.println("Table " + tableNumber + " not found.");
                    }
                    break;
                case 6:
                    System.out.print("Enter table number to calculate bill: ");
                    tableNumber = scanner.nextInt();
                    table = restaurant.findTable(tableNumber);
                    if (table != null) {
                        double bill = table.calculateBill(restaurant.menu);
                        System.out.println("Total Bill for Table " + tableNumber + ": $" + bill);
                    } else {
                        System.out.println("Table " + tableNumber + " not found.");
                    }
                    break;
                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
