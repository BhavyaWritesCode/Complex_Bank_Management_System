package models.loans;

public class PersonalLoan extends Loan {

    public PersonalLoan(double amount, int durationMonths) {
        
        super(amount, 12.0, durationMonths);
    }

    @Override
    public String getLoanType() {
        return "Personal Loan";
    }
}
