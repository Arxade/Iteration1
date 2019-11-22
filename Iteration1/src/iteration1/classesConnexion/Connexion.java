/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iteration1.classesConnexion;

import java.sql.*;
    import java.util.ArrayList;

/**
 *
 * @author Arxade
 */
public abstract class Connexion {

    protected Connection connect = null;
    protected DatabaseMetaData dbMetadata = null;
    protected Statement statement = null;
    protected ResultSet resultSet = null;
    protected PreparedStatement preparedStatement = null;


    public abstract void connexion(String nomBDD, String user, String psswd, String URL);

    public abstract ResultSet getResultSetFromTable(String table) throws Exception;

    public void writeMetaData(ResultSet resultSet) throws SQLException {
        //  Now get some metadata from the database
        // Result set get the result of the SQL query

        System.out.println("Les colonnes de la table sont : ");

        System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
        for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
            System.out.println("Colonne " + i + " " + resultSet.getMetaData().getColumnName(i));
        }
    }

    public String writeMetaDataToString(ResultSet resultSet) throws SQLException {

        String text = "Les colonnes de la table sont : \r\n";

        for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
            text = text + ("Colonne " + i + " " + resultSet.getMetaData().getColumnName(i) + " Type: " + resultSet.getMetaData().getColumnTypeName(i) + "\r\n");
        }

        return text;
    }

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
            java.util.Date date = resultSet.getDate("datum");
            String comment = resultSet.getString("comments");
            System.out.println("User: " + user);
            System.out.println("Website: " + website);
            System.out.println("summary: " + summary);
            System.out.println("Date: " + date);
            System.out.println("Comment: " + comment);
        }
    }

    public String writePrimaryKeysToString(String nomTable) throws SQLException {
        dbMetadata = connect.getMetaData();
        ResultSet clefs = dbMetadata.getPrimaryKeys(connect.getCatalog(), connect.getSchema(), nomTable);
        String text = "";
        while (clefs.next()) {
            String nomColonne = clefs.getString("COLUMN_NAME");
            text = text + ("La colonne " + nomColonne + " est une clef primaire de " + nomTable + "\r\n");
        }
        return text;
    }

    public String writeForeignKeysToString(String nomTable) throws SQLException {
        dbMetadata = connect.getMetaData();
        ResultSet clefs = dbMetadata.getImportedKeys(connect.getCatalog(), connect.getSchema(), nomTable);
        String text = "";
        while (clefs.next()) {
            String nomColonne = clefs.getString("FKCOLUMN_NAME ");
            String nomColonnePk = clefs.getString("PKCOLUMN_NAME");
            String nomTablePk = clefs.getString("PKTABLE_NAME");
            text = text + ("La colonne " + nomColonne + " est une clef etrangere de " + nomTable + " referenceant " + nomColonnePk + " de " + nomTablePk + "\r\n");
        }
        return text;
    }

    public String writeConstraintsToString(String nomTable) throws SQLException {
        dbMetadata = connect.getMetaData();
        ResultSet clefs = dbMetadata.getIndexInfo(connect.getCatalog(), connect.getSchema(), nomTable, false, false);
        String text = "";
        while (clefs.next()) {
            String nomIndex = clefs.getString("INDEX_NAME");
            String type = clefs.getString("TYPE");
            String nomColonne = clefs.getString("COLUMN_NAME");
            text = text + ("La contrainte " + nomIndex + " est liée à la colonne " + nomColonne + " de type: " + type + " \r\n");
        }
        return text;
    }

    public String writeSelectToString(String nomTable) throws SQLException, Exception {
        String text = "";
        resultSet = getResultSetFromTable(nomTable);
        ResultSet rsTable = getResultSetFromTable(nomTable);
        while (resultSet.next()) {
            for (int y = 1; y < (rsTable.getMetaData().getColumnCount()) + 1; y++) {
                if (y == rsTable.getMetaData().getColumnCount()) {
                    text = text + resultSet.getString(y) + "\r\n";
                } else {
                    text = text + resultSet.getString(y) + " ";
                }

            }
        }
        return text;
    }

    public ArrayList<String> getTables() throws SQLException {
        String[] types = {"TABLE"};
        ArrayList<String> tables = new ArrayList<>();
        dbMetadata = connect.getMetaData();
        ResultSet rsTables = dbMetadata.getTables(connect.getCatalog(), connect.getSchema(), "%", types);
        while (rsTables.next()) {
            tables.add(rsTables.getString("TABLE_NAME"));
        }
        return tables;

    }

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
