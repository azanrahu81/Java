import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HotelManagementSystem {

    // Nested class for Room
    static class Room {
        private int roomNumber;
        private boolean isOccupied;
        private double rate;

        public Room(int roomNumber, double rate) {
            this.roomNumber = roomNumber;
            this.rate = rate;
            this.isOccupied = false;
        }

        public int getRoomNumber() {
            return roomNumber;
        }

        public boolean isOccupied() {
            return isOccupied;
        }

        public void setOccupied(boolean occupied) {
            isOccupied = occupied;
        }

        public double getRate() {
            return rate;
        }

        public void displayDetails() {
            System.out.println("Room Number: " + roomNumber);
            System.out.println("Rate per night: $" + rate);
            System.out.println("Occupied: " + (isOccupied ? "Yes" : "No"));
        }
    }

    // Nested class for Guest
    static class Guest {
         String name;
        int roomNumber;
         int numNights;

        public Guest(String name, int roomNumber, int numNights) {
            this.name = name;
            this.roomNumber = roomNumber;
            this.numNights = numNights;
        }

        public String getName() {
            return name;
        }

        public int getRoomNumber() {
            return roomNumber;
        }

        public int getNumNights() {
            return numNights;
        }
    }

    // Main class containing rooms, guests, and main methods
     ArrayList<Room> rooms;
     ArrayList<Guest> guests;

    public HotelManagementSystem() {
        rooms = new ArrayList<>();
        guests = new ArrayList<>();
    }

    public void addRoom(int roomNumber, double rate) {
        Room room = new Room(roomNumber, rate);
        rooms.add(room);
        System.out.println("Room added successfully!");
    }

    public void displayRooms() {
        System.out.println("\nRooms:");
        for (Room room : rooms) {
            room.displayDetails();
            System.out.println();
        }
    }

    public void checkIn(String guestName, int roomNumber, int numNights) {
        boolean roomFound = false;
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber && !room.isOccupied()) {
                room.setOccupied(true);
                Guest guest = new Guest(guestName, roomNumber, numNights);
                guests.add(guest);
                roomFound = true;
                System.out.println("Check-in successful!");
                break;
            }
        }
        if (!roomFound) {
            System.out.println("Room " + roomNumber + " is either occupied or does not exist.");
        }
    }

    public void displayGuests() {
        System.out.println("\nGuests:");
        for (Guest guest : guests) {
            System.out.println("Guest Name: " + guest.getName());
            System.out.println("Room Number: " + guest.getRoomNumber());
            System.out.println("Number of Nights: " + guest.getNumNights());
            System.out.println();
        }
    }

    public void checkOut(int roomNumber) {
        boolean guestFound = false;
        for (Guest guest : guests) {
            if (guest.getRoomNumber() == roomNumber) {
                guests.remove(guest);
                for (Room room : rooms) {
                    if (room.getRoomNumber() == roomNumber) {
                        room.setOccupied(false);
                        guestFound = true;
                        System.out.println("Check-out successful!");
                        break;
                    }
                }
                break;
            }
        }
        if (!guestFound) {
            System.out.println("No guest found in Room " + roomNumber + ".");
        }
    }

    public double calculateBill(int roomNumber) {
        double totalBill = 0;
        for (Guest guest : guests) {
            if (guest.getRoomNumber() == roomNumber) {
                for (Room room : rooms) {
                    if (room.getRoomNumber() == roomNumber) {
                        totalBill = guest.getNumNights() * room.getRate();
                        break;
                    }
                }
                break;
            }
        }
        return totalBill;
    }

    public static void main(String[] args) {
        HotelManagementSystem hotel = new HotelManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Room");
            System.out.println("2. Display Rooms");
            System.out.println("3. Check-in Guest");
            System.out.println("4. Display Guests");
            System.out.println("5. Check-out Guest");
            System.out.println("6. Calculate Bill");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter room number: ");
                    int roomNumber = scanner.nextInt();
                    System.out.print("Enter rate per night: ");
                    double rate = scanner.nextDouble();
                    hotel.addRoom(roomNumber, rate);
                    break;
                case 2:
                    hotel.displayRooms();
                    break;
                case 3:
                    System.out.print("Enter guest name: ");
                    String guestName = scanner.nextLine();
                    System.out.print("Enter room number: ");
                    roomNumber = scanner.nextInt();
                    System.out.print("Enter number of nights: ");
                    int numNights = scanner.nextInt();
                    hotel.checkIn(guestName, roomNumber, numNights);
                    scanner.nextLine(); // Consume newline
                    break;
                case 4:
                    hotel.displayGuests();
                    break;
                case 5:
                    System.out.print("Enter room number to check-out: ");
                    roomNumber = scanner.nextInt();
                    hotel.checkOut(roomNumber);
                    break;
                case 6:
                    System.out.print("Enter room number to calculate bill: ");
                    roomNumber = scanner.nextInt();
                    double bill = hotel.calculateBill(roomNumber);
                    System.out.println("Total Bill for Room " + roomNumber + ": $" + bill);
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
