package services;

import models.User;
import utils.InterestRates;
import utils.FileManager;

import java.time.LocalDate;
import java.util.List;

public class InterestManager {

    private static final String SUMMARY_LOG_PATH = "data/logs/interest_summary.txt";
    private static final double MIN_BALANCE = 1000.0;

    public void applyInterest(List<User> users) {
        int creditedUsers = 0;
        double totalInterest = 0;
        StringBuilder summary = new StringBuilder();

        for (User user : users) {
            if (user == null || user.getAccountType() == null) continue;

            double balance = user.getBalance();
            if (balance < MIN_BALANCE) continue;

            double rate = InterestRates.getRate(user.getAccountType());
            double interest = (balance * rate) / 100;
            user.setBalance(balance + interest);

            creditedUsers++;
            totalInterest += interest;

            String log = LocalDate.now() + " - ₹" + String.format("%.2f", interest)
                    + " credited to " + user.getUsername()
                    + " (" + user.getAccountType() + ")";

            String logFilePath = "data/logs/interest_" + user.getUsername() + ".txt";
            FileManager.appendToFile(logFilePath, log);
        }

        summary.append("Date: ").append(LocalDate.now()).append("\n");
        summary.append("Users Credited: ").append(creditedUsers).append("\n");
        summary.append("Total Interest Given: ₹").append(String.format("%.2f", totalInterest)).append("\n");
        summary.append("--------\n");

        FileManager.appendToFile(SUMMARY_LOG_PATH, summary.toString());
    }
}
