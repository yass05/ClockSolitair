package clocksolitaire;
// ====================  YASSINE SALIMI=================//

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static clocksolitaire.PackOfCards.*;
import clocksolitaire.excaptions.InvalidValueOfTheCardException;

/**
 *
 * @author Yassin Salimi
 */
public class GuiGame extends JFrame {
    
   private static int[] count = new int[PAIR_OF_CARDS];
   //****************** JFRAME**********************************/
    public static final int HEIGHT = 800; // height of the window
    public static final int WIDTH = 1200;// width of the window
    ////////////////////////////////////////////////////////////////////
   
    //******************* FOR  POSITIONING *****************************///
    public static final int WIDTH_OF_IMAGE = 100; // width of image
    public static final int HEIGHT_OF_IMAGE = 120; // height of image
    public static final int MARGINE_HEIGHT = HEIGHT_OF_IMAGE / 2;
    //the space between the cards
    public static final int MARGINE = 10 * 3; 
    //Bounds x , y  for each label
    private int myX[] = new int[PAIR_OF_CARDS];
    private int myY[] = new int[PAIR_OF_CARDS];
    // x and y for the lower labels (images)
    private int myDownX[] = new int[PAIR_OF_CARDS];
    private int myDownY[] = new int[PAIR_OF_CARDS];
//////////////////////////////////////////////////////////////////////
    //images of the cards
    private ImageIcon[] image = new ImageIcon[MAX_NUMBER_OF_CARDS];
    //label for down images (cards)
    private JLabel[] labelDownImage = new JLabel[PAIR_OF_CARDS];
    //labesl for upper images(card)
    private JLabel[] labelImage = new JLabel[PAIR_OF_CARDS];

    //backface of card
    private ImageIcon backOfCard = new ImageIcon(
         new ImageIcon(getClass().getResource("Cards\\Backface_Blue.jpg")).
        getImage().getScaledInstance(100, 120, Image.SCALE_DEFAULT));
    private PackOfCards cards; // creating  anew pack of cards
    private JPanel panel;// container for everything
    //creating 13 piles
    private PileOfCards[] piles = new PileOfCards[PAIR_OF_CARDS];
     
    // ===== To show start, score and instruction ============
   //counts the score    
    private int score;
    private int moves;
    private JTextArea showScore ; // shows the score a jtextArea
    private JButton startButton ;// to restart the game
    private JButton instructions ;//gives instruction
    private JLabel printScore ; 
    private JLabel labelMoves ;
    private JTextField showMoves ;
    /////////////////////////////////////////////////////////////////
   //===============================================================//
    //checks if card can be moves
    private boolean cardCanMove[] = new boolean[PAIR_OF_CARDS];
    //checks if card has been dragged at the correct location
    private boolean correctLocation[] = new boolean[PAIR_OF_CARDS];
    //=============================================================//

    public GuiGame() {
        instructions = new JButton("Instructions");
        startButton = new JButton("Quit");
        showScore = new JTextArea();
        printScore = new JLabel("Score");
        showScore.setEditable(false);
        showMoves = new JTextField();
        labelMoves = new JLabel("MOVES");
        
        instructions.setBounds(WIDTH-280, HEIGHT-700, 150, 60);
        instructions.setFont(new Font("Arial", Font.BOLD, 20));
        startButton.setBounds(WIDTH-250, HEIGHT-600, 120, 60);
        startButton.setFont(new Font("Arial", Font.BOLD, 30));
       showScore.setBounds(WIDTH-250, HEIGHT-300, 100, 30);
        showScore.setFont(new Font("Arial", Font.BOLD, 20));
        printScore.setBounds(WIDTH-250, HEIGHT-350, 100, 30);
        printScore.setFont(new Font("Arial", Font.BOLD, 35));
        showMoves.setBounds(WIDTH-250, HEIGHT-400, 100, 30);
        showMoves.setFont(new Font("Arial", Font.BOLD, 35));
        labelMoves.setBounds(WIDTH-250, HEIGHT-450, 120, 30);
        labelMoves.setFont(new Font("Arial", Font.BOLD, 30));
        
        score = 0;
        panel = new JPanel();
        panel.setLayout(null);
        cards = new PackOfCards();
        cards.shuffle(); //shufle athe cards

        //Intialize labelImages  and the 13 piles   
        initializeLabelsAndImages();
        // file the piles with cards each pile must have four cards
        filePiles();
     
        flipedCardByDefault();
        
    // ///////////////// SHOWU UPPER CARDS ////////////
      showUperCards();
    ///////////////////////////// SHOW LAWER CARDS ////////////////////
     showLowerCards();
    /////////////////////////////////////////////////////////////////
    /// ADD ACTION LISTENER TO THE CARDS
     addActionListenerToLables();
          showMoves.setEditable(false);
          showScore.setText(score+"");
          showMoves.setText(moves+"");
          panel.add(instructions);
          panel.add(startButton);
          panel.add(printScore);
          panel.add(showScore);
          panel.add(showMoves);
          panel.add(labelMoves);
          add(panel);
          startButton.addActionListener(new Start());
          instructions.addActionListener(new InstructionButton());
 
    }
    //let the first card in the last pile is the starting cards
    public void flipedCardByDefault()
    {
       for (int x = 0; x < PAIR_OF_CARDS; x++) {
            if (x == 12) {
                cardCanMove[x] = true;
            } else {
                cardCanMove[x] = false;
            }
        }
    }
    //Intialize labesl and images
    public void initializeLabelsAndImages()
    {
         for (int x = 0; x < PAIR_OF_CARDS; x++) {
            labelImage[x] = new JLabel();
            piles[x] = new PileOfCards();
            correctLocation[x] = false;
        }
    }
     //intialse images
        public void initializeImages() {
        for (int x = 0; x < PAIR_OF_CARDS; x++) {
            if (!cardCanMove[x]) {
                image[x] = backOfCard;
            } 
            else 
            {
             image[x] = new ImageIcon(
              new ImageIcon(getClass().getResource(piles[x]
               .getCardFromPile().getImageSource())).
              getImage().getScaledInstance(100, 120, Image.SCALE_DEFAULT));
            }
        }
    }
    //=====Display the uper cards on the panel =====//
     public void showUperCards()
     {
           for (int x = 0; x < (PAIR_OF_CARDS); x++) {
            initializeImages();
            labelImage[x].setIcon(image[x]);
                   setPositionForCards(x);
            panel.add(labelImage[x]);
        }
     }
     //addActionListenr to the 13 labesls
     public void addActionListenerToLables()
     {
         for ( int x = 0 ; x < PAIR_OF_CARDS;x++)
         { 
          labelImage[x].addMouseMotionListener(new MouveLabel());
          labelImage[x].addMouseListener(new TransferLabel());
         }
     }
     //========Displays the lower cards on the panel/=====///
     public void showLowerCards()
     {
         for (int x = 0; x < PAIR_OF_CARDS; x++) {
             labelDownImage[x] = new JLabel(backOfCard);
             setPositionForLowerCard(x);
             panel.add(labelDownImage[x]);
         }
     }
    ///////////File the piles /////////
    public void filePiles() {
      for (int x = 0; x < MAX_NUMBER_OF_CARDS; x++) {
       if ((x >= 0) && x < FOUR_CARDS) {
            piles[x % PAIR_OF_CARDS].addCardToPile(cards.getCardAtIndex(x));
        } else if ((x >= FOUR_CARDS) && (x < FOUR_CARDS * 2)) {
            piles[x % PAIR_OF_CARDS].addCardToPile(cards.getCardAtIndex(x));
        } else if ((x >= FOUR_CARDS * 2) && x < (FOUR_CARDS * 3)) {
           piles[x % PAIR_OF_CARDS].addCardToPile(cards.getCardAtIndex(x));
        } else if ((x >= FOUR_CARDS * 3) && (x < FOUR_CARDS * 4)) {
            piles[x % PAIR_OF_CARDS].addCardToPile(cards.getCardAtIndex(x));
        } else if ((x >= FOUR_CARDS * 4) && (x < FOUR_CARDS * 5)) {
            piles[x % PAIR_OF_CARDS].addCardToPile(cards.getCardAtIndex(x));
        } else if ((x >= (FOUR_CARDS * 5)) && (x < FOUR_CARDS * 6)) {
            piles[x % PAIR_OF_CARDS].addCardToPile(cards.getCardAtIndex(x));
        } else if ((x >= FOUR_CARDS * 6) && (x < FOUR_CARDS * 7)) {
            piles[x % PAIR_OF_CARDS].addCardToPile(cards.getCardAtIndex(x));
        } else if ((x >= (FOUR_CARDS * 7)) && (x < FOUR_CARDS * 8)) {
            piles[x % PAIR_OF_CARDS].addCardToPile(cards.getCardAtIndex(x));
        } else if ((x >= FOUR_CARDS * 8) && (x < FOUR_CARDS * 9)) {
            piles[x % PAIR_OF_CARDS].addCardToPile(cards.getCardAtIndex(x));
        } else if ((x >= FOUR_CARDS * 9) && (x < FOUR_CARDS * 10)) {
            piles[x % PAIR_OF_CARDS].addCardToPile(cards.getCardAtIndex(x));
        } else if ((x >= FOUR_CARDS * 10) && (x < FOUR_CARDS * 11)) {
            piles[x % PAIR_OF_CARDS].addCardToPile(cards.getCardAtIndex(x));
        } else if ((x >= FOUR_CARDS * 11) && (x < FOUR_CARDS * 12)) {
            piles[x % PAIR_OF_CARDS].addCardToPile(cards.getCardAtIndex(x));
        } else if ((x >= FOUR_CARDS * 12) && (x < FOUR_CARDS * PAIR_OF_CARDS))
        {
            piles[x % PAIR_OF_CARDS].addCardToPile(cards.getCardAtIndex(x));
         } else {
                System.out.println("FILE PILE ERROR ");
            }

        }
    }
    
    //set position for upper cards
    public void setPositionForCards(Integer cardIndex) {

        if (cardIndex % PAIR_OF_CARDS == 0) {
            
            int x = WIDTH / 2;    int y = MARGINE_HEIGHT;
            myX[0] = x;     myY[0] = y;
            labelImage[cardIndex % PAIR_OF_CARDS].setBounds(x,
                    y,
                    WIDTH_OF_IMAGE, HEIGHT_OF_IMAGE);
        } else if (cardIndex % PAIR_OF_CARDS == 1) {

            int x = (WIDTH / 2 + WIDTH_OF_IMAGE) + MARGINE;
            int y = (MARGINE_HEIGHT * 2);

            labelImage[cardIndex % PAIR_OF_CARDS].setBounds(x,
                    y,
                    WIDTH_OF_IMAGE, HEIGHT_OF_IMAGE);
            myX[1] = x;
            myY[1] = y;
        }
        else if (cardIndex % PAIR_OF_CARDS == 2) {
            int x = (WIDTH / 2 + WIDTH_OF_IMAGE)
                    + MARGINE + WIDTH_OF_IMAGE / 2;
            int y = (MARGINE_HEIGHT * 3) + MARGINE_HEIGHT + MARGINE;
            labelImage[cardIndex % PAIR_OF_CARDS].setBounds(x,
                    y,
                    WIDTH_OF_IMAGE, HEIGHT_OF_IMAGE);
            myX[2] = x;     myY[2] = y;
        } else if (cardIndex % PAIR_OF_CARDS == 3) {
            int x = (WIDTH / 2 + WIDTH_OF_IMAGE) + (MARGINE);
            int y = (MARGINE_HEIGHT * 4) + (MARGINE_HEIGHT + MARGINE) * 2;
            labelImage[cardIndex % PAIR_OF_CARDS].setBounds(x,
                    y,
                    WIDTH_OF_IMAGE, HEIGHT_OF_IMAGE);
            myX[3] = x;    myY[3] = y;
        } 
        else if (cardIndex % PAIR_OF_CARDS == 4) {
            int x = (WIDTH / 2 + WIDTH_OF_IMAGE) - (WIDTH_OF_IMAGE);
            int y = (MARGINE_HEIGHT * 4) + (MARGINE_HEIGHT + MARGINE) * 3;
            labelImage[cardIndex % PAIR_OF_CARDS].setBounds(x,
                    y,
                    WIDTH_OF_IMAGE, HEIGHT_OF_IMAGE);
            myX[4] = x;
            myY[4] = y;
        } else if (cardIndex % PAIR_OF_CARDS == 5) {
            int x = (WIDTH / 2 - WIDTH_OF_IMAGE) - MARGINE;
            int y = (MARGINE_HEIGHT * 5) + (MARGINE_HEIGHT + MARGINE) * 3;
            labelImage[cardIndex % PAIR_OF_CARDS].setBounds(x,
                    y,
                    WIDTH_OF_IMAGE, HEIGHT_OF_IMAGE);
            myX[5] = x;     myY[5] = y;
        } 
        else if (cardIndex % PAIR_OF_CARDS == 6) {
            int x = (WIDTH / 2 - WIDTH_OF_IMAGE * 2) - MARGINE * 2;
            int y = (MARGINE_HEIGHT * 4) + (MARGINE_HEIGHT + MARGINE) * 3;
            labelImage[cardIndex % PAIR_OF_CARDS].setBounds(x,
                    y,
                    WIDTH_OF_IMAGE, HEIGHT_OF_IMAGE);
            myX[6] = x;        myY[6] = y;
        } else if (cardIndex % PAIR_OF_CARDS == 7) {
            int x = (WIDTH / 2 - WIDTH_OF_IMAGE * 3) - MARGINE * 3;
            int y = (MARGINE_HEIGHT * 4) + (MARGINE_HEIGHT + MARGINE) * 2;
            labelImage[cardIndex % PAIR_OF_CARDS].setBounds(x,
                    y,
                    WIDTH_OF_IMAGE, HEIGHT_OF_IMAGE);
            myX[7] = x;     myY[7] = y;
        } else if (cardIndex % PAIR_OF_CARDS == 8) {
            int x = (WIDTH / 2 - WIDTH_OF_IMAGE * 4)
                    - MARGINE * 4 + WIDTH_OF_IMAGE / 2 + MARGINE;
            int y = (MARGINE_HEIGHT * 3) + MARGINE_HEIGHT + MARGINE ;
            labelImage[cardIndex % PAIR_OF_CARDS].setBounds(x,
                    y,
                    WIDTH_OF_IMAGE, HEIGHT_OF_IMAGE);
            myX[8] = x;     myY[8] = y;
        } else if (cardIndex % PAIR_OF_CARDS == 9) {
            int x = (WIDTH / 2 - WIDTH_OF_IMAGE * 3) - MARGINE * 3;
            int y = (MARGINE_HEIGHT * 2);
            labelImage[cardIndex % PAIR_OF_CARDS].setBounds(x,
                    y,
                    WIDTH_OF_IMAGE, HEIGHT_OF_IMAGE);
            myX[9] = x;       myY[9] = y;

        } else if (cardIndex % PAIR_OF_CARDS == 10) {
            int x = (WIDTH / 2 - WIDTH_OF_IMAGE * 2) - MARGINE * 2;
            int y = (MARGINE_HEIGHT);
            labelImage[cardIndex % PAIR_OF_CARDS].setBounds(x,
                    y,
                    WIDTH_OF_IMAGE, HEIGHT_OF_IMAGE);
            myX[10] = x;     myY[10] = y;

            System.out.println(cardIndex + "x=" + x + "\ty=" + y);
        } else if (cardIndex % PAIR_OF_CARDS == 11) {
            int x = (WIDTH / 2 - WIDTH_OF_IMAGE) - MARGINE;
            int y = (MARGINE);
            labelImage[cardIndex % PAIR_OF_CARDS].setBounds(x,
                    y,
                    WIDTH_OF_IMAGE, HEIGHT_OF_IMAGE);
            myX[11] = x;   myY[11] = y;

        } else if (cardIndex % PAIR_OF_CARDS == 12) {
            int x = (WIDTH / 2 - WIDTH_OF_IMAGE) - MARGINE;
            int y = (MARGINE_HEIGHT * 3) + MARGINE_HEIGHT + MARGINE;
            labelImage[cardIndex % PAIR_OF_CARDS].setBounds(x,
                    y,
                    WIDTH_OF_IMAGE, HEIGHT_OF_IMAGE);
            myX[12] = x;  myY[12] = y;
        } else {
            System.out.print(cardIndex % PackOfCards.PAIR_OF_CARDS);
            System.out.println("Something went wrong "
                    + "in sePosition for cards");
        }

    }
    //set positions for lower cards
    public void setPositionForLowerCard(Integer cardIndex) {

        if (cardIndex % PAIR_OF_CARDS == 0) {
            int x = WIDTH / 2;
            int y = MARGINE_HEIGHT + MARGINE;
            myDownX[0] = x;myDownY[0] = y;
            labelDownImage[cardIndex % PAIR_OF_CARDS].setBounds(x,
                    y,
                    WIDTH_OF_IMAGE, HEIGHT_OF_IMAGE);
        } else if (cardIndex % PAIR_OF_CARDS == 1) {
            int x = (WIDTH / 2 + WIDTH_OF_IMAGE) + MARGINE;
            int y = (MARGINE_HEIGHT * 2) + MARGINE;
            myDownX[1] = x; myDownY[1] = y;
            labelDownImage[cardIndex % PAIR_OF_CARDS].setBounds(x,
                    y,
                    WIDTH_OF_IMAGE, HEIGHT_OF_IMAGE);
        } else if (cardIndex % PAIR_OF_CARDS == 2) {
            int x = (WIDTH / 2 + WIDTH_OF_IMAGE)
                    + MARGINE + WIDTH_OF_IMAGE / 2;
            int y = (MARGINE_HEIGHT * 3)
                    + MARGINE_HEIGHT + MARGINE + MARGINE;
            myDownX[2] = x;myDownY[2] = y;

            labelDownImage[cardIndex % PAIR_OF_CARDS].setBounds(x,
                    y,
                    WIDTH_OF_IMAGE, HEIGHT_OF_IMAGE);
        } else if (cardIndex % PAIR_OF_CARDS == 3) {
            int x = (WIDTH / 2 + WIDTH_OF_IMAGE) + (MARGINE);
            int y = (MARGINE_HEIGHT * 4)
                    + (MARGINE_HEIGHT + MARGINE) * 2 + MARGINE;
            myDownX[3] = x;   myDownY[3] = y;
            labelDownImage[cardIndex % PAIR_OF_CARDS].setBounds(x,
                    y,
                    WIDTH_OF_IMAGE, HEIGHT_OF_IMAGE);
        } else if (cardIndex % PAIR_OF_CARDS == 4) {
            int x = (WIDTH / 2 + WIDTH_OF_IMAGE) - (WIDTH_OF_IMAGE);
            int y = (MARGINE_HEIGHT * 4)
                    + (MARGINE_HEIGHT + MARGINE) * 3 + MARGINE;
            myDownX[4] = x;     myDownY[4] = y;
            labelDownImage[cardIndex % PAIR_OF_CARDS].setBounds(x,
                    y,
                    WIDTH_OF_IMAGE, HEIGHT_OF_IMAGE);
        } else if (cardIndex % PAIR_OF_CARDS == 5) {
            int x = (WIDTH / 2 - WIDTH_OF_IMAGE) - MARGINE;
            int y = (MARGINE_HEIGHT * 5)
                    + (MARGINE_HEIGHT + MARGINE) * 3 + MARGINE;
            myDownX[5] = x;    myDownY[5] = y;
            labelDownImage[cardIndex % PAIR_OF_CARDS].setBounds(x,
                    y,
                    WIDTH_OF_IMAGE, HEIGHT_OF_IMAGE);
        } else if (cardIndex % PAIR_OF_CARDS == 6) {
            int x = (WIDTH / 2 - WIDTH_OF_IMAGE * 2) - MARGINE * 2;
            int y = (MARGINE_HEIGHT * 4)
                    + (MARGINE_HEIGHT + MARGINE) * 3 + MARGINE;
            myDownX[6] = x;    myDownY[6] = y;
            labelDownImage[cardIndex % PAIR_OF_CARDS].setBounds(x,
                    y,
                    WIDTH_OF_IMAGE, HEIGHT_OF_IMAGE);

        } else if (cardIndex % PAIR_OF_CARDS == 7) {
            int x = (WIDTH / 2 - WIDTH_OF_IMAGE * 3) - MARGINE * 3;
            int y = (MARGINE_HEIGHT * 4)
                    + (MARGINE_HEIGHT + MARGINE) * 2 + MARGINE;
            myDownX[7] = x;   myDownY[7] = y;
            labelDownImage[cardIndex % PAIR_OF_CARDS].setBounds(x,
                    y,
                    WIDTH_OF_IMAGE, HEIGHT_OF_IMAGE);

        } else if (cardIndex % PAIR_OF_CARDS == 8) {
            int x = (WIDTH / 2 - WIDTH_OF_IMAGE * 4)+MARGINE
                    - MARGINE * 4 + WIDTH_OF_IMAGE / 2;
            int y = (MARGINE_HEIGHT * 3)
                    + MARGINE_HEIGHT + MARGINE + MARGINE;
            myDownX[8] = x; myDownY[8] = y;
            labelDownImage[cardIndex % PackOfCards.PAIR_OF_CARDS]
                    .setBounds(x,
                    y,
                    WIDTH_OF_IMAGE, HEIGHT_OF_IMAGE);
        } else if (cardIndex % PAIR_OF_CARDS == 9) {
            int x = (WIDTH / 2 - WIDTH_OF_IMAGE * 3) - MARGINE * 3;
            int y = (MARGINE_HEIGHT * 2) + MARGINE;
            myDownX[9] = x; myDownY[9] = y;
            labelDownImage[cardIndex % PAIR_OF_CARDS].setBounds(x,
                    y,
                    WIDTH_OF_IMAGE, HEIGHT_OF_IMAGE);
        } else if (cardIndex % PackOfCards.PAIR_OF_CARDS == 10) {
            int x = (WIDTH / 2 - WIDTH_OF_IMAGE * 2) - MARGINE * 2;
            int y = (MARGINE_HEIGHT) + MARGINE;
            myDownX[10] = x;
            myDownY[10] = y;
            labelDownImage[cardIndex % PAIR_OF_CARDS].setBounds(x,
                    y,
                    WIDTH_OF_IMAGE, HEIGHT_OF_IMAGE);
        } else if (cardIndex % PAIR_OF_CARDS == 11) {
            int x = (WIDTH / 2 - WIDTH_OF_IMAGE) - MARGINE;
            int y = MARGINE + MARGINE;
            myDownX[11] = x; myDownY[11] = y;
            labelDownImage[cardIndex].setBounds(x,
                    y,
                    WIDTH_OF_IMAGE, HEIGHT_OF_IMAGE);

        } else if (cardIndex % PAIR_OF_CARDS == 12) {
            int x = (WIDTH / 2 - WIDTH_OF_IMAGE) - MARGINE;
            int y = (MARGINE_HEIGHT * 3)
                    + MARGINE_HEIGHT + MARGINE + MARGINE * 2;
            myDownX[12] = x;myDownY[12] = y;
            labelDownImage[cardIndex % PAIR_OF_CARDS].setBounds(x,
                    y,
                    WIDTH_OF_IMAGE, HEIGHT_OF_IMAGE);
        } else {
            System.out.print(cardIndex % PAIR_OF_CARDS);
            System.out.println("Something went "
                    + "wrong in sePosition for Lowercards");
        }
    }
    
   //inner class for correctLocation dragging
    private class MouveLabel implements MouseMotionListener {
       public void helpMouseDragged
        (Point p1, int value, int index , MouseEvent event)
        {
            value = value -1;
             int myXX = p1.x - (event.getComponent().getWidth() / 2);
             int myYY = p1.y - (event.getComponent().getHeight() / 2);
            event.getComponent().setLocation(myXX, myYY);

          if (myXX >= myX[value]-MARGINE &&
                  myXX <= myX[value] + WIDTH_OF_IMAGE
              && myYY >= myY[0]-MARGINE &&
                  myYY <= myY[value] + HEIGHT_OF_IMAGE) 
          {
                     correctLocation[index] = true;
                    } else {
                       correctLocation[index] = false;
                  }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            
       //Point to be abale to drag the card from the middle
       // not the top left // converting the location of the label point
            Point p = e.getPoint();
            p = SwingUtilities.convertPoint(
                    e.getComponent(), p, e.getComponent().getParent());

            for (int x = 0; x < PAIR_OF_CARDS; x++) {
              
                switch (piles[x].getCardFromPile().getFaceValue()) {

                    case 1: {
                                helpMouseDragged(p,1,x,e);
                        break;
                    }
                    case 2: {
                            helpMouseDragged(p,2,x,e);
                        break;

                    }
                    case 3: {
                              helpMouseDragged(p,3,x,e);
                        break;
                    }
                    case 4: {
                               helpMouseDragged(p,4,x,e);
                        break;
                    }
                    case 5: {
                            helpMouseDragged(p,5,x,e);
                        break;
                    }
                    case 6: {
                              helpMouseDragged(p,6,x,e);
                        break;
                    }
                    case 7: {
                              helpMouseDragged(p,7,x,e);
                        break;
                    }
                    case 8: {
                               helpMouseDragged(p,8,x,e);
                        break;
                    }
                    case 9: {
                            helpMouseDragged(p,9,x,e);
                        break;
                    }
                    case 10: {
                               helpMouseDragged(p,10,x,e);
                        break;
                    }
                    case 11: {
                               helpMouseDragged(p,11,x,e);
                        break;
                    }
                    case 12: {
                                 helpMouseDragged(p,12,x,e);
                        break;
                    }
                    case 13: {
                              helpMouseDragged(p,13,x,e);
                        break;
                    }
                    default:
                        System.out.println
             ("Somethin went wrong in the Mouse Dragged");
                        break;
                }
            }
            repaint();
        }
        @Override
        public void mouseMoved(MouseEvent e) {
        }
    }
    
    //inner class to transfer the labes
    private class TransferLabel implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
        }
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {
           //Switch the cards if mouse has been released
            doTheSwitch();
        }
        @Override
        public void mouseEntered(MouseEvent e) {
        }
        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
    //iner class for the start button
    private class Start implements ActionListener{

        
        
        
        // need to work on this Method 
        @Override
        public void actionPerformed(ActionEvent e)  
        {
            if(e.getSource() == startButton)
            {
                 new JOptionPane("I still need to work on this method");
                try {
                 Thread.sleep(140);
                }catch(InterruptedException ex)
                {
                    
                }
               
                 System.exit(0);
                //cealn the program
                //dispose();
                
                
                //start a new one
                GuiGame c = new GuiGame();
                   c.setTitle("Iamge");
                       c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    c.setVisible(true);
                             c.pack();
                     c.setSize(new Dimension(1100,800)); 
                       c.setPreferredSize(null);
                   c.setResizable(false);
               
                
                
                
            }
        }
        
    }
    
    //inner class for instruction btton
    private class InstructionButton implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == instructions)
            {
                JOptionPane.showMessageDialog(null,
                     "The objective is to place a card into its corresponding clock"
                       + "number position,\n e.g. the card 5 goes into the 5 o'clock pile,"
                       + "\n Ace into the 1 o'clock pile, Jack into the "
                       + "11 o'clock pile, Queen into 12, and King into the center pile.\n"
                       +"When all of the cards are face up and in their correct clock number pile then you've won the game,\n but if all"
                       + " four Kings are facing up before all of the other cards are in their correct pile you've lost.");
            }
        }
        
    }
    //dequeue card in pile1 then store it in pile2
    public void swapCards(PileOfCards pile1, PileOfCards pile2) {
        PlayingCard temp;
        temp = pile1.deletePile();
        pile2.addCardToPile(temp);
    }
    // switch the cards in the pile
    public void doTheSwitch() {
        if(userWon())
        {
         gameIsOver();
        }
        //
        disableLabel();
        for (int x = 0; x < PAIR_OF_CARDS; x++) {
            //if the cardis at correct position and is notFlipped
            if (correctLocation[x] && cardCanMove[x]) {
                score+=10;
                moves++;
                showScore.setText(score+"");
                showMoves.setText(moves+"");
                if (piles[x].getCardFromPile().getFaceValue() == 1)
                {
                    count[0]++;
                    if (x != 0) {
                        insertCardToPile(x,0);
                    } else {
                        insertCardToTheSamePile(x);
                    } 
                    labelImage[x].setLocation(myX[x], myY[x]);              
                         break;
                } else if (piles[x].getCardFromPile().getFaceValue() == 2) {
                    count[1]++;
                    if (x != 1) {
                       insertCardToPile(x,1);
                    } else {
                       insertCardToTheSamePile(x);

                    }

                    labelImage[x].setLocation(myX[x], myY[x]);
                    break;
                } else if (piles[x].getCardFromPile().getFaceValue() == 3) {
                    count[2]++;
                    if (x != 2) {
                        insertCardToPile(x,2);
                    }else {
                        insertCardToTheSamePile(x);
                    }
                    labelImage[x].setLocation(myX[x], myY[x]);
                    break;
                } else if (piles[x].getCardFromPile().getFaceValue() == 4) {
                    if (x != 3) {
                        count[3]++;
                        insertCardToPile(x,3);
                    } else {
                        insertCardToTheSamePile(x);

                    }

                    labelImage[x].setLocation(myX[x], myY[x]);
                    break;
                } else if (piles[x].getCardFromPile().getFaceValue() == 5) {
                    count[4]++;
                    if (x != 4) {
                        insertCardToPile(x,4);
                    } else {
                        insertCardToTheSamePile(x);
                    }

                    labelImage[x].setLocation(myX[x], myY[x]);
                    break;
                } else if (piles[x].getCardFromPile().getFaceValue() == 6) {
                    count[5]++;
                    if (x != 5) {
                        insertCardToPile(x,5);
                    } else {
                       insertCardToTheSamePile(x);
                    }

                    labelImage[x].setLocation(myX[x], myY[x]);
                    break;
                } else if (piles[x].getCardFromPile().getFaceValue() == 7) {
                    count[6]++;
                    if (x != 6) {
                       insertCardToPile(x,6);
                    } else {
                        insertCardToTheSamePile(x);

                    }

                    labelImage[x].setLocation(myX[x], myY[x]);
                } else if (piles[x].getCardFromPile().getFaceValue() == 8) {
                    count[7]++;
                    if (x != 7) {
                        insertCardToPile(x,7);
                    } else {
                       insertCardToTheSamePile(x);
                    }

                    labelImage[x].setLocation(myX[x], myY[x]);
                    break;
                } else if (piles[x].getCardFromPile().getFaceValue() == 9) {
                    count[8]++;
                    if (x != 8) {
                        insertCardToPile(x,8);
                    } else {
                        insertCardToTheSamePile(x);
                    }

                    labelImage[x].setLocation(myX[x], myY[x]);
                    break;
                } else if (piles[x].getCardFromPile().getFaceValue() == 10) {
                    count[9]++;
                    if (x != 9) {
                        insertCardToPile(x,9);
                    } else {
                        insertCardToTheSamePile(x);
                    }

                    labelImage[x].setLocation(myX[x], myY[x]);
                    break;
                } else if (piles[x].getCardFromPile().getFaceValue() == 11) {
                    count[10]++;
                    if (x != 10) {
                        insertCardToPile(x,10);
                    } else {
                        insertCardToTheSamePile(x);
                    }

                    labelImage[x].setLocation(myX[x], myY[x]);
                    break;
                } else if (piles[x].getCardFromPile().getFaceValue() == 12) {
                    count[11]++;
                    if (x != 11) {
                        insertCardToPile(x,11);
                    } else {
                       insertCardToTheSamePile(x);
                    }
                    labelImage[x].setLocation(myX[x], myY[x]);
                    break;
                } else if (piles[x].getCardFromPile().getFaceValue() == 13) {
                    count[12]++;
                    if (x != 12) {
                        insertCardToPile(x,12);
                    } else {
                        insertCardToTheSamePile(x);
                    }
                    labelImage[x].setLocation(myX[x], myY[x]);
                    if(userLost())
                    {
                        gameIsOver();
                    }
                    break;

                } else {
                    throw new 
                        InvalidValueOfTheCardException
                        (piles[x].getCardFromPile().getFaceValue()+"");

                }
            } 
            else {
                labelImage[x].setLocation(myX[x], myY[x]);
            }

        }
    }
    //switc cards in  piles piles at index1 with pile at index2
    
    //insert card to an other pile
    public void insertCardToPile(int index1 ,int index2)
    {
        ImageIcon temp;
       temp = new ImageIcon(new ImageIcon(getClass()
              .getResource(piles[index2].getCardFromPile()
                           .getImageSource())).
             getImage().getScaledInstance(100, 120, Image.SCALE_DEFAULT));

              labelImage[index2].setIcon(temp);
              labelDownImage[index2].setIcon(labelImage[index1].getIcon());
              swapCards(piles[index1], piles[index2]);
              cardCanMove[index1] = false;
              cardCanMove[index2] = true;
              labelImage[index1].setIcon(backOfCard);    
    }
//insert he first card in the pile by
 //the second one in the same pile
    public void insertCardToTheSamePile(int index)
    {
      swapCards(piles[index], piles[index]);
      ImageIcon temp;
       temp = new ImageIcon(new ImageIcon(getClass()
            .getResource(piles[index].getCardFromPile()
            .getImageSource())).
            getImage().getScaledInstance(100, 120, Image.SCALE_DEFAULT));

           cardCanMove[index] = true;
           labelImage[index].setIcon(temp);
    }
  // check if the user won 
public boolean userWon()
{
    for ( int x = 0 ; x < PAIR_OF_CARDS;x++)
    {
        if(count[x] !=4)
            return false;
    }
    return true ;
}
//checks if user lost
public boolean userLost()
{
    return count[12] == 4;
}
//it ends the game
public void gameIsOver()
{
    try{
        if(userWon())
                JOptionPane.showMessageDialog
        (null, "CONGRATS YOU WON !!!!");
        else if(userLost())
            JOptionPane.showMessageDialog(null, "YOU LOST BYE!!!!!");
        Thread.sleep(1000);
        System.exit(-1);
    }catch(Exception e)
    {
        e.printStackTrace();
    }
}
public void disableLabel()
{
    for ( int x = 0 ;x < PAIR_OF_CARDS;x++)
    {
        if(count[x] == 4)
            labelImage[x].setEnabled(false);
    }
}
 
 

}
