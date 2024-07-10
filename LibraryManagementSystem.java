import java.util.ArrayList;
import java.util.Scanner;

public class LibraryManagementSystem {

    // Nested class for Book
    static class Book {
        private String title;
        private int bookId;
        private String author;
        private boolean isIssued;

        public Book(String title, int bookId, String author) {
            this.title = title;
            this.bookId = bookId;
            this.author = author;
            this.isIssued = false;
        }

        public String getTitle() {
            return title;
        }

        public int getBookId() {
            return bookId;
        }

        public String getAuthor() {
            return author;
        }

        public boolean isIssued() {
            return isIssued;
        }

        public void issueBook() {
            this.isIssued = true;
        }

        public void returnBook() {
            this.isIssued = false;
        }
    }

    // Nested class for Member
    static class Member {
        private String name;
        private int memberId;

        public Member(String name, int memberId) {
            this.name = name;
            this.memberId = memberId;
        }

        public String getName() {
            return name;
        }

        public int getMemberId() {
            return memberId;
        }
    }

    // Main class containing book and member lists and main methods
    private ArrayList<Book> books;
    private ArrayList<Member> members;

    public LibraryManagementSystem() {
        books = new ArrayList<>();
        members = new ArrayList<>();
    }

    public void addBook(String title, int bookId, String author) {
        Book book = new Book(title, bookId, author);
        books.add(book);
        System.out.println("Book added successfully!");
    }

    public void addMember(String name, int memberId) {
        Member member = new Member(name, memberId);
        members.add(member);
        System.out.println("Member added successfully!");
    }

    public void issueBook(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                if (!book.isIssued()) {
                    book.issueBook();
                    System.out.println("Book issued successfully!");
                } else {
                    System.out.println("Book is already issued.");
                }
                return;
            }
        }
        System.out.println("Book with ID " + bookId + " not found.");
    }

    public void returnBook(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                if (book.isIssued()) {
                    book.returnBook();
                    System.out.println("Book returned successfully!");
                } else {
                    System.out.println("Book is not issued.");
                }
                return;
            }
        }
        System.out.println("Book with ID " + bookId + " not found.");
    }

    public void viewBookDetails(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                System.out.println("Book Title: " + book.getTitle());
                System.out.println("Book ID: " + book.getBookId());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("Is Issued: " + book.isIssued());
                return;
            }
        }
        System.out.println("Book with ID " + bookId + " not found.");
    }

    public void viewMemberDetails(int memberId) {
        for (Member member : members) {
            if (member.getMemberId() == memberId) {
                System.out.println("Member Name: " + member.getName());
                System.out.println("Member ID: " + member.getMemberId());
                return;
            }
        }
        System.out.println("Member with ID " + memberId + " not found.");
    }

    public static void main(String[] args) {
        LibraryManagementSystem lms = new LibraryManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. View Book Details");
            System.out.println("6. View Member Details");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book ID: ");
                    int bookId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();
                    lms.addBook(title, bookId, author);
                    break;
                case 2:
                    System.out.print("Enter member name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter member ID: ");
                    int memberId = scanner.nextInt();
                    lms.addMember(name, memberId);
                    break;
                case 3:
                    System.out.print("Enter book ID to issue: ");
                    bookId = scanner.nextInt();
                    lms.issueBook(bookId);
                    break;
                case 4:
                    System.out.print("Enter book ID to return: ");
                    bookId = scanner.nextInt();
                    lms.returnBook(bookId);
                    break;
                case 5:
                    System.out.print("Enter book ID to view details: ");
                    bookId = scanner.nextInt();
                    lms.viewBookDetails(bookId);
                    break;
                case 6:
                    System.out.print("Enter member ID to view details: ");
                    memberId = scanner.nextInt();
                    lms.viewMemberDetails(memberId);
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
