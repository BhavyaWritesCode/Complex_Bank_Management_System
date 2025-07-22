package services;

import models.loans.*;

public class LoanService {

    public Loan createLoan(String type, double amount, int durationMonths) {
        switch (type.toLowerCase()) {
            case "education":
                return new EduLoan(amount, durationMonths);
            case "home":
                return new HomeLoan(amount, durationMonths);
            case "personal":
                return new PersonalLoan(amount, durationMonths);
            case "vehicle":
                return new VehicleLoan(amount, durationMonths);
            case "business":
                return new BusinessLoan(amount, durationMonths);
            default:
                return null;
        }
    }

    public boolean isEligible(String loanType, double amount, int durationMonths) {
        // You can make complex eligibility logic here based on credit score, salary etc.
        // For now, dummy logic:
        return amount >= 1000 && durationMonths >= 6;
    }
}
