public class Reservation {

    private Flight flight;

    private Customer customer;
 
    public Reservation(Flight flight, Customer customer) {

        this.flight = flight;

        this.customer = customer;

    }
 
    public boolean reserveSeat() {

        if (flight.getAvailableSeats() > 0) {

            flight.setAvailableSeats(flight.getAvailableSeats() - 1);

            System.out.println("Seat reserved for " + customer.getName());

            return true;

        } else {

            System.out.println("No available seats on flight " + flight.getFlightNumber());

            return false;

        }

    }
 
    public Flight getFlight() {

        return flight;

    }
 
    public Customer getCustomer() {

        return customer;

    }

}

 