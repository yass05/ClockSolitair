
package clocksolitaire;

import clocksolitaire.adt.*;
import clocksolitaire.LinkedDataStructures.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Yassine Salimi
 */
public class Clock {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        PackOfCards cards = new PackOfCards();  
        GuiGame p = new GuiGame();
        p.setTitle("Clock Solitaire Game");
        p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p.setVisible(true);
        p.pack();
        p.setSize(new Dimension(1100,800)); 
        p.setPreferredSize(null);
        p.setResizable(false);
     }
    
}
