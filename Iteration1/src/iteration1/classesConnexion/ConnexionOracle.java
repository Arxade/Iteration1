/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iteration1.classesConnexion;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author Paul
 */
public class ConnexionOracle extends Connexion {
    

    public ConnexionOracle(){}
    
    @Override
    public void connexion(String user, String psswd, String URL, String nomBDD){
    
        try  
        {
            //On modifira l'ui pour s'adapter à Oracle, MySQL n'ayant pas exactement la même organisation//
            connect = DriverManager.getConnection("jdbc:oracle:thin:@" + URL, user , psswd);
            if (connect != null)
            {
                javax.swing.JOptionPane.showMessageDialog(null, "Connecté à la base de donnée!");
            }
            else
            {
                javax.swing.JOptionPane.showMessageDialog(null, "la connexion a échoué!");
            }
        }
        catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            javax.swing.JOptionPane.showMessageDialog(null, e);
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null,e);
            e.printStackTrace();
        }

    
    }
    
    @Override
    public ResultSet getResultSetFromTable(String table) throws Exception{
                statement = connect.createStatement();

            preparedStatement = connect.prepareStatement("SELECT * FROM "+ table);
            resultSet = preparedStatement.executeQuery();
            return resultSet;
   
    }
    
}
