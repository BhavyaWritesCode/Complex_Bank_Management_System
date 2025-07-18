package models.loans;

public class VehicleLoan extends Loan {

    public VehicleLoan(double amount, int durationMonths) {
        super(amount, 10.0, durationMonths);
    }

    @Override
    public String getLoanType() {
        return "Vehicle Loan";
    }
}
