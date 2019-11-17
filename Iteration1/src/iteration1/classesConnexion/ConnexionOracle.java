/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iteration1.classesConnexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Paul
 */
public class ConnexionOracle extends Connexion {
    
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private DatabaseMetaData metadata = null;
    
    public ConnexionOracle(){}
    
    @Override
    public void connexion(String user, String psswd, String URL, String nomBDD){
    
        try  
        {
            //On modifira l'ui pour s'adapter à Oracle, MySQL n'ayant pas exactement la même organisation//
            connect = DriverManager.getConnection("jdbc:oracle:thin:@" + URL, user , psswd);
            if (connect != null)
            {
                javax.swing.JOptionPane.showMessageDialog(null, "Connected to the database!");
                              metadata = connect.getMetaData(); 

                
                // Accéder à la liste des tables
                Statement stmt= connect.createStatement();
                ResultSet res = stmt.executeQuery("SELECT table_name FROM user_tables");
                DefaultListModel model=new DefaultListModel();
                while(res.next())
                {
                    model.addElement(res.getString("TABLE_NAME"));

                }
            }
            else
            {
                javax.swing.JOptionPane.showMessageDialog(null, "Failed to make connection!");
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

            preparedStatement = connect.prepareStatement("SELECT * from "+ table);
            resultSet = preparedStatement.executeQuery();
            return resultSet;
   
    }
    
    @Override
    public void writeMetaData(ResultSet resultSet) throws SQLException {
        //  Now get some metadata from the database
        // Result set get the result of the SQL query

        System.out.println("The columns in the table are: ");

        System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
        for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
            System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
        }
    }
    
    @Override
    public String writeMetaDataToString(ResultSet resultSet) throws SQLException {
       
           
        String text = "The columns in the table are : \r\n";
        
        for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
            text = text + ("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i) + "\r\n");
        }
        
        return text;
       
    }
   

    
    @Override
    public String writePrimaryKeysToString(String nomTable) throws SQLException {
        metadata = connect.getMetaData();
        ResultSet clefs = metadata.getPrimaryKeys(connect.getCatalog(), connect.getSchema(), nomTable);
        String text = "";
        while (clefs.next()) {
            String nomColonne = clefs.getString("COLUMN_NAME");
            text = text + ("La colonne " + nomColonne + " est une clef primaire de " + nomTable + "\r\n");
        }
        return text;
    }


    @Override
    public void writeResultSet(ResultSet resultSet) throws SQLException {
        // ResultSet is initially before the first data set
        while (resultSet.next()) {
            // It is possible to get the columns via name
            // also possible to get the columns via the column number
            // which starts at 1
            // e.g. resultSet.getSTring(2);
            String user = resultSet.getString("myuser");
            String website = resultSet.getString("webpage");
            String summary = resultSet.getString("summary");
            Date date = resultSet.getDate("datum");
            String comment = resultSet.getString("comments");
            System.out.println("User: " + user);
            System.out.println("Website: " + website);
            System.out.println("summary: " + summary);
            System.out.println("Date: " + date);
            System.out.println("Comment: " + comment);
        }
    }
    
    @Override
    protected void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }
      
}
