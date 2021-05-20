
package clocksolitaire;

import clocksolitaire.Enum.Suit;
import clocksolitaire.adt.*;
import clocksolitaire.LinkedDataStructures.*;
import java.util.Random;


/**
 *
 * @author Yassine Salimi
 */
public class PackOfCards {

   //=================================================================///
    public static final int MAX_NUMBER_OF_CARDS = 52;
    public static final int PAIR_OF_CARDS = MAX_NUMBER_OF_CARDS/4;
    public static final int FOUR_CARDS = MAX_NUMBER_OF_CARDS/PAIR_OF_CARDS;
    /////////////////////////////////////////////////////////////////
    
    
    private final PlayingCard[] cards =
            new PlayingCard[MAX_NUMBER_OF_CARDS];
    //private StackADT<Card>[] packCards = new StackADT[PAIR_OF_CARDS];
    //private String[] images = new String[MAX_NUMBER_OF_CARDS];

    public PackOfCards() {
      
        for(int x = 0 ; x < MAX_NUMBER_OF_CARDS;x++)
        {
          //  packCards[x%PAIR_OF_CARDS] = new LinkedStack();
            cards[x] = new PlayingCard();
          //  images[x] = new String();
        }
       initializeCards(); 
    }

    public void shuffle() 
    {
        Random ran = new Random();
        PlayingCard holdCard ;
        for (int x = 0 ;x < MAX_NUMBER_OF_CARDS;x++)
        {
            int tempRan= ran.nextInt(MAX_NUMBER_OF_CARDS);
            holdCard  = cards[x];
            cards[x] = cards[tempRan];
            cards[tempRan] = holdCard; 
           // images[x] = cards[x].getImageSource();
        }
    }
    private final void initializeCards()
    {
     for (int x = 0 ;x < MAX_NUMBER_OF_CARDS;x++)
     {
    

         //set the suits
         if((x+1)<=PAIR_OF_CARDS)
             cards[x].setSuit(Suit.clubs);
         else if((x+1)>PAIR_OF_CARDS && (x+1)<=PAIR_OF_CARDS*2)
             cards[x].setSuit(Suit.spadess);
        else if((x+1) > PAIR_OF_CARDS*2 && (x+1) <= PAIR_OF_CARDS*3 )
            cards[x].setSuit(Suit.Diamnonds);
        else 
            cards[x].setSuit(Suit.hearts);
         
         //set the values
         if((x+1)%PAIR_OF_CARDS ==0)
         {
             cards[x].setFaceValue(PAIR_OF_CARDS);
         }
          else{
             cards[x].setFaceValue( (x+1) % PAIR_OF_CARDS);
          }
         cards[x].getImageSource(cards[x].getFaceValue(),
                 cards[x].getSuit());
//         images[x] = cards[x].getImageSource();
         
     }
    }
    public PlayingCard getCardAtIndex(int index)
    {
        return cards[index];
    }
 
 public String toString()
 {
     String print ="";
     for(int x = 0 ; x < MAX_NUMBER_OF_CARDS;x++)
     {
        print+=cards[x]   +"\n";
     }
     return print;
 }

    

}
