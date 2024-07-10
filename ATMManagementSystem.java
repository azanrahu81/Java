import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ATMManagementSystem {

    // Nested class for Account
    static class Account {
        private int accountId;
        private String accountHolderName;
        private double balance;

        public Account(int accountId, String accountHolderName, double balance) {
            this.accountId = accountId;
            this.accountHolderName = accountHolderName;
            this.balance = balance;
        }

        public int getAccountId() {
            return accountId;
        }

        public String getAccountHolderName() {
            return accountHolderName;
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
            System.out.println("Account Holder Name: " + accountHolderName);
            System.out.println("Balance: " + balance);
        }
    }

    // Main class containing account list and main methods
    private Map<Integer, Account> accounts;
    private int accountIdCounter;

    public ATMManagementSystem() {
        accounts = new HashMap<>();
        accountIdCounter = 1;
    }

    public void createAccount(String accountHolderName, double initialBalance) {
        Account account = new Account(accountIdCounter++, accountHolderName, initialBalance);
        accounts.put(account.getAccountId(), account);
        System.out.println("Account created successfully!");
    }

    public void depositToAccount(int accountId, double amount) {
        Account account = accounts.get(accountId);
        if (account == null) {
            System.out.println("Account with ID " + accountId + " not found.");
            return;
        }

        account.deposit(amount);
    }

    public void withdrawFromAccount(int accountId, double amount) {
        Account account = accounts.get(accountId);
        if (account == null) {
            System.out.println("Account with ID " + accountId + " not found.");
            return;
        }

        account.withdraw(amount);
    }

    public void checkBalance(int accountId) {
        Account account = accounts.get(accountId);
        if (account == null) {
            System.out.println("Account with ID " + accountId + " not found.");
            return;
        }

        System.out.println("Balance for Account ID " + accountId + ": " + account.getBalance());
    }

    public static void main(String[] args) {
        ATMManagementSystem atm = new ATMManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Create Account");
            System.out.println("2. Deposit to Account");
            System.out.println("3. Withdraw from Account");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter account holder name: ");
                    String accountHolderName = scanner.nextLine();
                    System.out.print("Enter initial balance: ");
                    double initialBalance = scanner.nextDouble();
                    atm.createAccount(accountHolderName, initialBalance);
                    break;
                case 2:
                    System.out.print("Enter account ID: ");
                    int accountId = scanner.nextInt();
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    atm.depositToAccount(accountId, depositAmount);
                    break;
                case 3:
                    System.out.print("Enter account ID: ");
                    accountId = scanner.nextInt();
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    atm.withdrawFromAccount(accountId, withdrawAmount);
                    break;
                case 4:
                    System.out.print("Enter account ID: ");
                    accountId = scanner.nextInt();
                    atm.checkBalance(accountId);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
