package services;

import UserAdmin.User;

import java.util.*;

public class DataService {
    private static DataService instance;
    private HashMap<String, String> userData;
    private SortedSet<User> userList;
    private DataService() {
        userData = new HashMap<>();
        userList = new TreeSet<>();

        userData.put("TestUser", "testing321");
        userList.add(new User("TestUser", "testing321"));
    }

    synchronized public static DataService getInstance() {
        if(instance == null)
            instance = new DataService();

        return instance;
    }

    public boolean checkLoginCredentials(String username, String password) {
        return Objects.equals(userData.get(username), password);
    }

    public void addUser(String username, String password) {
        if(!userData.containsKey(username)) {
            userData.put(username, password);
            User user = new User(username, password);
            userList.add(user);
        } else {
            System.out.println("Username already taken.");
        }
    }

    public ArrayList<User> getTopContributors() {
        int contributorSize = 5;

        if(userList.size() < contributorSize)
            return new ArrayList<User>(userList);


        ArrayList<User> topContributors = new ArrayList<>();
        for (User user : userList) {
            if (topContributors.size() >= contributorSize)
                break;
            topContributors.add(user);
        }
        return topContributors;
    }
}
