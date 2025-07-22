package utils;

import java.io.*;
import java.util.*;

import models.User;

public class FileManager {

    private static final String USER_FILE = "data/users.txt";


    public static List<User> loadAllUsers() {
        return readUsers(USER_FILE);
    }

    public static void updateAllUsers(List<User> users) {
        writeUsers(users, USER_FILE);
    }

    public static void saveUser(User user) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USER_FILE, true))) {
            bw.write(user.getUsername() + "|" + user.getPassword() + "|" + user.getBalance());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        createUserLog(user.getUsername());
    }

    public static void updateUser(User updatedUser) {
        List<User> users = loadAllUsers();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(updatedUser.getUsername())) {
                users.set(i, updatedUser);
                break;
            }
        }
        updateAllUsers(users);
    }

    public static void logTransaction(String username, String message) {
        writeLog(username, message);
    }


    public static List<User> readUsers(String filePath) {
        List<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 3) {
                    String username = parts[0];
                    String password = parts[1];
                    double balance = Double.parseDouble(parts[2]);
                    users.add(new User(username, password, balance));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static void writeUsers(List<User> users, String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (User user : users) {
                bw.write(user.getUsername() + "|" + user.getPassword() + "|" + user.getBalance());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createUserLog(String username) {
        try {
            File logDir = new File("data/logs");
            if (!logDir.exists()) {
                logDir.mkdirs();
            }

            File file = new File(logDir, username + "_log.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeLog(String username, String message) {
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter("data/logs/" + username + "_log.txt", true))) {
            bw.write(new Date() + " - " + message);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
