import java.util.ArrayList;
import java.util.Scanner;

public class ScheduleManagementSystem {

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

    // Nested class for Class Schedule
    static class ClassSchedule {
        private String courseName;
        private String instructor;
        private String timing;

        public ClassSchedule(String courseName, String instructor, String timing) {
            this.courseName = courseName;
            this.instructor = instructor;
            this.timing = timing;
        }

        public void displayDetails() {
            System.out.println("Course Name: " + courseName);
            System.out.println("Instructor: " + instructor);
            System.out.println("Timing: " + timing);
        }
    }

    // Main class containing users, class schedules, and main methods
    private ArrayList<User> users;
    private ArrayList<ClassSchedule> schedules;

    public ScheduleManagementSystem() {
        users = new ArrayList<>();
        schedules = new ArrayList<>();
        initializeSchedules();
    }

    private void initializeSchedules() {
        schedules.add(new ClassSchedule("Mathematics", "Mr. Smith", "Monday, Wednesday, Friday 10:00 AM"));
        schedules.add(new ClassSchedule("English Literature", "Ms. Johnson", "Tuesday, Thursday 2:00 PM"));
        schedules.add(new ClassSchedule("Science", "Dr. Brown", "Monday, Wednesday, Friday 1:00 PM"));
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

    public void displaySchedules() {
        System.out.println("\nClass Schedules:");
        for (ClassSchedule schedule : schedules) {
            schedule.displayDetails();
            System.out.println();
        }
    }

    public static void main(String[] args) {
        ScheduleManagementSystem system = new ScheduleManagementSystem();
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
                        system.displaySchedules();
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
