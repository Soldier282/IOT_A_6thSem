class TicketBookingSystem {
    private boolean[] seats;

    public TicketBookingSystem(int numberOfSeats) {
        seats = new boolean[numberOfSeats]; // false = available, true = booked
    }

    // Synchronized to prevent race conditions
    public synchronized boolean bookSeat(int seatNumber, String customerName) {
        if (seatNumber < 0 || seatNumber >= seats.length) {
            System.out.println(customerName + " tried to book invalid seat " + seatNumber);
            return false;
        }
        if (!seats[seatNumber]) {
            seats[seatNumber] = true;
            System.out.println(customerName + " successfully booked seat " + seatNumber);
            return true;
        } else {
            System.out.println(customerName + " failed to book seat " + seatNumber + " (already booked)");
            return false;
        }
    }
}

class Customer extends Thread {
    private TicketBookingSystem bookingSystem;
    private int seatNumber;
    private String customerName;

    public Customer(TicketBookingSystem bookingSystem, int seatNumber, String customerName, int priority) {
        this.bookingSystem = bookingSystem;
        this.seatNumber = seatNumber;
        this.customerName = customerName;
        setName(customerName);
        setPriority(priority); // Higher priority for VIPs
    }

    @Override
    public void run() {
        bookingSystem.bookSeat(seatNumber, customerName);
    }
}

public class TicketBookingMain {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem(10); // 10 seats

        Customer vip1 = new Customer(system, 3, "VIP_Customer1", Thread.MAX_PRIORITY);
        Customer vip2 = new Customer(system, 5, "VIP_Customer2", Thread.MAX_PRIORITY);

        Customer customer1 = new Customer(system, 3, "Regular_Customer1", Thread.NORM_PRIORITY);
        Customer customer2 = new Customer(system, 5, "Regular_Customer2", Thread.NORM_PRIORITY);
        Customer customer3 = new Customer(system, 6, "Regular_Customer3", Thread.NORM_PRIORITY);

        vip1.start();
        vip2.start();
        customer1.start();
        customer2.start();
        customer3.start();
    }
}
