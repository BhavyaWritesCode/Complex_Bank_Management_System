import models.User;
import java.io.*;
import java.util.*;

public class Bank {
    private static final String USER_FILE = "data/users.txt";

    // 💰 Deposit amount
    public static void deposit(User user, double amount) {
        if (amount <= 0) {
            System.out.println("❌ Invalid amount.");
            return;
        }
        double newBalance = user.getBalance() + amount;
        user.setBalance(newBalance);
        updateUserBalance(user);
        FileManager.logTransaction(user, "Deposited ₹" + amount + " | New Balance: ₹" + newBalance);
        System.out.println("✅ ₹" + amount + " deposited successfully.");
    }

    // 💸 Withdraw amount
    public static void withdraw(User user, double amount) {
        if (amount <= 0) {
            System.out.println("❌ Invalid amount.");
            return;
        }

        if (amount > user.getBalance()) {
            System.out.println("❌ Insufficient balance.");
            return;
        }

        double newBalance = user.getBalance() - amount;
        user.setBalance(newBalance);
        updateUserBalance(user);
        FileManager.logTransaction(user, "Withdrew ₹" + amount + " | New Balance: ₹" + newBalance);
        System.out.println("✅ ₹" + amount + " withdrawn successfully.");
    }

    // 📄 Update user balance in users.txt
    private static void updateUserBalance(User user) {
        try {
            File inputFile = new File(USER_FILE);
            File tempFile = new File("data/temp_users.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts[0].equals(user.getUsername())) {
                    writer.write(user.getUsername() + "|" + user.getPassword() + "|" + user.getBalance());
                } else {
                    writer.write(line);
                }
                writer.newLine();
            }

            reader.close();
            writer.close();

            // Replace original file
            inputFile.delete();
            tempFile.renameTo(inputFile);
        } catch (IOException e) {
            System.out.println("❌ Error updating balance: " + e.getMessage());
        }
    }
}
