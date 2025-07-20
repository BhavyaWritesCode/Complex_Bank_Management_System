package models;

public class User {
    private String username;
    private String password;
    private double balance;

    // Future use: card or loan IDs if needed
    // private List<Loan> loans;
    // private List<Card> cards;

    public User(String username, String password, double balance) {
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    // Setters
    public void setPassword(String password) {
        this.password = password;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // For file saving as a string (users.txt)
    public String toFileString() {
        return username + "|" + password + "|" + balance;
    }

    // From file (users.txt) to object
    public static User fromFileString(String line) {
        String[] parts = line.split("\\|");
        String username = parts[0];
        String password = parts[1];
        double balance = Double.parseDouble(parts[2]);
        return new User(username, password, balance);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", balance=₹" + balance +
                '}';
    }
}
