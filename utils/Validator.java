package utils;

public class Validator {

    public static boolean isValidUsername(String username) {
        return username != null && username.matches("^[a-zA-Z][a-zA-Z0-9_]{4,19}$");
    }

    public static boolean isStrongPassword(String password) {
        return password != null && password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$");
    }

    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$");
    }

    public static boolean isValidPAN(String pan) {
        return pan != null && pan.matches("[A-Z]{5}[0-9]{4}[A-Z]");
    }

    public static boolean isValidAadhar(String aadhar) {
        return aadhar != null && aadhar.matches("\\d{12}");
    }

    public static boolean isValidMobile(String mobile) {
        return mobile != null && mobile.matches("[6-9]\\d{9}");
    }

    public static boolean isValidAmount(double amount) {
        return amount >= 0.0;
    }

    public static boolean isValidInterestRate(double rate) {
        return rate > 0.0 && rate <= 40.0;
    }

    public static boolean isEligibleAge(int age) {
        return age >= 18 && age <= 100;
    }

    public static boolean isValidLoanTerm(int years) {
        return years >= 1 && years <= 30;
    }

    public static boolean isValidCardNumber(String cardNumber) {
        return cardNumber != null && cardNumber.matches("\\d{16}");
    }

    public static boolean isValidCVV(String cvv) {
        return cvv != null && cvv.matches("\\d{3}");
    }

    public static boolean isValidExpiryDate(String expiry) {
        return expiry != null && expiry.matches("^(0[1-9]|1[0-2])\\/([0-9]{2})$");
    }

    public static boolean isValidAccountNumber(String acc) {
        return acc != null && acc.matches("\\d{9,18}");
    }

    public static boolean isValidIFSC(String ifsc) {
        return ifsc != null && ifsc.matches("^[A-Z]{4}0[A-Z0-9]{6}$");
    }

    public static boolean isValidGender(String gender) {
        return gender != null && (gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female") || gender.equalsIgnoreCase("other"));
    }
}
