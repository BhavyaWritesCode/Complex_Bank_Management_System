package services;

import java.util.Scanner;

public class CardService {

    private static final Scanner sc = new Scanner(System.in);

    public static void start(String username) {
        while (true) {
            System.out.println("\n--- Card Services ---");
            System.out.println("1. Issue Card");
            System.out.println("2. View Card Details");
            System.out.println("3. Check Balance");
            System.out.println("4. Deposit");
            System.out.println("5. Withdraw");
            System.out.println("6. Update PIN");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Card Type (Debit/Credit): ");
                    String type = sc.nextLine();
                    System.out.print("Enter Card Number: ");
                    String number = sc.nextLine();
                    System.out.print("Set a 4-digit PIN: ");
                    String pin = sc.nextLine();
                    System.out.print("Enter initial balance: ");
                    double balance = sc.nextDouble();
                    sc.nextLine();
                    boolean success = CardManager.issueCard(username, type, number, pin, balance);
                    if (success) System.out.println("Card issued successfully.");
                    else System.out.println("Card already exists.");
                    break;

                case 2:
                    System.out.println(CardManager.viewCardDetails(username));
                    break;

                case 3:
                    System.out.print("Enter PIN: ");
                    String pinToCheck = sc.nextLine();
                    double bal = CardManager.getCardBalance(username, pinToCheck);
                    if (bal >= 0) System.out.println("Card Balance: ₹" + bal);
                    break;

                case 4:
                    System.out.print("Enter PIN: ");
                    String depositPin = sc.nextLine();
                    System.out.print("Enter amount to deposit: ");
                    double depAmount = sc.nextDouble();
                    sc.nextLine();
                    if (CardManager.deposit(username, depositPin, depAmount))
                        System.out.println("Deposit successful.");
                    else System.out.println("Deposit failed.");
                    break;

                case 5:
                    System.out.print("Enter PIN: ");
                    String withdrawPin = sc.nextLine();
                    System.out.print("Enter amount to withdraw: ");
                    double withAmount = sc.nextDouble();
                    sc.nextLine();
                    if (CardManager.withdraw(username, withdrawPin, withAmount))
                        System.out.println("Withdraw successful.");
                    else System.out.println("Withdraw failed.");
                    break;

                case 6:
                    System.out.print("Enter Old PIN: ");
                    String oldPin = sc.nextLine();
                    System.out.print("Enter New PIN: ");
                    String newPin = sc.nextLine();
                    if (CardManager.updatePin(username, oldPin, newPin))
                        System.out.println("PIN updated successfully.");
                    else System.out.println("PIN update failed.");
                    break;

                case 7:
                    System.out.println("Exiting Card Services...");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
