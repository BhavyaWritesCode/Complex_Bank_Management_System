package models.cards;

import java.time.LocalDate;

public abstract class Card {
    protected String cardNumber;
    protected String holderName;
    protected LocalDate issueDate;
    protected LocalDate expiryDate;

    public Card(String cardNumber, String holderName) {
        this.cardNumber = cardNumber;
        this.holderName = holderName;
        this.issueDate = LocalDate.now();
        this.expiryDate = issueDate.plusYears(5); // Valid for 5 years
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public abstract String getCardType();

    @Override
    public String toString() {
        return "CardType: " + getCardType() + 
               ", Number: " + cardNumber +
               ", Holder: " + holderName +
               ", Issue Date: " + issueDate +
               ", Expiry Date: " + expiryDate;
    }
}
