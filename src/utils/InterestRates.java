package utils;

import java.util.HashMap;
import java.util.Map;

public class InterestRates {

    private static final Map<String, Double> loanRates = new HashMap<>();
    private static final Map<String, Double> savingsRates = new HashMap<>();

    static {
        loanRates.put("HomeLoan", 8.5);
        loanRates.put("EduLoan", 5.5);
        loanRates.put("PersonalLoan", 13.0);
        loanRates.put("CarLoan", 9.0);
        loanRates.put("GoldLoan", 11.0);

        savingsRates.put("Savings", 3.5);
        savingsRates.put("FixedDeposit", 6.8);
        savingsRates.put("RecurringDeposit", 6.2);
    }

    public static double getLoanInterestRate(String loanType) {
        return loanRates.getOrDefault(loanType, 0.0);
    }

    public static double getSavingsInterestRate(String type) {
        return savingsRates.getOrDefault(type, 0.0);
    }

    public static void updateLoanInterestRate(String loanType, double rate) {
        if (rate > 0) {
            loanRates.put(loanType, rate);
        }
    }

    public static void updateSavingsInterestRate(String type, double rate) {
        if (rate > 0) {
            savingsRates.put(type, rate);
        }
    }

    public static Map<String, Double> getAllLoanRates() {
        return new HashMap<>(loanRates);
    }

    public static Map<String, Double> getAllSavingsRates() {
        return new HashMap<>(savingsRates);
    }
}
