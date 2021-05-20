
package clocksolitaire;
import clocksolitaire.Enum.Suit;
import clocksolitaire.Enum.Color;
import clocksolitaire.excaptions.InvalidValueOfTheCardException;

/**
 * @author Yassine Salimi
 */
public class PlayingCard 
{
   
    
    private Integer faceValue ; // value of card
    private Suit suit ; // the suit of card
    private String ImageResource ; // the imagerresource of card
    
 
    PlayingCard()
    {
        faceValue = 0 ;
        suit = Suit.Diamnonds;
        //fliped = true ;
    }
    PlayingCard(Integer faceValue , Suit suit)
    {
        this.faceValue = faceValue;
        this.suit = suit;
        
    }
    PlayingCard(Integer faceValue, Suit suit , boolean fliped)
    {
        this.faceValue = faceValue;
        this.suit = suit ;
    }
    //################ SETTERS ######################//
    public void setFaceValue(Integer faceValue)
    {
        if (!this.isValidValue())
            throw new InvalidValueOfTheCardException(faceValue+"");
        choseValue(faceValue);
    }
    public void setSuit(Suit suit)
    {       
        choseSuit(suit);
    }
    
    //##############################################//
    
    //################## GETTERS ###################//
    public Integer getFaceValue()
    {
        return faceValue;
    }
    public Suit getSuit()
    {
        return suit ;
    }
   //get the imageResource based o the value and suit
    public void getImageSource(Integer faceValue, Suit suit)
    {

       if(faceValue == 1)
        {
            if(suit == Suit.clubs )
                ImageResource = "Cards\\Ace_Clubs.jpg";
            else if(suit == Suit.Diamnonds )
                ImageResource = "Cards\\Ace_Diamonds.jpg";
            else if (suit == Suit.hearts)
                ImageResource = "Cards\\Ace_Hearts.jpg";
            else 
                ImageResource = "Cards\\Ace_Spades.jpg";
       }
        else if(faceValue == 2)
        {
            if(suit == Suit.clubs )
                ImageResource = "Cards\\2_Clubs.jpg";
            else if(suit == Suit.Diamnonds )
                ImageResource = "Cards\\2_Diamonds.jpg";
            else if (suit == Suit.hearts)
                ImageResource = "Cards\\2_Hearts.jpg";
            else 
                ImageResource = "Cards\\2_Spades.jpg";
       }
        else  if(faceValue == 3)
        {
            if(suit == Suit.clubs )
                ImageResource = "Cards\\3_Clubs.jpg";
            else if(suit == Suit.Diamnonds )
                ImageResource = "Cards\\3_Diamonds.jpg";
            else if (suit == Suit.hearts)
                ImageResource = "Cards\\3_Hearts.jpg";
            else 
                ImageResource = "Cards\\3_Spades.jpg";
       }
        else if(faceValue == 4)
        {
            if(suit == Suit.clubs )
                ImageResource = "Cards\\4_Clubs.jpg";
            else if(suit == Suit.Diamnonds )
                ImageResource = "Cards\\4_Diamonds.jpg";
            else if (suit == Suit.hearts)
                ImageResource = "Cards\\4_Hearts.jpg";
            else 
                ImageResource = "Cards\\4_Spades.jpg";
       }
        else if(faceValue == 5)
        {
            if(suit == Suit.clubs )
                ImageResource = "Cards\\5_Clubs.jpg";
            else if(suit == Suit.Diamnonds )
                ImageResource = "Cards\\5_Diamonds.jpg";
            else if (suit == Suit.hearts)
                ImageResource = "Cards\\5_Hearts.jpg";
            else 
                ImageResource = "Cards\\5_Spades.jpg";
       }
        else if(faceValue == 6)
        {
            if(suit == Suit.clubs )
                ImageResource = "Cards\\6_Clubs.jpg";
            else if(suit == Suit.Diamnonds )
                ImageResource = "Cards\\6_Diamonds.jpg";
            else if (suit == Suit.hearts)
                ImageResource = "Cards\\6_Hearts.jpg";
            else 
                ImageResource = "Cards\\6_Spades.jpg";
       }
        else  if(faceValue == 7)
        {
           
             if(suit == Suit.clubs )
                ImageResource = "Cards\\7_Clubs.jpg";
            else if(suit == Suit.Diamnonds )
                ImageResource = "Cards\\7_Diamonds.jpg";
            else if (suit == Suit.hearts)
                ImageResource = "Cards\\7_Hearts.jpg";
            else 
                ImageResource = "Cards\\7_Spades.jpg";
       }
        else if(faceValue == 8)
        {
            if(suit == Suit.clubs )
                ImageResource = "Cards\\8_Clubs.jpg";
            else if(suit == Suit.Diamnonds )
                ImageResource = "Cards\\8_Diamonds.jpg";
            else if (suit == Suit.hearts)
                ImageResource = "Cards\\8_Hearts.jpg";
            else 
                ImageResource = "Cards\\8_Spades.jpg";
       }
        else if(faceValue == 9)
        {
            if(suit == Suit.clubs )
                ImageResource = "Cards\\9_Clubs.jpg";
            else if(suit == Suit.Diamnonds )
                ImageResource = "Cards\\9_Diamonds.jpg";
            else if (suit == Suit.hearts)
                ImageResource = "Cards\\9_Hearts.jpg";
            else 
                ImageResource = "Cards\\9_Spades.jpg";
       }
        else if(faceValue == 10)
        {
            if(suit == Suit.clubs )
                ImageResource = "Cards\\10_Clubs.jpg";
            else if(suit == Suit.Diamnonds )
                ImageResource = "Cards\\10_Diamonds.jpg";
            else if (suit == Suit.hearts)
                ImageResource = "Cards\\10_Hearts.jpg";
            else 
                ImageResource = "Cards\\10_Spades.jpg";
       }
        else  if(faceValue == 11)
        {
            if(suit == Suit.clubs )
                ImageResource = "Cards\\Jack_Clubs.jpg";
            else if(suit == Suit.Diamnonds )
                ImageResource = "Cards\\Jack_Diamonds.jpg";
            else if (suit == Suit.hearts)
                ImageResource = "Cards\\Jack_Hearts.jpg";
            else 
                ImageResource = "Cards\\Jack_Spades.jpg";
       }
        else if(faceValue == 12)
        {
            if(suit == Suit.clubs )
                ImageResource = "Cards\\Queen_Clubs.jpg";
            else if(suit == Suit.Diamnonds )
                ImageResource = "Cards\\Queen_Diamonds.jpg";
            else if (suit == Suit.hearts)
                ImageResource = "Cards\\Queen_Hearts.jpg";
            else 
                ImageResource = "Cards\\Queen_Spades.jpg";
       }
        else if(faceValue == 13)
        {
            if(suit == Suit.clubs )
                ImageResource = "Cards\\King_Clubs.jpg";
            else if(suit == Suit.Diamnonds )
                ImageResource = "Cards\\King_Diamonds.jpg";
            else if (suit == Suit.hearts)
                ImageResource = "Cards\\King_Hearts.jpg";
            else 
                ImageResource = "Cards\\King_Spades.jpg";
       }
        else 
        {
            System.out.println("setImage Error");
            System.exit(0);
        }     
    }
    //check is suit is null
    public boolean suitIsNull()
    {
        return suit == null ;
    }
    public final String getBackSideImageSource()
    {
        return "Cards\\Backface_Blue.jpg";
    }
    //retur the image resource
    public String getImageSource()
    {
        return ImageResource;
    }
    //#############################################//
    
    //check if the value of card is valid
    public boolean isValidValue()
    {
        return (faceValue >=1 || faceValue <=13);
    }
     // pick a suit for card
     private void choseSuit(Suit tempSuit)
     {
         if(tempSuit == Suit.Diamnonds)
             suit = Suit.Diamnonds;
         else if(tempSuit == Suit.clubs)
              suit = Suit.clubs;
         else if (tempSuit == Suit.spadess)
             suit = Suit.spadess;
         else if (tempSuit == Suit.hearts)
             suit = Suit.hearts;
         else 
         {
             System.out.println("Invalid suit has been chossen");
              System.exit(1);
         }
     }
     //pick a value
     private void choseValue(Integer value)
     {
         switch(value)
         {
             case 1:
                 faceValue = 1;
                 break;
             case 2:
                 faceValue = 2 ;
                 break;
             case 3:
                 faceValue = 3 ;
                 break;
             case 4:
                 faceValue = 4 ;
                 break;
             case 5:
                 faceValue = 5 ;
                 break;
             case 6:
                 faceValue = 6 ;
                 break ;
             case 7 :
                 faceValue = 7 ;
                 break ;
             case 8:
                 faceValue = 8 ;
                 break ;
             case 9 :
                 faceValue = 9 ;
                 break ;
             case 10:
                 faceValue = 10;
                 break;
             case 11:
                 faceValue = 11;
                 break ;
             case 12:
                 faceValue = 12 ;
                 break;
             case 13 :
                 faceValue = 13 ;
                 break;        
         }
     }
    @Override
    public String toString()
    {
        return faceValue + "\t" + suit;
    }
}
