public class Flight {
    private String flightNumber;
    private String callsign;
    private String country;
    private String destination;
    private String departureDate;
    private String returnDate;
    private String departureTime;
    private String arrivalTime;
    private int availableSeats;
    private double price;
    private String originCountry;
    private String destinationCountry;


    public Flight(String flightNumber, String callsign, String country, String destination,
                  String departureDate, String returnDate, String departureTime, String arrivalTime,
                  int availableSeats, double price) {
        this.flightNumber = flightNumber;
        this.callsign = callsign;
        this.country = country;
        this.destination = destination;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.availableSeats = availableSeats;
        this.price = price;
    }
   
public void setDepartureTime(String departureTime) {
    this.departureTime = departureTime;
}

public void setArrivalTime(String arrivalTime) {
    this.arrivalTime = arrivalTime;
}


    public String getFlightNumber() {
        return flightNumber;
    }

    public String getCallsign() {
        return callsign;
    }

    public String getCountry() {
        return country;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

/**
 * Sets the number of available seats for this flight.
 *
 * @param availableSeats the new number of available seats
 */

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public boolean bookSeat() {
        if (availableSeats > 0) {
            availableSeats--;
            return true;
        } else {
            System.out.println("No seats available on this flight.");
            return false;
        }
    }

    public String getDestination() {
        return destination;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public double getPrice() {
        return price;
    }
      public String getOriginCountry() {
    return originCountry;
}

public String getDestinationCountry() {
    return destinationCountry;
}
    @Override
    public String toString() {
        return "Flight{" +
                "flightNumber='" + flightNumber + '\'' +
                ", callsign='" + callsign + '\'' +
                ", country='" + country + '\'' +
                ", destination='" + destination + '\'' +
                ", departureDate='" + departureDate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                ", availableSeats=" + availableSeats +
                ", price=" + price +
                '}';
    }
}
