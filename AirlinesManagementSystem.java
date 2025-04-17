import java.util.*;
import java.io.*;
import com.google.gson.*;

public class AirlinesManagementSystem {
    private static final String USERS_FILE = "users.txt";
    private static List<User> users = new ArrayList<>();
    private static List<Flight> flights = new ArrayList<>();
    private static List<Customer> customers = new ArrayList<>();
    private static Notification notification = new Notification();
// private static R notification = new Notification();

    public static void main(String[] args) {
        loadUsersFromFile();

        // Clear previous flights
        FileManager.clearFlights();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Airlines Management System ---");
            System.out.println("1. Admin Login");
            System.out.println("2. Customer Login");
            System.out.println("3. Customer Sign Up");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    adminLogin(scanner);
                    break;
                case 2:
                    customerLogin(scanner);
                    break;
                case 3:
                    customerSignUp(scanner);
                    break;
                case 4:
                    System.out.println("Exiting system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void loadUsersFromFile() {
        File file = new File(USERS_FILE);
        if (!file.exists()) {
            try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
                pw.println("admin,admin123");
                pw.println("customer,customer123");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (BufferedReader br = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] creds = line.split(",");
                if (creds.length == 2) {
                    users.add(new User(creds[0], creds[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveUserToFile(String username, String password) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(USERS_FILE, true))) {
            pw.println(username + "," + password);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void customerSignUp(Scanner scanner) {
        System.out.print("Enter new username: ");
        String username = scanner.nextLine();
        System.out.print("Enter new password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("Username already exists.");
                return;
            }
        }

        users.add(new User(username, password));
        saveUserToFile(username, password);
        System.out.println("Sign up successful.");
    }

    private static void adminLogin(Scanner scanner) {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.authenticate(password)) {
                System.out.println("Admin login successful.");
                adminMenu(scanner);
                return;
            }
        }
        System.out.println("Invalid credentials.");
    }

    private static void adminMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Add Flight");
            System.out.println("2. View Flights");
            System.out.println("3. Update Flight Time");
            System.out.println("4. Delay or Cancel Flight");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Flight Number: ");
                    String flightNumber = scanner.nextLine();
                    System.out.print("Callsign: ");
                    String callsign = scanner.nextLine();
                    System.out.print("Origin Country: ");
                    String originCountry = scanner.nextLine();
                    System.out.print("Destination Country: ");
                    String destinationCountry = scanner.nextLine();
                    System.out.print("Departure Date: ");
                    String departureDate = scanner.nextLine();
                    System.out.print("Return Date (if any): ");
                    String returnDate = scanner.nextLine();
                    System.out.print("Departure Time: ");
                    String departureTime = scanner.nextLine();
                    System.out.print("Arrival Time: ");
                    String arrivalTime = scanner.nextLine();
                    System.out.print("Available Seats: ");
                    int seats = scanner.nextInt();
                    scanner.nextLine();

                    Flight flight = new Flight(flightNumber, callsign, originCountry, destinationCountry,
                            departureDate, returnDate, departureTime, arrivalTime, seats, 0.0);
                    flights.add(flight);
                    FileManager.saveFlights(flights);
                    System.out.println("Flight added.");
                    break;

                case 2:
                    viewAllFlights();
                    break;

                case 3:
                    updateFlightTime(scanner);
                    break;

                case 4:
                    delayOrCancelFlight(scanner);
                    break;

                case 5:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void updateFlightTime(Scanner scanner) {
        System.out.print("Enter Flight Number to update: ");
        String flightNum = scanner.nextLine();
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equalsIgnoreCase(flightNum)) {
                System.out.print("New Departure Time: ");
                String newDep = scanner.nextLine();
                System.out.print("New Arrival Time: ");
                String newArr = scanner.nextLine();
                flight.setDepartureTime(newDep);
                flight.setArrivalTime(newArr);
                FileManager.saveFlights(flights);
                System.out.println("Flight time updated.");
                return;
            }
        }
        System.out.println("Flight not found.");
    }

    private static void delayOrCancelFlight(Scanner scanner) {
        System.out.print("Enter Flight Number: ");
        String flightNum = scanner.nextLine();
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equalsIgnoreCase(flightNum)) {
                System.out.println("1. Delay Flight\n2. Cancel Flight");
                System.out.print("Enter choice: ");
                int option = scanner.nextInt();
                scanner.nextLine();

                if (option == 1) {
                    System.out.print("Enter delay in minutes: ");
                    int delay = scanner.nextInt();
                    scanner.nextLine();
                    flight.setDepartureTime(flight.getDepartureTime() + " (Delayed by " + delay + " mins)");
                    FileManager.saveFlights(flights);
                    System.out.println("Flight marked as delayed.");
                } else if (option == 2) {
                    flights.remove(flight);
                    FileManager.saveFlights(flights);
                    System.out.println("Flight cancelled.");
                } else {
                    System.out.println("Invalid choice.");
                }
                return;
            }
        }
        System.out.println("Flight not found.");
    }

    private static void customerLogin(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.authenticate(password)) {
                System.out.println("Customer login successful.");

                System.out.print("Departure Country: ");
                String origin = scanner.nextLine();
                System.out.print("Destination Country: ");
                String destination = scanner.nextLine();
                System.out.print("Departure Date (YYYY-MM-DD): ");
                String depDate = scanner.nextLine();
                System.out.print("Trip Type (1 = One-Way, 2 = Return): ");
                int tripChoice = scanner.nextInt();
                scanner.nextLine();
                boolean isReturn = tripChoice == 2;

                try {
                    List<Flight> filteredFlights = OpenSkyFetcher.fetchFilteredFlights(origin, destination, depDate, isReturn);
                    FileManager.saveFlights(filteredFlights);
                    flights = filteredFlights;
                    System.out.println("Flights loaded successfully.");
                    customerMenu(scanner);
                } catch (Exception e) {
                    System.out.println("Error fetching filtered flights: " + e.getMessage());
                }
                return;
            }
        }
        System.out.println("Invalid credentials.");
    }

    private static void customerMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Customer Menu ---");
            System.out.println("1. View Flights");
            System.out.println("2. Make Reservation");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewAllFlights();
                    break;
                case 2:
                    makeReservation(scanner);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void viewAllFlights() {
        if (flights.isEmpty()) {
            System.out.println("No flights available.");
        } else {
            System.out.println("\n--- Available Flights ---");
            for (Flight f : flights) {
                System.out.println(f);
            }
        }
    }

    private static void makeReservation(Scanner scanner) {

    System.out.print("Enter your name: ");
    String name = scanner.nextLine();

    System.out.print("Enter your email: ");
    String email = scanner.nextLine();

    Customer customer = new Customer(name, email);
    customers.add(customer);

    System.out.print("Enter Flight Number to reserve: ");
    String flightNumber = scanner.nextLine();

    Flight selectedFlight = null;
    for (Flight flight : flights) {
        if (flight.getFlightNumber().equalsIgnoreCase(flightNumber)) {
            selectedFlight = flight;
            break;
        }
    }

    // Check if flight was found
    if (selectedFlight == null) {
        System.out.println(" Flight not found.");
        return;
    }

    // ✅ Create the Reservation object here
    Reservation reservation = new Reservation(selectedFlight, customer);


    if (reservation.reserveSeat()) {
        double price = 4975.00; // or selectedFlight.getEstimatedPrice() if available

        String upiUrl = UpiPaymentUrlGenerator.generateUpiUrl(
            "airlines@upi", "Airlines Booking", price, "Flight Reservation"
        );

        System.out.println("\nReservation pending payment...");
        System.out.println("Scan this UPI link in your UPI app to complete payment:");
        System.out.println(upiUrl);

        System.out.print("Enter 'done' once payment is completed: ");
        String paymentStatus = scanner.nextLine();

        if (paymentStatus.equalsIgnoreCase("done")) {
            System.out.println("✅ Payment successful. Reservation confirmed.");
            notification.sendNotification("Reservation successful", customer.getEmail());
        } else {
            System.out.println(" Payment not completed. Reservation not confirmed.");
        }
    } else {
        System.out.println(" No seats available on this flight.");
    }
}


 

}

 

