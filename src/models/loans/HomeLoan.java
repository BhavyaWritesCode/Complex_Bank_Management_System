package models.loans;

public class HomeLoan extends Loan{

    public HomeLoan(double amount, int durationMonths){

        super(amount, 7.5, durationMonths); 

    }

    @Override
    public String getLoanType() {
        return "Home Loan";
    }
    
}
