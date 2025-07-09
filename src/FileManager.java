import java.io.*;
import java.util.*;
import models.User;

public class FileManager {
    private static final String USER_FILE = "data/users.txt";
    private static final String LOG_FOLDER = "data/logs/";

    // Register User and write to users.txt
    public static boolean registerUser(User user) {
        try {
            // Create folders if they don't exist
            File dataDir = new File("data");
            File logDir = new File(LOG_FOLDER);

            if (!dataDir.exists()) dataDir.mkdir();
            if (!logDir.exists()) logDir.mkdir();

            // Check if user already exists
            if (userExists(user.getUsername())) {
                return false;
            }

            // Append user to file
            FileWriter fw = new FileWriter(USER_FILE, true);
            fw.write(user.toString() + "\n");
            fw.close();

            // Create user log file
            FileWriter log = new FileWriter(LOG_FOLDER + user.getUsername() + "_log.txt", true);
            log.write("[REGISTERED] User created with ₹" + user.getBalance() + "\n");
            log.close();

            return true;
        } catch (IOException e) {
            System.out.println("❌ Error in registerUser(): " + e.getMessage());
            return false;
        }
    }

    // Login user & return User 
    public static User loginUser(String username, String password) {
        try {
            File file = new File(USER_FILE);
            if (!file.exists()) return null;

            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split("\\|");

                if (parts.length >= 3) {
                    String uname = parts[0];
                    String pass = parts[1];
                    double balance = Double.parseDouble(parts[2]);

                    if (uname.equals(username) && pass.equals(password)) {
                        sc.close();
                        return new User(uname, pass, balance);
                    }
                }
            }
            sc.close();
        } catch (IOException e) {
            System.out.println("❌ Error in loginUser(): " + e.getMessage());
        }

        return null;
    }

    // Check if user exists already
    public static boolean userExists(String username) {
        try {
            File file = new File(USER_FILE);
            if (!file.exists()) return false;

            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split("\\|");
                if (parts.length >= 1 && parts[0].equals(username)) {
                    sc.close();
                    return true;
                }
            }
            sc.close();
        } catch (IOException e) {
            System.out.println("❌ Error in userExists(): " + e.getMessage());
        }

        return false;
    }

    // Log user actions into their personal log file
    public static void logTransaction(User user, String message) {
        try {
            FileWriter fw = new FileWriter(LOG_FOLDER + user.getUsername() + "_log.txt", true);
            fw.write("[LOG] " + message + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("❌ Error writing log: " + e.getMessage());
        }
    }
}
