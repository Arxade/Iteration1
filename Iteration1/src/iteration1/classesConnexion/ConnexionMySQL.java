/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iteration1.classesConnexion;
import iteration1.forms.FormConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;



/**
 *
 * @author Paul
 */
public class ConnexionMySQL extends Connexion{ 
    
    private Connection connecter = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
   
    public ConnexionMySQL(){}
    
   
    public void connexion(String user, String psswd, String nomBDD) {
        
        try {
            // chargement driver sql
            Class.forName("com.mysql.jdbc.Driver");
            
            // setup connexion avec la BD (A CHANGER ICI BD LOCALE WAMPSERVEUR POUR TEST)
            connecter = DriverManager
                    .getConnection("jdbc:mysql://localhost/"+nomBDD+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
                            + "&user="+user+"&password="+psswd );

        } catch (Exception e) {
           System.out.println(e);
        }
    }
    
}
