package models;

import UserAdmin.User;
import services.DataService;

import java.util.ArrayList;

public class Deck {
    private int id;
    private String name;
    private boolean isPublic;
    private ArrayList<Card> cards;
    // TODO: user - add getter

    public Deck(String name)
    {
        this(name,false);
    }

    public Deck(String name, boolean isPublic) {
        this.name = name;
        this.isPublic = isPublic;
        this.cards = new ArrayList<Card>();

        if(isPublic) {
            DataService.getInstance().registerPublicDeck(this);
        }
    }

    public int addCard(Card card) {
        // TODO: if deck is public, change contributions
        if(this.cards.add(card)) {
            return card.getId();
        }

        // TODO: throw custom exception
        return -1;
    }

    public void removeCard(Card card) throws Exception {
        this.cards.remove(card);
    }

    public boolean isEmpty() {
        return cards.size() == 0;
    }

    public boolean containsCard(Card card) {
        return this.cards.contains(card);
    }


    public ArrayList<Card> getCards() {
        return this.cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public int getId() {
        return id;
    }

    public int getSize() {
        return this.cards.size();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void makePublic() {
        isPublic = true;
    }

    @Override
    public String toString() {
        return "Deck [name=" + name + ", cards=" + cards + "]";
    }
}
