package services;

import UserAdmin.User;

import java.util.*;

public class DataService {
    private static DataService instance;
    private HashMap<String, String> userData;
    private HashSet<User> userList;
    private DataService() {
        userData = new HashMap<>();
        userList = new HashSet<>();

        userData.put("TestUser", "testing321");
        userList.add(new User("TestUser", "testing321"));
    }

    synchronized public static DataService getInstance() {
        if(instance == null)
            instance = new DataService();

        return instance;
    }

    // checks if user exists or not and if password and username match
    public boolean checkLoginCredentials(String username, String password) {
        if(!userData.containsKey(username))
            return false;

        return userData.get(username).equals(password);
    }

    public boolean registerUser(String username, String password) {
        if(userData.containsKey(username)) {
            System.out.println("Username already taken.");
            return false;
        }

        User user = new User(username, password);
        userList.add(user);
        userData.put(username, password);
        return true;
    }

    public User findUser(String username) {
        for(User user : userList) {
            if(user.getUsername().equals(username))
                return user;
        }
        System.out.println(userList);
        return null;
    }
    public ArrayList<User> getTopContributors() {
        int contributorSize = 5;

        // create a list to users from hashset
        ArrayList<User> userArrayList = new ArrayList<>(userList);
        Collections.sort(userArrayList);

        if(userList.size() < contributorSize)
            return userArrayList;

        ArrayList<User> topContributors = new ArrayList<>();
        for (User user : userArrayList) {
            if (topContributors.size() >= contributorSize)
                break;
            topContributors.add(user);
        }
        return topContributors;
    }
}
