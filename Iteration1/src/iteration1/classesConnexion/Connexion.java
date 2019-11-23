/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iteration1.classesConnexion;

import java.sql.*;
    import java.util.ArrayList;
import java.util.Hashtable;

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

    //METHODE INUTILISEE POUR LINSTANT
    //public abstract ResultSet getResultSetFromTable(String table) throws Exception;

    
    public ArrayList<Object[]> getColonnes(String nomTable)throws SQLException{
        
        //get info de la table, nombre de colonnes        
        resultSet = dbMetadata.getColumns(null, null, nomTable, null);   
        ArrayList<Object[]> tab = new ArrayList<>();
        
        //compteur pour l'affichage du numéro du champ
        int compteur = 1;
        
        //on récupere les clés primaires et etrangeres
        ArrayList<String> clefsPrimaire = getPrimaryKey(nomTable);
        ArrayList<Hashtable> clefsEtrangere = getForeignKey(nomTable);
        
        //ajout des noms de colonnes au tableau de string
        while(resultSet.next()){
            
            Object[] data = new Object[8]; 
            
            //numero de la colonne
            data[0] = compteur;
            
            //nom de la colonne
            data[1] = resultSet.getString("COLUMN_NAME");
            
            //type de la colonne
            data[2] = resultSet.getString("TYPE_NAME");
            
            //on teste si la colonne actuelle est une clé primaire
            data[3] = false;           
            for(int i = 0; i < clefsPrimaire.size(); i++) {                 
                if(!(boolean)data[3]){
                    data[3] = clefsPrimaire.get(i).equals(data[1]);
                }                 
            }  
                 
            //test si la colonne actuelle fait parti de la ou des cléfs etrangeres
            data[4] = "";           
            for (int i = 0; i < clefsEtrangere.size(); i++) {
                if(data[4].equals("")){
                    if (clefsEtrangere.get(i).get("FKCOLUMN_NAME").equals(data[1])){
                        data[4] = clefsEtrangere.get(i).get("PKTABLE_NAME") + "(" + clefsEtrangere.get(i).get("PKCOLUMN_NAME") + ")";
                    }            
                }               
            }
            
            
            // /!\ A CORRIGER FONCTIONNE UNIQUEMENT AVEC LES CLE PRIMAIRES!!! /!\
            //la colonne est nullable ou non 
            data[5] = !resultSet.getBoolean("NULLABLE");
            
            //valeur par défaut de la colonne
            data[6] = resultSet.getString("COLUMN_DEF");
            
            //FONCTION A AJOUTER: CONTRAINTE UNIQUE
            
            //on ajoute les infos de la colonne a l'arraylist retourné + incrémentation du compteur
            tab.add(data);
            compteur++;
        }   
        return tab;
    }
    
    
    //methode retournant un arraylist des noms des cléfs primaires
    private ArrayList<String> getPrimaryKey(String nomTable) throws SQLException {
              
        ResultSet rs = dbMetadata.getPrimaryKeys(null, null, nomTable);
        
        ArrayList<String> tab = new ArrayList<>();
  
        //on rempli l'arraylist des nom des clés primaires
        while(rs.next()) {
            tab.add(rs.getString("COLUMN_NAME"));             
        }      
        return tab;
    }
   
    
    
    //methode retournant un tableau de Hashtable contenant pour chacun des éléments : 
    //le nom de la colonne , la clé primaire sur laquelle elle pointe et la table sur laqelle elle pointe.
    private ArrayList<Hashtable> getForeignKey(String nomTable) throws SQLException {
        
        ResultSet clefs = dbMetadata.getImportedKeys(null, null, nomTable);
        
        ArrayList<Hashtable> tab = new ArrayList<>();
         
        //on rempli l'arraylist des hashtable des foreign keys
        while (clefs.next()) {
            Hashtable<String, String> h = new Hashtable<>();
            h.put("FKCOLUMN_NAME",clefs.getString("FKCOLUMN_NAME")) ;
            h.put("PKCOLUMN_NAME", clefs.getString("PKCOLUMN_NAME"));
            h.put("PKTABLE_NAME", clefs.getString("PKTABLE_NAME"));
            
            tab.add(h);
        }
        return tab;
    }
  
   
    /*
    METHODE SELECT PAR NOM DE TABLE JE LA LAISSE ON VA SUREMENT SEN SERVIR PLUS TARD
    
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
    */

    
    //methode qui retourne la liste des tables
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

    
    /*
    METHODE QUI FERME LA CONNEXION ON LUTILISE PAS ENCORE MAIS EN VRAI FAUDRAI
    
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
    */

}
