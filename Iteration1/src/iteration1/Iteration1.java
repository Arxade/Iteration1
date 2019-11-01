/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iteration1;
import iteration1.forms.*;
import iteration1.classesConnexion.*;

/**
 *
 * @author Arxade
 */
public class Iteration1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FormConnect form = new FormConnect();
        form.setVisible(true);
        ConnexionMySQL co = new ConnexionMySQL();
        co.connexion("nom", "login", "mdp");
        
    }
    
}
