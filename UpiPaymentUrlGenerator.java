public class UpiPaymentUrlGenerator {
 
    public static String generateUpiUrl(String payeeUpiId, String payeeName, double amount, String transactionNote) {

        return "upi://pay?" +

                "pa=" + payeeUpiId +

                "&pn=" + payeeName.replaceAll(" ", "%20") +

                "&am=" + amount +

                "&cu=INR" +

                "&tn=" + transactionNote.replaceAll(" ", "%20");

    }

}

 