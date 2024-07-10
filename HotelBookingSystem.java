import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HotelBookingSystem {

    // Inner class for a hotel room
    class Room {
        private int roomNumber;
        private boolean isBooked;

        public Room(int roomNumber) {
            this.roomNumber = roomNumber;
            this.isBooked = false;
        }

        public int getRoomNumber() {
            return roomNumber;
        }

        public boolean isBooked() {
            return isBooked;
        }

        public void bookRoom() {
            if (!isBooked) {
                isBooked = true;
            }
        }

        public void unbookRoom() {
            if (isBooked) {
                isBooked = false;
            }
        }
    }

    // Inner class for a booking
    class Booking {
        private String customerName;
        private int roomNumber;
        private int nights;

        public Booking(String customerName, int roomNumber, int nights) {
            this.customerName = customerName;
            this.roomNumber = roomNumber;
            this.nights = nights;
        }

        public String getCustomerName() {
            return customerName;
        }

        public int getRoomNumber() {
            return roomNumber;
        }

        public int getNights() {
            return nights;
        }

        @Override
        public String toString() {
            return "Booking[Customer: " + customerName + ", Room: " + roomNumber + ", Nights: " + nights + "]";
        }
    }

    // Main method to demonstrate functionality
    public static void main(String[] args) {
        HotelBookingSystem system = new HotelBookingSystem();
        List<Room> rooms = new ArrayList<>();
        List<Booking> bookings = new ArrayList<>();

        // Adding some rooms
        for (int i = 1; i <= 5; i++) {
            rooms.add(system.new Room(i));
        }

        // Creating a booking
        Booking booking1 = system.new Booking("John Doe", 1, 3);
        bookings.add(booking1);
        rooms.get(booking1.getRoomNumber() - 1).bookRoom();

        // Creating another booking
        Booking booking2 = system.new Booking("Jane Smith", 2, 2);
        bookings.add(booking2);
        rooms.get(booking2.getRoomNumber() - 1).bookRoom();

        // Saving bookings to a file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("bookings.txt"))) {
            for (Booking booking : bookings) {
                writer.write(booking.toString());
                writer.newLine();
            }
            System.out.println("Bookings saved to bookings.txt");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

        // Displaying room statuses
        for (Room room : rooms) {
            System.out.println("Room " + room.getRoomNumber() + " is " + (room.isBooked ? "booked" : "available"));
        }
    }
}
