package services;

import java.util.HashMap;
import java.util.Objects;

public class DataService {
    private static HashMap<String, String> userData;
    public DataService() {
        userData = new HashMap<>();
        userData.put("TestUser", "testing321");
    }

    public static boolean checkLoginCredentials(String username, String password) {
        return Objects.equals(userData.get(username), password);
    }

    public static void addUser(String username, String password) {
        if(!userData.containsKey(username)) {
            userData.put(username, password);
        } else {
            System.out.println("Username already taken.");
        }
    }

}
