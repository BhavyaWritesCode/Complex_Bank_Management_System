package services;

import java.io.*;
import java.util.*;

public class CardManager {

    private static final File cardFile = new File("data/cards.txt");


    public static void issueCard(String username, String cardType, String cardNumber, String pin, double balance) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(cardFile, true))) {
            writer.write(username + "|" + cardType + "|" + cardNumber + "|" + pin + "|" + balance);
            writer.newLine();
            System.out.println( cardType + " card issued to " + username);
        } catch (IOException e) {
            System.out.println("Error issuing card: " + e.getMessage());
        }
    }

    public static void updatePin(String username, String cardNumber, String newPin) {
        List<String> updatedLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(cardFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5 && parts[0].equals(username) && parts[2].equals(cardNumber)) {
                    parts[3] = newPin;
                    line = String.join("|", parts);
                }
                updatedLines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading card file: " + e.getMessage());
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(cardFile))) {
            for (String updated : updatedLines) {
                writer.write(updated);
                writer.newLine();
            }
            System.out.println(" PIN updated successfully.");
        } catch (IOException e) {
            System.out.println(" Error updating card file: " + e.getMessage());
        }
    }

    public static void viewCardDetails(String username) {
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(cardFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5 && parts[0].equals(username)) {
                    System.out.println(" Card Type: " + parts[1]);
                    System.out.println(" Card Number: " + parts[2]);
                    System.out.println(" PIN: " + parts[3]);
                    System.out.println(" Linked Balance: ₹" + parts[4]);
                    System.out.println("-------------------------");
                    found = true;
                }
            }
            if (!found) {
                System.out.println(" No card found for user: " + username);
            }
        } catch (IOException e) {
            System.out.println(" Error reading card details: " + e.getMessage());
        }
    }

    public static double getCardBalance(String username, String cardNumber) {
        try (BufferedReader reader = new BufferedReader(new FileReader(cardFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5 && parts[0].equals(username) && parts[2].equals(cardNumber)) {
                    return Double.parseDouble(parts[4]);
                }
            }
        } catch (IOException e) {
            System.out.println(" Error reading card file: " + e.getMessage());
        }
        return 0.0;
    }

    public static boolean isCardExists(String cardNumber) {
        try (BufferedReader reader = new BufferedReader(new FileReader(cardFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 3 && parts[2].equals(cardNumber)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println(" Error checking card: " + e.getMessage());
        }
        return false;
    }
}
