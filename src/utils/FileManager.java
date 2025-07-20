package utils;

import models.User;

import java.io.*;
import java.util.*;

public class FileManager {
    private static final String USER_DIR = "data/users/";
    private static final String LOG_DIR = "data/logs/";

    static {
        new File(USER_DIR).mkdirs();
        new File(LOG_DIR).mkdirs();
    }

    public static void saveUser(User user) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USER_DIR + user.getUsername() + ".dat"))) {
            oos.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, User> loadAllUsers() {
        Map<String, User> userMap = new HashMap<>();
        File folder = new File(USER_DIR);
        for (File file : folder.listFiles()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                User user = (User) ois.readObject();
                userMap.put(user.getUsername(), user);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return userMap;
    }

    public static void updateUser(User user) {
        saveUser(user);
    }

    public static void logTransaction(String username, String message) {
        try (FileWriter fw = new FileWriter(LOG_DIR + username + "_log.txt", true)) {
            fw.write("[" + new Date() + "] " + message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
