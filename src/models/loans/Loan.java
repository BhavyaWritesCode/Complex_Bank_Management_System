package models.loans;

import java.util.UUID;
import java.time.LocalDate;

public abstract class Loan {
    protected String loanId;
    protected double amount;
    protected double interestRate;
    protected int durationMonths;
    protected double monthlyEMI;
    protected LocalDate startDate;

    public Loan(double amount, double interestRate, int durationMonths) {
        this.loanId = UUID.randomUUID().toString();  // genrerating a unique loan id
        this.amount = amount;
        this.interestRate = interestRate;
        this.durationMonths = durationMonths;
        this.startDate = LocalDate.now();
        this.monthlyEMI = calculateEMI();
    }

    
    public abstract String getLoanType();

    // EMI calculation formula
    public double calculateEMI() {
        double monthlyRate = interestRate / 12 / 100;
        double emi = (amount * monthlyRate * Math.pow(1 + monthlyRate, durationMonths)) /
                     (Math.pow(1 + monthlyRate, durationMonths) - 1);
        return Math.round(emi * 100.0) / 100.0;
    }

    
    public String getLoanId() { return loanId; }
    public double getAmount() { return amount; }
    public double getInterestRate() { return interestRate; }
    public int getDurationMonths() { return durationMonths; }
    public double getMonthlyEMI() { return monthlyEMI; }
    public LocalDate getStartDate() { return startDate; }


    public String toFileString(String username) {
        return username + "|" + getLoanType() + "|" + loanId + "|" + amount + "|" + interestRate + "|" + durationMonths + "|" + monthlyEMI + "|" + startDate;
    }
}
