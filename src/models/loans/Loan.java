package models.loans;
import java.util.UUID;
import java.time.LocalDate;

public abstract class Loan {
    protected String loanID;
    protected double amount;
    protected double interestRate;
    protected int durationMonths;
    protected double monthlyEMI;
    protected LocalDate startDate;

    public Loan(double amount, double interestRate, int durationMonths){
        this.loanID - UUID.randomUUID().toString();
        this.amount = amount;
        this.interestRate = interestRate;
        this.durationMonths = durationMonths;
        this.startDate = LocalDate.now();
        this.monthlyEMI = calculateEMI();
    }

    public abstract String getLoanType();
    
}
