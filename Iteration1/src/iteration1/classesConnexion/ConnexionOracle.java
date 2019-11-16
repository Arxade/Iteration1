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
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Paul
 */
public class ConnexionOracle extends Connexion {
    
    private Connection conn = null;
    private Statement stmt = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    
    public ConnexionOracle(){}
    
    @Override
    public void connexion(String nomBDD, String user, String psswd, String URL){
    
        try  
        {
            //On modifira l'ui pour s'adapter à Oracle, MySQL n'ayant pas exactement la même organisation//
            conn = DriverManager.getConnection("jdbc:oracle:thin:@" + URL, user , psswd);
            if (conn != null)
            {
                javax.swing.JOptionPane.showMessageDialog(null, "Connected to the database!");
                
                // Accéder à la liste des tables
                Statement stmt= conn.createStatement();
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
                stmt = conn.createStatement();

            preparedStatement = conn.prepareStatement("SELECT * from "+ table);
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

            if (stmt != null) {
                stmt.close();
            }

            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {

        }
    }
      
}
