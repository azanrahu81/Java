import java.util.ArrayList;
import java.util.Scanner;

public class BankManagementSystem {

    // Nested class for Customer
    static class Customer {
        private String name;
        private int customerId;
        private ArrayList<Account> accounts;

        public Customer(String name, int customerId) {
            this.name = name;
            this.customerId = customerId;
            this.accounts = new ArrayList<>();
        }

        public String getName() {
            return name;
        }

        public int getCustomerId() {
            return customerId;
        }

        public ArrayList<Account> getAccounts() {
            return accounts;
        }

        public void addAccount(Account account) {
            accounts.add(account);
        }
    }

    // Nested class for Account
    static class Account {
        private int accountId;
        private double balance;

        public Account(int accountId) {
            this.accountId = accountId;
            this.balance = 0.0;
        }

        public int getAccountId() {
            return accountId;
        }

        public double getBalance() {
            return balance;
        }

        public void deposit(double amount) {
            balance += amount;
            System.out.println("Deposit of " + amount + " successful.");
        }

        public void withdraw(double amount) {
            if (amount > balance) {
                System.out.println("Insufficient funds.");
            } else {
                balance -= amount;
                System.out.println("Withdrawal of " + amount + " successful.");
            }
        }

        public void viewAccountDetails() {
            System.out.println("Account ID: " + accountId);
            System.out.println("Balance: " + balance);
        }
    }

    // Main class containing customer list and main methods
    private ArrayList<Customer> customers;

    public BankManagementSystem() {
        customers = new ArrayList<>();
    }

    public void addCustomer(String name, int customerId) {
        Customer customer = new Customer(name, customerId);
        customers.add(customer);
        System.out.println("Customer added successfully!");
    }

    public void openAccount(int customerId, int accountId) {
        Customer customer = null;
        for (Customer c : customers) {
            if (c.getCustomerId() == customerId) {
                customer = c;
                break;
            }
        }
        if (customer == null) {
            System.out.println("Customer with ID " + customerId + " not found.");
            return;
        }

        Account account = new Account(accountId);
        customer.addAccount(account);
        System.out.println("Account opened successfully!");
    }

    public void depositToAccount(int customerId, int accountId, double amount) {
        Customer customer = null;
        Account account = null;
        outerLoop:
        for (Customer c : customers) {
            if (c.getCustomerId() == customerId) {
                customer = c;
                for (Account a : customer.getAccounts()) {
                    if (a.getAccountId() == accountId) {
                        account = a;
                        break outerLoop;
                    }
                }
            }
        }
        if (customer == null) {
            System.out.println("Customer with ID " + customerId + " not found.");
            return;
        }
        if (account == null) {
            System.out.println("Account with ID " + accountId + " not found.");
            return;
        }

        account.deposit(amount);
    }

    public void withdrawFromAccount(int customerId, int accountId, double amount) {
        Customer customer = null;
        Account account = null;
        outerLoop:
        for (Customer c : customers) {
            if (c.getCustomerId() == customerId) {
                customer = c;
                for (Account a : customer.getAccounts()) {
                    if (a.getAccountId() == accountId) {
                        account = a;
                        break outerLoop;
                    }
                }
            }
        }
        if (customer == null) {
            System.out.println("Customer with ID " + customerId + " not found.");
            return;
        }
        if (account == null) {
            System.out.println("Account with ID " + accountId + " not found.");
            return;
        }

        account.withdraw(amount);
    }

    public void viewCustomerDetails(int customerId) {
        Customer customer = null;
        for (Customer c : customers) {
            if (c.getCustomerId() == customerId) {
                customer = c;
                break;
            }
        }
        if (customer == null) {
            System.out.println("Customer with ID " + customerId + " not found.");
            return;
        }

        System.out.println("Customer Name: " + customer.getName());
        System.out.println("Customer ID: " + customer.getCustomerId());
        System.out.println("Accounts:");
        for (Account account : customer.getAccounts()) {
            account.viewAccountDetails();
        }
    }

    public static void main(String[] args) {
        BankManagementSystem bms = new BankManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Customer");
            System.out.println("2. Open Account");
            System.out.println("3. Deposit to Account");
            System.out.println("4. Withdraw from Account");
            System.out.println("5. View Customer Details");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter customer name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter customer ID: ");
                    int customerId = scanner.nextInt();
                    bms.addCustomer(name, customerId);
                    break;
                case 2:
                    System.out.print("Enter customer ID: ");
                    customerId = scanner.nextInt();
                    System.out.print("Enter account ID: ");
                    int accountId = scanner.nextInt();
                    bms.openAccount(customerId, accountId);
                    break;
                case 3:
                    System.out.print("Enter customer ID: ");
                    customerId = scanner.nextInt();
                    System.out.print("Enter account ID: ");
                    accountId = scanner.nextInt();
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    bms.depositToAccount(customerId, accountId, depositAmount);
                    break;
                case 4:
                    System.out.print("Enter customer ID: ");
                    customerId = scanner.nextInt();
                    System.out.print("Enter account ID: ");
                    accountId = scanner.nextInt();
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    bms.withdrawFromAccount(customerId, accountId, withdrawAmount);
                    break;
                case 5:
                    System.out.print("Enter customer ID to view details: ");
                    customerId = scanner.nextInt();
                    bms.viewCustomerDetails(customerId);
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
