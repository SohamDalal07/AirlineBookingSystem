import java.util.Scanner;
 
public class PaymentProcessor {

    public static boolean processPayment(Scanner scanner, int amount) {

        System.out.println("\n--- Payment Portal ---");

        System.out.println("Flight Price: ₹" + amount);

        System.out.println("Choose Payment Method:");

        System.out.println("1. UPI");

        System.out.println("2. Credit/Debit Card");

        System.out.println("3. Net Banking");

        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();

        scanner.nextLine();
 
        switch (choice) {

            case 1: return upiPayment(scanner, amount);

            case 2: return simulateCardPayment(scanner);

            case 3: return simulateNetBanking(scanner);

            default:

                System.out.println("Invalid option.");

                return false;

        }

    }
 
    private static boolean upiPayment(Scanner scanner, int amount) {

        System.out.println("\nGenerating UPI QR Code...");

        System.out.println("[###########]");

        System.out.println("[#  UPI QR  #]");

        System.out.println("[# \u20B9" + amount + "   #]");


        System.out.println("[###########]");

        System.out.println("Scan with your UPI app to pay to: airlines@upi");
 
        System.out.print("Enter 'done' once payment is completed: ");

        String confirm = scanner.nextLine();

        return confirm.equalsIgnoreCase("done");

    }
 
    private static boolean simulateCardPayment(Scanner scanner) {

        System.out.print("Enter Card Number (xxxx-xxxx-xxxx-xxxx): ");

        scanner.nextLine(); // Simulate card entry

        System.out.print("Enter Expiry Date (MM/YY): ");

        scanner.nextLine();

        System.out.print("Enter CVV: ");

        scanner.nextLine();

        System.out.println("Processing...");

        return true;

    }
 
    private static boolean simulateNetBanking(Scanner scanner) {

        System.out.print("Enter Bank Name: ");

        scanner.nextLine();

        System.out.print("Enter User ID: ");

        scanner.nextLine();

        System.out.print("Enter Password: ");

        scanner.nextLine();

        System.out.println("Redirecting to bank...");

        return true;

    }

}

 