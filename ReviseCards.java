import java.lang.*;
import java.util.Scanner;


public class ReviseCards {
    private deck Deck;
    private float learningRate;

    public float getLearningRate() {
        return learningRate;
    }

    public void setLearningRate(float learningRate) {
        this.learningRate = learningRate;
    }
    public void revise (){
        System.out.println("Select the deck you want to revise");
        //display list of decks;
        Scanner in = new Scanner(System.in);
        int deck_num;
        char ans;
         
        deck_num=in.nextInt();
         
        long Delay=20000;//initially 20seconds given to answer

        for(int i=0;i<Deck.getSize();i++){

            //display the card;
            Thread.sleep(Delay);
            //display the answer

            System.out.println("Were you able to answer (Y/N) ?");
            ans=in.next().charAt(0);
            
            if(ans=='Y' && Delay>=10000) { //min time 10seconds reduce by 2 seconds if correct answer
            	Delay-=2000;
            }
            else if(ans == 'N' && Delay<=40000) { // max time 40seconds increase by 2 seconds if wrong answer
            	Delay += 2000;
            }
            
             
             

         }
    }
}
