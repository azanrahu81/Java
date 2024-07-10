import java.util.ArrayList;
import java.util.Scanner;

public class TimeManagementSystem {

    // Nested class for User
    static class User {
        private String username;
        private String password;
        private String firstName;
        private String lastName;
        private String fatherName;
        private int birthYear;
        private String education;
        private String phoneNumber;
        private String address;
        private String district;

        public User(String username, String password, String firstName, String lastName, String fatherName,
                    int birthYear, String education, String phoneNumber, String address, String district) {
            this.username = username;
            this.password = password;
            this.firstName = firstName;
            this.lastName = lastName;
            this.fatherName = fatherName;
            this.birthYear = birthYear;
            this.education = education;
            this.phoneNumber = phoneNumber;
            this.address = address;
            this.district = district;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }

    // Nested class for Project
    static class Project {
        private String projectName;
        private String deadline;
        private String description;

        public Project(String projectName, String deadline, String description) {
            this.projectName = projectName;
            this.deadline = deadline;
            this.description = description;
        }

        public void displayDetails() {
            System.out.println("Project Name: " + projectName);
            System.out.println("Deadline: " + deadline);
            System.out.println("Description: " + description);
        }
    }

    // Main class containing users, projects, and main methods
    private ArrayList<User> users;
    private ArrayList<Project> projects;

    public TimeManagementSystem() {
        users = new ArrayList<>();
        projects = new ArrayList<>();
        initializeProjects();
    }

    private void initializeProjects() {
        projects.add(new Project("Project Alpha", "2024-12-31", "Description of Project Alpha"));
        projects.add(new Project("Project Beta", "2024-11-30", "Description of Project Beta"));
        projects.add(new Project("Project Gamma", "2024-10-31", "Description of Project Gamma"));
    }

    public void signUp(String username, String password, String firstName, String lastName, String fatherName,
                       int birthYear, String education, String phoneNumber, String address, String district) {
        User user = new User(username, password, firstName, lastName, fatherName, birthYear, education, phoneNumber, address, district);
        users.add(user);
        System.out.println("User signed up successfully!");
    }

    public boolean login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public void displayProjects() {
        System.out.println("\nProjects:");
        for (Project project : projects) {
            project.displayDetails();
            System.out.println();
        }
    }

    public static void main(String[] args) {
        TimeManagementSystem system = new TimeManagementSystem();
        Scanner scanner = new Scanner(System.in);
        boolean loggedIn = false;

        while (!loggedIn) {
            System.out.println("1. Sign Up");
            System.out.println("2. Log In");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
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
                    System.out.print("Enter address: ");
                    String address = scanner.nextLine();
                    System.out.print("Enter district: ");
                    String district = scanner.nextLine();
                    system.signUp(username, password, firstName, lastName, fatherName, birthYear, education, phoneNumber, address, district);
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    password = scanner.nextLine();
                    if (system.login(username, password)) {
                        System.out.println("Login successful!");
                        loggedIn = true;
                        system.displayProjects();
                    } else {
                        System.out.println("Invalid username or password. Please try again.");
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}
