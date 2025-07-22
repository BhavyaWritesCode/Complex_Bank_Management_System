package threads;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import utils.InterestRates;

public class InterestThread extends Thread {

    private final File usersFile = new File("data/users.txt");
    private final File interestLogFile = new File("data/interest.txt");

    @Override
    public void run() {
        while (true) {
            try {
                applyMonthlyInterest();
                TimeUnit.DAYS.sleep(30); // Wait for a month
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    private synchronized void applyMonthlyInterest() {
        List<String> updatedUsers = new ArrayList<>();
        List<String> interestLogs = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(usersFile))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");

                if (parts.length < 4) continue;

                String username = parts[0];
                String password = parts[1];
                double balance = Double.parseDouble(parts[2]);
                String accountType = parts[3];

                double interestRate = InterestRates.getSavingsInterestRate(accountType);
                double interestAmount = (balance * interestRate) / 100;

                double newBalance = balance + interestAmount;

                updatedUsers.add(username + "|" + password + "|" + newBalance + "|" + accountType);
                interestLogs.add(username + " received ₹" + String.format("%.2f", interestAmount) + " interest at " + new Date());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(usersFile))) {
            for (String user : updatedUsers) {
                writer.write(user);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter logWriter = new BufferedWriter(new FileWriter(interestLogFile, true))) {
            for (String log : interestLogs) {
                logWriter.write(log);
                logWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
