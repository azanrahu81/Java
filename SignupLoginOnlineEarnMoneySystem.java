import java.util.ArrayList;
import java.util.Scanner;

public class SignupLoginOnlineEarnMoneySystem 
{

    // Nested class for User
    static class User 
    {
        String username;
        String password;
        String firstName;
        String lastName;
        String fatherName;
        int birthYear;
        String education;
        String phoneNumber;
        String city;
        String district;
        double balance;

        public User(String username, String password, String firstName, String lastName, String fatherName, int birthYear,
                    String education, String phoneNumber, String city, String district) 
        {
            this.username = username;
            this.password = password;
            this.firstName = firstName;
            this.lastName = lastName;
            this.fatherName = fatherName;
            this.birthYear = birthYear;
            this.education = education;
            this.phoneNumber = phoneNumber;
            this.city = city;
            this.district = district;
            this.balance = 0.0;
        }

        public String getUsername() 
        {
            return username;
        }

        public String getPassword() 
        {
            return password;
        }

        public double getBalance()
         {
            return balance;
        }

        public void deposit(double amount)
         {
            balance += amount;
            System.out.println(amount + " deposited successfully. Current balance: $" + balance);
        }

        public void withdraw(double amount)
         {
            if (balance >= amount) 
            {
                balance -= amount;
                System.out.println(amount + " withdrawn successfully. Current balance: $" + balance);
            } 
            else 
            {
                System.out.println("Insufficient balance. Withdrawal failed.");
            }
        }

        public void displayBalance() 
        {
            System.out.println("Current balance for " + username + ": $" + balance);
        }

        public void displayDetails() 
        {
            System.out.println("Username: " + username);
            System.out.println("First Name: " + firstName);
            System.out.println("Last Name: " + lastName);
            System.out.println("Father's Name: " + fatherName);
            System.out.println("Birth Year: " + birthYear);
            System.out.println("Education: " + education);
            System.out.println("Phone Number: " + phoneNumber);
            System.out.println("City: " + city);
            System.out.println("District: " + district);
        }
    }

    // Main class containing users and main methods
    ArrayList<User> users;
    User loggedInUser;

    // Static block to initialize users
    static {
        SignupOnlineEarnMoneySystem system = new SignupOnlineEarnMoneySystem();
        system.registerUser("john", "password123", "John", "Doe", "Mr. Doe", 1985, "Masters", "1234567890", "New York", "NY");
        system.registerUser("jane", "password456", "Jane", "Doe", "Mr. Doe", 1990, "Bachelors", "0987654321", "Los Angeles", "CA");
    }

    public SignupLoginOnlineEarnMoneySystem() 
    {
        users = new ArrayList<>();
    }

    public void registerUser(String username, String password, String firstName, String lastName, String fatherName,
                             int birthYear, String education, String phoneNumber, String city, String district) 
    {
        User user = new User(username, password, firstName, lastName, fatherName, birthYear, education, phoneNumber, city, district);
        users.add(user);
        System.out.println("User " + username + " registered successfully!");
    }

    public User loginUser(String username, String password) 
    {
        for (User user : users) 
        {
            if (user.getUsername().equals(username)) 
            {
                if (user.getPassword().equals(password))
                 {
                    return user;
                } else {
                    System.out.println("Incorrect password.");
                    return null;
                }
            }
        }
        System.out.println("Username not found.");
        return null;
    }

    public void displayUsers() 
    {
        System.out.println("\nUsers:");
        for (User user : users) 
        {
            user.displayDetails();
            System.out.println("Balance: $" + user.getBalance());
            System.out.println("-------------------");
        }
    }

    public static void main(String[] args)
     {
        SignupLoginOnlineEarnMoneySystem system = new SignupLoginOnlineEarnMoneySystem();
        Scanner scanner = new Scanner(System.in);

        // Skip signup process if there are already users
        if (system.users.isEmpty()) {
            // Signup process
            System.out.println("Signup Form");
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            System.out.print("Enter first name: ");
            String firstName = scanner.nextLine();
            System.out.print("Enter last name: ");
            String lastName = scanner.nextLine();
            System.out.print("Enter father's name: ");
            String fatherName = scanner.nextLine();
            System.out.print("Enter birth year: ");
            int birthYear = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter education: ");
            String education = scanner.nextLine();
            System.out.print("Enter phone number: ");
            String phoneNumber = scanner.nextLine();
            System.out.print("Enter city: ");
            String city = scanner.nextLine();
            System.out.print("Enter district: ");
            String district = scanner.nextLine();

            system.registerUser(username, password, firstName, lastName, fatherName, birthYear, education, phoneNumber, city, district);
        }

        // Login process
        System.out.println("Login Form");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        User user = system.loginUser(username, password);

        if (user != null)
         {
            system.loggedInUser = user;
            System.out.println("Login successful! Welcome, " + username + ".");
        } 
        else
         {
            System.out.println("Login failed. Exiting...");
            scanner.close();
            return;
        }

        // Menu after successful login
        while (true)
         {
            System.out.println("\n1. Display Users");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Display Balance");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice)
             {
                case 1:
                    system.displayUsers();
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    system.loggedInUser.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    system.loggedInUser.withdraw(withdrawAmount);
                    break;
                case 4:
                    system.loggedInUser.displayBalance();
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
