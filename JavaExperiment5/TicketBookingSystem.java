package JavaExperiment5;
class SeatManager {
    private int availableSeats;

    public SeatManager(int totalSeats) {
        this.availableSeats = totalSeats;
    }

    // Synchronized booking to prevent race conditions
    public synchronized boolean bookSeat(String customerName, int seatsRequested) {
        if (availableSeats >= seatsRequested) {
            System.out.println(customerName + " booked " + seatsRequested + " seat(s).");
            availableSeats -= seatsRequested;
            return true;
        } else {
            System.out.println(customerName + " failed to book seats. Not enough available.");
            return false;
        }
    }

    public int getAvailableSeats() {
        return availableSeats;
    }
}

class BookingThread extends Thread {
    private String customerName;
    private int seatsToBook;
    private SeatManager manager;

    public BookingThread(String customerName, int seatsToBook, SeatManager manager, int priority) {
        this.customerName = customerName;
        this.seatsToBook = seatsToBook;
        this.manager = manager;
        this.setPriority(priority); // VIP = higher priority
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100); // slight delay 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        manager.bookSeat(customerName, seatsToBook);
    }
}

public class TicketBookingSystem {
    public static void main(String[] args) {
        SeatManager manager = new SeatManager(10); // Total 10 seats

        // VIPs (higher priority)
        BookingThread vip1 = new BookingThread("VIP_MJ", 2, manager, Thread.MAX_PRIORITY);
        BookingThread vip2 = new BookingThread("VIP_KS", 3, manager, Thread.MAX_PRIORITY);

        // Regular customers (lower priority)
        BookingThread user1 = new BookingThread("User_A", 2, manager, Thread.NORM_PRIORITY);
        BookingThread user2 = new BookingThread("User_B", 4, manager, Thread.NORM_PRIORITY);
        BookingThread user3 = new BookingThread("User_C", 1, manager, Thread.MIN_PRIORITY);

        // Start VIP threads first
        vip1.start();
        vip2.start();

        // Then regular threads
        user1.start();
        user2.start();
        user3.start();

        // Wait for all threads to finish
        try {
            vip1.join();
            vip2.join();
            user1.join();
            user2.join();
            user3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final Available Seats: " + manager.getAvailableSeats());
    }
}
