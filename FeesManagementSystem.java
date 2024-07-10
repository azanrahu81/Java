import java.util.ArrayList;
import java.util.Scanner;

public class FeesManagementSystem {

    // Nested class for Student
    static class Student {
        private String name;
        private int studentId;
        private ArrayList<Course> enrolledCourses;

        public Student(String name, int studentId) {
            this.name = name;
            this.studentId = studentId;
            this.enrolledCourses = new ArrayList<>();
        }

        public String getName() {
            return name;
        }

        public int getStudentId() {
            return studentId;
        }

        public ArrayList<Course> getEnrolledCourses() {
            return enrolledCourses;
        }

        public void enrollCourse(Course course) {
            enrolledCourses.add(course);
        }

        public void payFees(int courseId) {
            for (Course course : enrolledCourses) {
                if (course.getCourseId() == courseId) {
                    course.setPaid(true);
                    System.out.println("Fees paid for course: " + course.getCourseName());
                    return;
                }
            }
            System.out.println("Course not found or not enrolled.");
        }

        public void viewFeeStatus() {
            System.out.println("Fee Status for " + name + ":");
            for (Course course : enrolledCourses) {
                System.out.println("Course: " + course.getCourseName() + " | Paid: " + (course.isPaid() ? "Yes" : "No"));
            }
        }
    }

    // Nested class for Course
    static class Course {
        private String courseName;
        private int courseId;
        private double fee;
        private boolean isPaid;

        public Course(String courseName, int courseId, double fee) {
            this.courseName = courseName;
            this.courseId = courseId;
            this.fee = fee;
            this.isPaid = false;
        }

        public String getCourseName() {
            return courseName;
        }

        public int getCourseId() {
            return courseId;
        }

        public double getFee() {
            return fee;
        }

        public boolean isPaid() {
            return isPaid;
        }

        public void setPaid(boolean isPaid) {
            this.isPaid = isPaid;
        }
    }

    // Main class containing student and course lists and main methods
    private ArrayList<Student> students;
    private ArrayList<Course> courses;

    public FeesManagementSystem() {
        students = new ArrayList<>();
        courses = new ArrayList<>();
    }

    public void addStudent(String name, int studentId) {
        Student student = new Student(name, studentId);
        students.add(student);
        System.out.println("Student added successfully!");
    }

    public void addCourse(String courseName, int courseId, double fee) {
        Course course = new Course(courseName, courseId, fee);
        courses.add(course);
        System.out.println("Course added successfully!");
    }

    public void enrollStudentInCourse(int studentId, int courseId) {
        Student student = null;
        for (Student s : students) {
            if (s.getStudentId() == studentId) {
                student = s;
                break;
            }
        }
        if (student == null) {
            System.out.println("Student with ID " + studentId + " not found.");
            return;
        }

        Course course = null;
        for (Course c : courses) {
            if (c.getCourseId() == courseId) {
                course = c;
                break;
            }
        }
        if (course == null) {
            System.out.println("Course with ID " + courseId + " not found.");
            return;
        }

        student.enrollCourse(course);
        System.out.println("Student enrolled in course successfully!");
    }

    public void viewStudentDetails(int studentId) {
        for (Student student : students) {
            if (student.getStudentId() == studentId) {
                System.out.println("Student Name: " + student.getName());
                System.out.println("Student ID: " + student.getStudentId());
                System.out.print("Enrolled Courses: ");
                for (Course course : student.getEnrolledCourses()) {
                    System.out.print(course.getCourseName() + " ");
                }
                System.out.println();
                student.viewFeeStatus();
                return;
            }
        }
        System.out.println("Student with ID " + studentId + " not found.");
    }

    public void viewCourseDetails(int courseId) {
        for (Course course : courses) {
            if (course.getCourseId() == courseId) {
                System.out.println("Course Name: " + course.getCourseName());
                System.out.println("Course ID: " + course.getCourseId());
                System.out.println("Fee: " + course.getFee());
                System.out.println("Is Paid: " + (course.isPaid() ? "Yes" : "No"));
                return;
            }
        }
        System.out.println("Course with ID " + courseId + " not found.");
    }

    public static void main(String[] args) {
        FeesManagementSystem fms = new FeesManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Student");
            System.out.println("2. Add Course");
            System.out.println("3. Enroll Student in Course");
            System.out.println("4. Pay Fees");
            System.out.println("5. View Student Details");
            System.out.println("6. View Course Details");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter student ID: ");
                    int studentId = scanner.nextInt();
                    fms.addStudent(name, studentId);
                    break;
                case 2:
                    System.out.print("Enter course name: ");
                    String courseName = scanner.nextLine();
                    System.out.print("Enter course ID: ");
                    int courseId = scanner.nextInt();
                    System.out.print("Enter course fee: ");
                    double fee = scanner.nextDouble();
                    fms.addCourse(courseName, courseId, fee);
                    break;
                case 3:
                    System.out.print("Enter student ID: ");
                    studentId = scanner.nextInt();
                    System.out.print("Enter course ID: ");
                    courseId = scanner.nextInt();
                    fms.enrollStudentInCourse(studentId, courseId);
                    break;
                case 4:
                    System.out.print("Enter student ID: ");
                    studentId = scanner.nextInt();
                    System.out.print("Enter course ID: ");
                    courseId = scanner.nextInt();
                    for (Student s : fms.students) {
                        if (s.getStudentId() == studentId) {
                            s.payFees(courseId);
                            break;
                        }
                    }
                    break;
                case 5:
                    System.out.print("Enter student ID to view details: ");
                    studentId = scanner.nextInt();
                    fms.viewStudentDetails(studentId);
                    break;
                case 6:
                    System.out.print("Enter course ID to view details: ");
                    courseId = scanner.nextInt();
                    fms.viewCourseDetails(courseId);
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
