package UserAdmin;

import models.Card;
import models.Deck;
import services.AdminService;

import java.util.ArrayList;

public class User implements Comparable<User> {
    private int contributions;
    private int activity;
    private final String username;
    private final String password;
    private boolean isLoggedIn;
    private ArrayList<Deck> decks;

    public User(String username, String password)
    {
        this.username = username;
        this.password = password;
        this.contributions = 0;
        this.activity = 0;
        this.decks = new ArrayList<>();
    }

    public void makeDeckPublic(Deck deck){
        // if deck is already public, don't do anything
        if(deck.isPublic())
            return;

        deck.makePublic();
        AdminService.getInstance().registerPublicDeck(deck);

        // update contributions
        contributions = contributions + deck.getSize();
    }

    public void deleteCard(Deck deck, Card card){
        try {
            deck.removeCard(card);
        } catch (Exception e) {//TODO:custom exception
            //TODO
        }
    }
    public void updateCard(Card card)
    {
        //TODO: set answer set question
    }



    public int getContributions() {
        int count = 0;
        for(Deck deck : decks) {
            if(deck.isPublic())
                count = count + deck.getSize();
        }
        this.contributions = count;

        System.out.println("user= " + username + ", contributions= " + contributions);
        return contributions;
    }

    public int getActivity() {
        return activity;
    }

    public void incrementActivity() {
        this.activity = this.activity + 1;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public ArrayList<Deck> getDecks() {
        return this.decks;
    }

    public boolean addNewDeck(Deck deck) {
        for(Deck d : decks)
        {
            if(d.getCategory().getName().equals(deck.getCategory().getName()) && d.getName().equals(deck.getName())){
                return false;
            }
        }
        decks.add(deck);
        return true;
    }

    public int getNumberOfPublicCards() {
        int count = 0;
        for(Deck deck : decks) {
            if(deck.isPublic())
                count = count + deck.getSize();
        }

        return count;
    }

    @Override
    public String toString() {
        return "User [username= " + getUsername() + ", password= " + getPassword() + "]";
    }

    @Override
    public int compareTo(User otherUser) {
        // multiplying with -1 since we want to sort in descending order of contributions
        return -1 * Integer.compare(this.getContributions(), otherUser.getContributions());
    }
}