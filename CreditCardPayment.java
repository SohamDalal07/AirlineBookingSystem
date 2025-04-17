public class CreditCardPayment extends Payment {
    private String cardNumber;
    private String cardHolder;
    private String expiryDate;

    public CreditCardPayment(double amount, String cardNumber, String cardHolder, String expiryDate) {
        super(amount);
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean processPayment() {
        System.out.println("Processing credit card payment of ₹" + getAmount());
        // Add actual processing logic here
        return true;
    }
}
