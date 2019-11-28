/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iteration1;

import iteration1.forms.*;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Arxade
 */
public class Iteration1 {
    /*
     * @param args the command line arguments
     */
    public static void main(String[] args) {   
        //Initialisation des différents JPanel
        ConnectionPanel p1 = new ConnectionPanel();
        p1.setName("Connection");
        VisualizationPanel p2 = new VisualizationPanel();
        p2.setName("Visualization");
        
        //Initialisation du CardLayout avec les JPanel
        JPanel cards = new JPanel(new CardLayout());
        cards.add(p1);
        cards.add(p2);
        
        //Initialisation de la JFrame
        MainFrame frame = new MainFrame(cards);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(cards);
        frame.setCurrentCard();
        frame.setVisible(true);
        
        //Si les informations relatives à la connexion à la bdd ne sont pas 
        //trouvables dans le JSON, affiche le JDialog pour entrer les paramètres de l'URL
        if(!p1.isURLSet()) {
            p1.createParamsDialog().setVisible(true);
        }
    }

}
