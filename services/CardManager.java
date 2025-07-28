package services;

import java.util.HashMap;
import java.util.Map;

public class CardManager {

    private static class Card {
        String type;
        String number;
        String pin;
        double balance;

        Card(String type, String number, String pin, double balance) {
            this.type = type;
            this.number = number;
            this.pin = pin;
            this.balance = balance;
        }
    }

    private static final Map<String, Card> cardMap = new HashMap<>();

    public static boolean issueCard(String username, String type, String number, String pin, double balance) {
        if (cardMap.containsKey(username)) {
            System.out.println("User already has a card.");
            return false;
        }

        Card newCard = new Card(type, number, pin, balance);
        cardMap.put(username, newCard);
        return true;
    }

    public static double getCardBalance(String username, String pin) {
        if (!cardMap.containsKey(username)) {
            System.out.println("No card found for user.");
            return -1;
        }

        Card card = cardMap.get(username);
        if (!card.pin.equals(pin)) {
            System.out.println("Incorrect PIN.");
            return -1;
        }

        return card.balance;
    }

    public static boolean validateCard(String username, String pin) {
        if (!cardMap.containsKey(username)) return false;

        Card card = cardMap.get(username);
        return card.pin.equals(pin);
    }

    public static boolean withdraw(String username, String pin, double amount) {
        if (!validateCard(username, pin)) return false;

        Card card = cardMap.get(username);
        if (card.balance < amount) return false;

        card.balance -= amount;
        return true;
    }

    public static boolean deposit(String username, String pin, double amount) {
        if (!validateCard(username, pin)) return false;

        Card card = cardMap.get(username);
        card.balance += amount;
        return true;
    }

    public static String viewCardDetails(String username) {
        if (!cardMap.containsKey(username)) {
            return "No card found for user.";
        }

        Card card = cardMap.get(username);
        return "Type: " + card.type + ", Number: " + card.number + ", Balance: ₹" + card.balance;
    }

    public static boolean updatePin(String username, String oldPin, String newPin) {
        if (!validateCard(username, oldPin)) {
            return false;
        }

        Card card = cardMap.get(username);
        card.pin = newPin;
        return true;
    }
}
