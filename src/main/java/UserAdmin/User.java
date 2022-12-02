package UserAdmin;

import models.Card;
import models.Category;
import models.Deck;
import models.cardFactory.cardGenerator;

import java.util.ArrayList;
import java.util.Scanner;

public class User implements Comparable<User>{

    private ArrayList<Category> categories;
    private int contributions;
    private int activity;
    private final String username;
    private final String password;

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public User(String username, String password)
    {
        this.username = username;
        this.password = password;
        this.contributions = 0;
        this.activity = 0;
        this.categories = new ArrayList<>();
    }

    public Category createNewCategory(String name)
    {
        Category category = new Category(name);
        return category;
    }

    public Card createCard(Category category)
    {
        //TODO: integrate with javafx
        Scanner in = new Scanner(System.in);
        System.out.println("Enter card type");
        String cardType = in.nextLine();
        System.out.println("Enter question and answer");
        String question  = in.nextLine();
        String answer = in.nextLine();
        in.close();

        return (new cardGenerator()).newCard(question,answer,category,cardType);
    }

    public void revise(){
        //TODO
    }

    public void makeDeckPublic(Deck deck){
        // if deck is already public, don't do anything
        if(deck.isPublic())
            return;

        deck.setPublic(true);

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
        return contributions;
    }

    public int getActivity() {
        return activity;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public int compareTo(User otherUser) {
        // multiplying with -1 since we want to sort in descending order of contributions
        return -1 * Integer.compare(this.getContributions(), otherUser.getContributions());
    }
}
