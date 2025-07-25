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

    public static double getRate(String type) {
        if (loanRates.containsKey(type)) return loanRates.get(type);
        if (savingsRates.containsKey(type)) return savingsRates.get(type);
        return 0.0;
    }

    public static void updateRate(String type, double rate) {
        if (rate <= 0) return;
        if (loanRates.containsKey(type)) {
            loanRates.put(type, rate);
        } else if (savingsRates.containsKey(type)) {
            savingsRates.put(type, rate);
        }
    }

    public static Map<String, Double> getAllRates() {
        Map<String, Double> allRates = new HashMap<>();
        allRates.putAll(loanRates);
        allRates.putAll(savingsRates);
        return allRates;
    }

    public static boolean isLoanType(String type) {
        return loanRates.containsKey(type);
    }

    public static boolean isSavingsType(String type) {
        return savingsRates.containsKey(type);
    }


    public static double getLoanInterestRate(String loanType) {
        return loanRates.getOrDefault(loanType, 0.0);
    }

    public static double getSavingsInterestRate(String savingsType) {
        return savingsRates.getOrDefault(savingsType, 0.0);
    }
}
