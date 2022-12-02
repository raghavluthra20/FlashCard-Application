package models;

import java.util.ArrayList;

public class Category{//TODO: id

    private int id;
    private String name;
    private ArrayList<Deck> decks;

    public Category(String name) {
        this.name = name;
        this.decks = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return getName();
    }
}
