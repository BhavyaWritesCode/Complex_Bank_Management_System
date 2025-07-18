package services;

import models.loans.*;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class LoanManager {

    private Scanner sc = new Scanner(System.in);
    private LoanService loanService = new LoanService();

    public void applyForLoan(String username) {
        System.out.println("\n🔹 Apply for a Loan 🔹");
        System.out.println("1. Education Loan");
        System.out.println("2. Home Loan");
        System.out.println("3. Personal Loan");
        System.out.println("4. Vehicle Loan");
        System.out.println("5. Business Loan");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine(); // clear buffer

        String loanType = getLoanTypeFromChoice(choice);
        if (loanType == null) {
            System.out.println("❌ Invalid option.");
            return;
        }

        System.out.print("Enter loan amount (₹): ");
        double amount = sc.nextDouble();

        System.out.print("Enter duration (in months): ");
        int duration = sc.nextInt();

        // eligibility check
        if (!loanService.isEligible(loanType, amount, duration)) {
            System.out.println("❌ You are not eligible for this loan.");
            return;
        }

        // Create loan
        Loan loan = loanService.createLoan(loanType, amount, duration);
        if (loan == null) {
            System.out.println("❌ Error creating loan.");
            return;
        }

        double emi = loan.getMonthlyEMI();

        
        System.out.printf("✅ Loan Approved: %s%n", loan.getLoanType());
        System.out.printf("📅 Duration: %d months%n", duration);
        System.out.printf("💰 Monthly EMI: ₹%.2f%n", emi);

        saveLoanToFile(username, loan, emi);
    }

    private String getLoanTypeFromChoice(int choice) {
        return switch (choice) {
            case 1 -> "education";
            case 2 -> "home";
            case 3 -> "personal";
            case 4 -> "vehicle";
            case 5 -> "business";
            default -> null;
        };
    }

    private void saveLoanToFile(String username, Loan loan, double emi) {
        try (FileWriter writer = new FileWriter("data/loans.txt", true)) {
            writer.write("User: " + username + "\n");
            writer.write("Loan Type: " + loan.getLoanType() + "\n");
            writer.write("Amount: ₹" + loan.getAmount() + "\n");
            writer.write("Interest: " + loan.getInterestRate() + "%\n");
            writer.write("Duration: " + loan.getDurationMonths() + " months\n");
            writer.write("EMI: ₹" + String.format("%.2f", emi) + "\n");
            writer.write("Applied On: " + LocalDateTime.now() + "\n");
            writer.write("-------------------------------\n");
        } catch (IOException e) {
            System.out.println("❌ Error saving loan info.");
        }
    }
}
