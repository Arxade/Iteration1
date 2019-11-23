/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iteration1.classesConnexion;


import java.sql.DriverManager;


/**
 *
 * @author Paul
 */
public class ConnexionMySQL extends Connexion {

   
    public ConnexionMySQL() {
    }

    @Override
    public void connexion(String user, String psswd, String URL, String nomBDD) {

        try {
            // chargement driver sql
            Class.forName("com.mysql.cj.jdbc.Driver");

            // setup connexion avec la BD
            connect = DriverManager
                    .getConnection("jdbc:mysql://" + URL + "/" + nomBDD + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
                            + "&user=" + user + "&password=" + psswd);
            javax.swing.JOptionPane.showMessageDialog(null, "Connexion réussie");

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, e);
        }
    }

    /*
    VOIR DANS "Connexion.java"
    
    @Override
    public ResultSet getResultSetFromTable(String table) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        statement = connect.createStatement();

        preparedStatement = connect
                .prepareStatement("SELECT * from " + table);
        resultSet = preparedStatement.executeQuery();
        return resultSet;

    }
    */
}
