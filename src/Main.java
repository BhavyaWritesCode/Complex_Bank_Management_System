import java.util.Scanner;
import models.User;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser = null;

    public static void main(String[] args) {
        System.out.println("===== Welcome to Bhavya's Bank Management System =====");
        boolean exit = false;

        while (!exit) {
            System.out.println("\n1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    loginUser();
                    if (currentUser != null) {
                        showBankMenu();
                    }
                    break;
                case 3:
                    System.out.println("Exiting... Thank you!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void registerUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter initial deposit (minimum ₹1000): ");
        double balance = scanner.nextDouble();
        scanner.nextLine();

        if (balance < 1000) {
            System.out.println("Minimum ₹1000 required to open account.");
            return;
        }

        User user = new User(username, password, balance);
        boolean success = FileManager.registerUser(user);

        if (success) {
            System.out.println("✅ Registered successfully! Please login.");
        } else {
            System.out.println("⚠️ Username already exists. Try another.");
        }
    }

    private static void loginUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = FileManager.loginUser(username, password);

        if (user != null) {
            currentUser = user;
            System.out.println("✅ Login successful. Welcome, " + currentUser.getUsername() + "!");
            FileManager.logTransaction(currentUser, "User logged in.");
        } else {
            System.out.println("❌ Invalid username or password.");
        }
    }

    private static void showBankMenu() {
        boolean back = false;

        while (!back) {
            System.out.println("\n===== Banking Menu =====");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Apply for Loan");
            System.out.println("5. Apply for Card");
            System.out.println("6. Logout");

            System.out.print("Choose option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (option) {
                case 1:
                    System.out.print("Enter amount to deposit: ₹");
                    double depositAmount = scanner.nextDouble();
                    scanner.nextLine();
                    Bank.deposit(currentUser, depositAmount);
                    break;

                case 2:
                    System.out.print("Enter amount to withdraw: ₹");
                    double withdrawAmount = scanner.nextDouble();
                    scanner.nextLine();
                    Bank.withdraw(currentUser, withdrawAmount);
                    break;

                case 3:
                    System.out.println("💰 Current Balance: ₹" + currentUser.getBalance());
                    break;

                case 4:
                    System.out.println("🔜 Loan feature coming soon.");
                    break;

                case 5:
                    System.out.println("🔜 Card system coming soon.");
                    break;

                case 6:
                    FileManager.logTransaction(currentUser, "User logged out.");
                    System.out.println("👋 Logged out successfully.");
                    currentUser = null;
                    back = true;
                    break;

                default:
                    System.out.println("❌ Invalid choice.");
            }
        }
    }
}