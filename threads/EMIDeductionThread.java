package threads;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class EMIDeductionThread extends Thread {

    private final File loansFile = new File("data/loans.txt");
    private final File usersFile = new File("data/users.txt");

    @Override
    public void run() {
        while (true) {
            try {
                deductMonthlyEMIs();
                TimeUnit.DAYS.sleep(30); // monthly execution
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    private synchronized void deductMonthlyEMIs() {
        Map<String, Double> userBalances = new HashMap<>();
        List<String> updatedLoans = new ArrayList<>();

        try (BufferedReader userReader = new BufferedReader(new FileReader(usersFile))) {
            String line;
            while ((line = userReader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 3) {
                    userBalances.put(parts[0], Double.parseDouble(parts[2]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try (BufferedReader loanReader = new BufferedReader(new FileReader(loansFile))) {
            String line;
            while ((line = loanReader.readLine()) != null) {
                String[] parts = line.split("\\|");

                if (parts.length < 6) continue;

                String username = parts[1];
                double emi = Double.parseDouble(parts[4]);
                int monthsRemaining = Integer.parseInt(parts[5]);

                if (monthsRemaining <= 0) {
                    updatedLoans.add(line); // loan finished, keep unchanged
                    continue;
                }

                if (userBalances.containsKey(username)) {
                    double balance = userBalances.get(username);
                    if (balance >= emi) {
                        balance -= emi;
                        userBalances.put(username, balance);
                        monthsRemaining--;
                    }
                }

                parts[5] = String.valueOf(monthsRemaining);
                updatedLoans.add(String.join("|", parts));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try (BufferedWriter loanWriter = new BufferedWriter(new FileWriter(loansFile))) {
            for (String loan : updatedLoans) {
                loanWriter.write(loan);
                loanWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(usersFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter("data/tmp_users.txt"))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 3 && userBalances.containsKey(parts[0])) {
                    writer.write(parts[0] + "|" + parts[1] + "|" + userBalances.get(parts[0]) + "|" + parts[3]);
                    writer.newLine();
                } else {
                    writer.write(line);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        new File("data/tmp_users.txt").renameTo(usersFile);
    }
}
