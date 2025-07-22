package models.loans;

public class BusinessLoan extends Loan {

    public BusinessLoan(double amount, int durationMonths) {
        super(amount, 13.5, durationMonths);
    }

    @Override
    public String getLoanType() {
        return "Business Loan";
    }
}
