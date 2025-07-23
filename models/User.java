package models;

public class User {
    private String username;
    private String password;
    private double balance;
    private String accountType;

    public User(String username, String password, double balance) {
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.accountType = "savings"; 
    }

    public User(String username, String password, double balance, String accountType) {
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.accountType = accountType;
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

    public String getAccountType() {
        return accountType;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    // For file saving
    public String toFileString() {
        return username + "|" + password + "|" + balance + "|" + accountType;
    }

    public static User fromFileString(String line) {
        String[] parts = line.split("\\|");
        String username = parts[0];
        String password = parts[1];
        double balance = Double.parseDouble(parts[2]);
        String accountType = parts.length > 3 ? parts[3] : "savings"; 
        return new User(username, password, balance, accountType);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", balance=₹" + balance +
                ", accountType='" + accountType + '\'' +
                '}';
    }
}
