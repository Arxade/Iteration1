/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iteration1.classesConnexion;

import java.sql.*;

/**
 *
 * @author Arxade
 */
public abstract class Connexion {

    public abstract void connexion(String nomBDD, String user, String psswd, String URL);

    public abstract ResultSet getResultSetFromTable(String table) throws Exception;

    public abstract void writeMetaData(ResultSet resultSet) throws SQLException;

    public abstract String writeMetaDataToString(ResultSet resultSet) throws SQLException;

    public abstract void writeResultSet(ResultSet resultSet) throws SQLException;

    public abstract String writePrimaryKeysToString(String nomTable) throws SQLException;

    protected abstract void close();

}
