package models.cardFactory;


import models.Card;
import models.Category;
import models.cards.DefCard;
import models.cards.FIBCard;
import models.cards.MCQCard;
import models.cards.TFCard;

public class cardGenerator {
    public Card newCard(String question, String answer, Category category, String cardType)
    {
        switch(cardType.toLowerCase())
        {
            case "definition":
                return new DefCard(question,answer,category);
            case "fib":
                return new FIBCard(question,answer,category);
            case "mcq":
                return new MCQCard(question, answer, category);
            case "tf":
                return new TFCard(question, answer, category);
        }
        return null;
    }
}
