package ticketbooking;

class TicketCounter {
 private int availableSeats = 10;

 public synchronized void bookSeat(String customerName, int numberOfSeats) {
     if (numberOfSeats <= availableSeats) {
         System.out.println(customerName + " booked " + numberOfSeats + " seat(s).");
         availableSeats -= numberOfSeats;
     } else {
         System.out.println("Sorry " + customerName + ", not enough seats available.");
     }
 }
}

class BookingThread extends Thread {
 private TicketCounter ticketCounter;
 private String customerName;
 private int numberOfSeats;

 public BookingThread(TicketCounter ticketCounter, String customerName, int numberOfSeats, int priority) {
     this.ticketCounter = ticketCounter;
     this.customerName = customerName;
     this.numberOfSeats = numberOfSeats;
     this.setPriority(priority);
 }

 @Override
 public void run() {
     ticketCounter.bookSeat(customerName, numberOfSeats);
 }
}

public class TicketBookingSystem {
 public static void main(String[] args) {
     TicketCounter counter = new TicketCounter();

     BookingThread vipCustomer = new BookingThread(counter, "VIP_Customer", 2, Thread.MAX_PRIORITY);
     BookingThread normalCustomer1 = new BookingThread(counter, "Normal_Customer_1", 4, Thread.NORM_PRIORITY);
     BookingThread normalCustomer2 = new BookingThread(counter, "Normal_Customer_2", 5, Thread.NORM_PRIORITY);

     vipCustomer.start();
     normalCustomer1.start();
     normalCustomer2.start();
 }
}
