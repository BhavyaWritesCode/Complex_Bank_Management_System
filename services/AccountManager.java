package services;

import models.User;
import utils.FileManager;

import java.util.List;

public class AccountManager {

    private static final String USER_DATA_FILE = "data/users.txt";

    public static boolean register(String username, String password, double initialDeposit) {
        List<User> users = FileManager.readUsers(USER_DATA_FILE);
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                return false; 
            }
        }
        User newUser = new User(username, password, initialDeposit);
        users.add(newUser);
        FileManager.writeUsers(users, USER_DATA_FILE);
        FileManager.createUserLog(username);
        FileManager.writeLog(username, "Account created with ₹" + initialDeposit);
        return true;
    }

    public static User login(String username, String password) {
        List<User> users = FileManager.readUsers(USER_DATA_FILE);
        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                FileManager.writeLog(username, "Logged in");
                return u;
            }
        }
        return null;
    }

    public static boolean deposit(User user, double amount) {
        if (amount <= 0) return false;
        user.setBalance(user.getBalance() + amount);
        updateUser(user);
        FileManager.writeLog(user.getUsername(), "Deposited ₹" + amount);
        return true;
    }

    public static boolean withdraw(User user, double amount) {
        if (amount <= 0 || user.getBalance() < amount) return false;
        user.setBalance(user.getBalance() - amount);
        updateUser(user);
        FileManager.writeLog(user.getUsername(), "Withdrew ₹" + amount);
        return true;
    }

    public static void updateUser(User user) {
        List<User> users = FileManager.readUsers(USER_DATA_FILE);
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(user.getUsername())) {
                users.set(i, user);
                break;
            }
        }
        FileManager.writeUsers(users, USER_DATA_FILE);
    }

    public static void changePassword(User user, String newPassword) {
        user.setPassword(newPassword);
        updateUser(user);
        FileManager.writeLog(user.getUsername(), "Password changed");
    }

    public static double checkBalance(User user) {
        FileManager.writeLog(user.getUsername(), "Checked balance: ₹" + user.getBalance());
        return user.getBalance();
    }
}
