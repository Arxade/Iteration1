/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iteration1.classesConnexion;

import java.sql.*;
import java.util.Date;

/**
 *
 * @author Paul
 */
public class ConnexionMySQL extends Connexion {

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private DatabaseMetaData metadata = null;

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

    @Override
    public ResultSet getResultSetFromTable(String table) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        statement = connect.createStatement();

        preparedStatement = connect
                .prepareStatement("SELECT * from " + table);
        resultSet = preparedStatement.executeQuery();
        return resultSet;

    }

    @Override
    public void writeMetaData(ResultSet resultSet) throws SQLException {
        //  Now get some metadata from the database
        // Result set get the result of the SQL query

        System.out.println("Les colonnes de la table sont : ");

        System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
        for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
            System.out.println("Colonne " + i + " " + resultSet.getMetaData().getColumnName(i));
        }
    }

    @Override
    public String writeMetaDataToString(ResultSet resultSet) throws SQLException {

        String text = "Les colonnes de la table sont : \r\n";

        for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
            text = text + ("Colonne " + i + " " + resultSet.getMetaData().getColumnName(i) + "\r\n");
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
