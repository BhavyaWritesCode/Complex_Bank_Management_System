package models.cards;

public class CreditCard extends Card {
    private String cvv;
    private String pin;
    private double creditLimit;
    private double usedCredit;
    private double interestRate;  // Monthly interest
    private boolean isBlocked;

    public CreditCard(String cardNumber, String holderName, String cvv, String pin, double salary) {
        super(cardNumber, holderName);
        this.cvv = cvv;
        this.pin = pin;
        this.creditLimit = calculateLimit(salary);
        this.usedCredit = 0.0;
        this.interestRate = 2.5; // 2.5% monthly interest
        this.isBlocked = false;
    }

    @Override
    public String getCardType() {
        return "Credit Card";
    }

    private double calculateLimit(double salary) {
        return Math.min(salary * 4, 200000); // Max ₹2 lakh limit
    }

    public boolean spend(double amount, String enteredPin) {
        if (isBlocked || !this.pin.equals(enteredPin)) return false;
        if (usedCredit + amount > creditLimit) return false;

        usedCredit += amount;
        return true;
    }

    public void repay(double amount) {
        if (amount > 0) usedCredit = Math.max(0, usedCredit - amount);
    }

    public void applyInterest() {
        usedCredit += (usedCredit * interestRate / 100.0);
    }

    public void blockCard() {
        isBlocked = true;
    }

    public void unblockCard() {
        isBlocked = false;
    }

    public double getRemainingLimit() {
        return creditLimit - usedCredit;
    }

    public double getUsedCredit() {
        return usedCredit;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public String getCVV() {
        return cvv;
    }

    @Override
    public String toString() {
        return super.toString() +
               ", CVV: " + cvv +
               ", Limit: ₹" + creditLimit +
               ", Used: ₹" + usedCredit +
               ", Remaining: ₹" + getRemainingLimit() +
               ", Monthly Interest: " + interestRate + "%" +
               ", Status: " + (isBlocked ? "Blocked" : "Active");
    }
}
