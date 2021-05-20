
package clocksolitaire;
import clocksolitaire.adt.*;
import clocksolitaire.LinkedDataStructures.*;

/**
 * @author Yassine Salimi
 */
public class PileOfCards
{
    //creating a pile of cards 
    private QueueADT<PlayingCard> pile;
    
    
    public PileOfCards()
    {
        pile = new LinkedQueue();
    }
    //copy constructor
    public PileOfCards(PileOfCards other)
    {
        this.pile = other.pile;
    }
    //add card to the pile
    public void addCardToPile(PlayingCard card)
    {
         pile.enqueue(card);
      
    }
    //return the firstCardnt the pile
    public PlayingCard getCardFromPile()
    {
       
        return pile.first();
    }
    //return the second card in  the pile
    public PlayingCard getSeondCardFromPile()
    {
        QueueADT<PlayingCard> temp = pile ;
        temp.dequeue();   
        return temp.first();
    }
    //return the las card from the pile
    public PlayingCard getLastCardFromPile()
    {
         
        return pile.last();
    }
    //delete card from the pile
    public PlayingCard deletePile()
    {
        return pile.dequeue();
    }
    //return the size of the pile
public int getSizeOfPile()
{
    return pile.size();
}
//check if the pile is empty
public boolean pileIsEmpty()
{
    return pile.isEmpty();
}
    
    public String toString()
    {
        String print ="";
        for ( int x = 0 ;x < pile.size();x++)
        {
            Object holdCard = pile.dequeue();
            print += holdCard+"\n";
            pile.enqueue((PlayingCard)holdCard);
        }
        return print ;   
    }
}
