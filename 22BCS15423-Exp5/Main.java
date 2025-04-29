class TicketBookingSystem {
  private int availableSeats = 10; // total seats

  // Synchronized method to prevent double booking
  public synchronized void bookTicket(String customerName, int numberOfSeats) {
      if (availableSeats >= numberOfSeats) {
          System.out.println(customerName + " is booking " + numberOfSeats + " seat(s).");
          availableSeats -= numberOfSeats;
          System.out.println("Booking successful for " + customerName + ". Seats left: " + availableSeats);
      } else {
          System.out.println("Booking failed for " + customerName + ". Not enough seats.");
      }
  }
}

class Customer extends Thread {
  private TicketBookingSystem bookingSystem;
  private String customerName;
  private int seatsToBook;

  public Customer(TicketBookingSystem bookingSystem, String customerName, int seatsToBook) {
      this.bookingSystem = bookingSystem;
      this.customerName = customerName;
      this.seatsToBook = seatsToBook;
      this.setName(customerName);
  }

  @Override
  public void run() {
      bookingSystem.bookTicket(customerName, seatsToBook);
  }
}

public class Main {
  public static void main(String[] args) {
      TicketBookingSystem bookingSystem = new TicketBookingSystem();

      // VIP customer - high priority
      Customer vip1 = new Customer(bookingSystem, "VIP-Customer-1", 3);
      vip1.setPriority(Thread.MAX_PRIORITY);

      // Normal customers - medium priority
      Customer normal1 = new Customer(bookingSystem, "Normal-Customer-1", 4);
      normal1.setPriority(Thread.NORM_PRIORITY);

      Customer normal2 = new Customer(bookingSystem, "Normal-Customer-2", 4);
      normal2.setPriority(Thread.NORM_PRIORITY);

      // VIP customer - high priority
      Customer vip2 = new Customer(bookingSystem, "VIP-Customer-2", 2);
      vip2.setPriority(Thread.MAX_PRIORITY);

      // Start threads
      vip1.start();
      normal1.start();
      normal2.start();
      vip2.start();
  }
}