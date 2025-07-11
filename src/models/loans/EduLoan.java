package models.loans;

public class EduLoan extends Loan{
    public EduLoan(double amount, int durationMonths) {
        super(amount,8.0, durationMonths); 
    }

    @Override
    public String getLoanType() {
        return "Education Loan";
    }
    
}
