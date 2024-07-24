import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Hotel {
    private String name;
    private List<Room> rooms;

    public Hotel(String name) {
        this.name = name;
        this.rooms = new ArrayList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public List<Room> getAvailableRooms() {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (!room.isBooked()) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public boolean reserveRoom(Room room, Guest guest, int nights) {
        if (room.isBooked()) {
            return false;
        }
        Booking booking = new Booking(room, guest, nights);
        room.setBooking(booking);
        return true;
    }

    public void displayBookingDetails() {
        System.out.println("Booking Details for Hotel " + name);
        for (Room room : rooms) {
            if (room.isBooked()) {
                Booking booking = room.getBooking();
                System.out.println("Room: " + room.getRoomNumber() +
                        ", Guest: " + booking.getGuest().getName() +
                        ", Nights: " + booking.getNights());
            }
        }
    }

    public String getName() {
        return name;
    }
}

class Room {
    private int roomNumber;
    private String category;
    private boolean booked;
    private Booking booking;

    public Room(int roomNumber, String category) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.booked = false;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
        this.booked = true;
    }

    public Booking getBooking() {
        return booking;
    }
}

class Guest {
    private String name;

    public Guest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Booking {
    private Room room;
    private Guest guest;
    private int nights;

    public Booking(Room room, Guest guest, int nights) {
        this.room = room;
        this.guest = guest;
        this.nights = nights;
    }

    public Room getRoom() {
        return room;
    }

    public Guest getGuest() {
        return guest;
    }

    public int getNights() {
        return nights;
    }
}

public class HotelReservationSystem {

    public static void main(String[] args) {
        // Create a hotel with some rooms
        Hotel hotel = new Hotel("Grand Hotel");

        Room room1 = new Room(101, "Standard");
        Room room2 = new Room(102, "Deluxe");
        Room room3 = new Room(103, "Suite");

        hotel.addRoom(room1);
        hotel.addRoom(room2);
        hotel.addRoom(room3);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to " + hotel.getName() + "!");
        System.out.println("Available rooms:");

        List<Room> availableRooms = hotel.getAvailableRooms();
        for (Room room : availableRooms) {
            System.out.println("Room Number: " + room.getRoomNumber() +
                    ", Category: " + room.getCategory());
        }

        System.out.print("Enter the room number you want to reserve: ");
        int roomNumber = scanner.nextInt();

        Room selectedRoom = null;
        for (Room room : availableRooms) {
            if (room.getRoomNumber() == roomNumber) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom != null) {
            System.out.print("Enter your name: ");
            scanner.nextLine(); // Consume newline left-over
            String guestName = scanner.nextLine();

            Guest guest = new Guest(guestName);

            System.out.print("Enter number of nights: ");
            int nights = scanner.nextInt();

            boolean reserved = hotel.reserveRoom(selectedRoom, guest, nights);
            if (reserved) {
                System.out.println("Room reserved successfully!");
                hotel.displayBookingDetails();
            } else {
                System.out.println("Room is already booked. Reservation failed.");
            }
        } else {
            System.out.println("Invalid room number entered. Reservation failed.");
        }

        scanner.close();
    }
}
