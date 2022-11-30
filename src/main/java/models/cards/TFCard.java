package models.cards;


import models.Card;
import models.CardType;
import models.Category;

// TODO: Use factory pattern here
public class TFCard extends Card {

    public TFCard(String question, String answer, Category category) {
        super(question, answer, category, CardType.TF);
    }

    public String toString() {
        return "Card{" +
                "type= True/False Card \\" +
                "question='" + this.getQuestion() + '\'' +
                ", answer='" + this.getAnswer() + '\'' +
                '}';
    }


}
