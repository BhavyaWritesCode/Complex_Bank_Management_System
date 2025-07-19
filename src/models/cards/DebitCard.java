package models.cards;

public class DebitCard extends Card {
    private String cvv;
    private String pin;
    private boolean isActive;
    private double dailyLimit;
    private double dailyUsed;
    private double linkedBalance; // Current available balance

    public DebitCard(String cardNumber, String holderName, String cvv, String pin, double linkedBalance) {
        super(cardNumber, holderName);
        this.cvv = cvv;
        this.pin = pin;
        this.linkedBalance = linkedBalance;
        this.isActive = true;
        this.dailyLimit = 25000.0;
        this.dailyUsed = 0.0;
    }

    @Override
    public String getCardType() {
        return "Debit Card";
    }

    public boolean withdraw(double amount, String enteredPin) {
        if (!isActive || !this.pin.equals(enteredPin)) return false;
        if (amount > linkedBalance || (dailyUsed + amount) > dailyLimit) return false;

        linkedBalance -= amount;
        dailyUsed += amount;
        return true;
    }

    public void resetDailyUsage() {
        dailyUsed = 0;
    }

    public void deactivate() {
        isActive = false;
    }

    public void activate() {
        isActive = true;
    }

    public String getCVV() {
        return cvv;
    }

    public double getBalance() {
        return linkedBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) linkedBalance += amount;
    }

    @Override
    public String toString() {
        return super.toString() +
               ", CVV: " + cvv +
               ", Balance: ₹" + linkedBalance +
               ", Daily Used: ₹" + dailyUsed +
               ", Daily Limit: ₹" + dailyLimit +
               ", Active: " + (isActive ? "Yes" : "No");
    }
}
