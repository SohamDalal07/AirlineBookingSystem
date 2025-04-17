public class WalletPayment extends Payment {
    private String walletId;

    public WalletPayment(double amount, String walletId) {
        super(amount);
        this.walletId = walletId;
    }

    @Override
    public boolean processPayment() {
        System.out.println("Processing wallet payment of ₹" + getAmount() + " from Wallet ID: " + walletId);
        // Add actual processing logic here
        return true;
    }
}
