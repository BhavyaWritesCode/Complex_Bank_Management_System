import java.util.Scanner;
import models.User;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static User currentUser = null;

    public static void main(String[] args) {
        System.out.println("------ Welcome to Bhavya Bank ------");

        while (true) {
            System.out.println("\n1. Register\n2. Login\n3. Exit");
            System.out.print("Choose: ");
            int ch = sc.nextInt();
            sc.nextLine();

            if (ch == 1) {
                handleRegister();
            } else if (ch == 2) {
                handleLogin();
            } else {
                System.out.println("Thank you! Visit Again.");
                break;
            }
        }
    }

    private static void handleRegister() {
        System.out.print("Enter username: ");
        String uname = sc.nextLine();
        System.out.print("Enter password: ");
        String pass = sc.nextLine();

        boolean success = Bank.register(uname, pass);
        if (success) {
            System.out.println("✅ Registered successfully!");
        } else {
            System.out.println("❌ Username already exists!");
        }
    }

    private static void handleLogin() {
        System.out.print("Username: ");
        String uname = sc.nextLine();
        System.out.print("Password: ");
        String pass = sc.nextLine();

        currentUser = Bank.login(uname, pass);
        if (currentUser != null) {
            System.out.println("✅ Login successful. Welcome " + currentUser.getUsername());
            userMenu();
        } else {
            System.out.println("❌ Invalid credentials.");
        }
    }

    private static void userMenu() {
        while (true) {
            System.out.println("\n--- User Menu ---");
            System.out.println("1. View Balance\n2. Deposit\n3. Withdraw\n4. Logout");
            System.out.print("Choose: ");
            int ch = sc.nextInt();

            if (ch == 1) {
                System.out.println("💰 Current Balance: ₹" + Bank.getBalance(currentUser));
            } else if (ch == 2) {
                System.out.print("Enter amount to deposit: ");
                double amt = sc.nextDouble();
                Bank.deposit(currentUser, amt);
                System.out.println("✅ Deposited ₹" + amt);
            } else if (ch == 3) {
                System.out.print("Enter amount to withdraw: ");
                double amt = sc.nextDouble();
                boolean success = Bank.withdraw(currentUser, amt);
                if (success) {
                    System.out.println("✅ Withdrawn ₹" + amt);
                } else {
                    System.out.println("❌ Insufficient balance.");
                }
            } else {
                System.out.println("👋 Logged out.");
                break;
            }
        }
    }
}
