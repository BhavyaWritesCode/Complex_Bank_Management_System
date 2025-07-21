import java.util.HashMap;
import java.util.Map;
import models.User;
import utils.FileManager;

public class Bank {
    private static Map<String, User> userMap = new HashMap<>();

    public static boolean register(String username, String password) {
        if (userMap.containsKey(username)) return false;

        User user = new User(username, password, 0.0);
        userMap.put(username, user);
        FileManager.saveUser(user); // File me bhi save
        return true;
    }

    public static User login(String username, String password) {
        if (!userMap.containsKey(username)) {
            // Convert List<User> to Map<String, User> before putAll
            for (User u : FileManager.loadAllUsers()) {
                userMap.put(u.getUsername(), u);
            }
        }

        User user = userMap.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public static void deposit(User user, double amount) {
        double updated = user.getBalance() + amount;
        user.setBalance(updated);
        FileManager.updateUser(user);
        FileManager.logTransaction(user.getUsername(), "Deposited ₹" + amount);
    }

    public static boolean withdraw(User user, double amount) {
        if (user.getBalance() >= amount) {
            double updated = user.getBalance() - amount;
            user.setBalance(updated);
            FileManager.updateUser(user);
            FileManager.logTransaction(user.getUsername(), "Withdrew ₹" + amount);
            return true;
        }
        return false;
    }

    public static double getBalance(User user) {
        return user.getBalance();
    }
}
