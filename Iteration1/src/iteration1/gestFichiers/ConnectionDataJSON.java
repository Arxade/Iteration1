/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iteration1.gestFichiers;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Hashtable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Paul
 */
public class ConnectionDataJSON {
    
    
    public void saveJSON(Hashtable <String, String> h){
        
        try{
            //recup des infos de la hashtable pour les mettre dans data
            JSONObject data = new JSONObject();
            
            data.put("URL", h.get("URL"));
            data.put("login", h.get("login"));
            data.put("mdp", h.get("mdp"));
            data.put("BDD", h.get("BDD"));
            data.put("isChecked", h.get("isChecked"));

            


            //ecriture du fichier connectData.json
            FileWriter file = new FileWriter("connectData.json");

            file.write(data.toJSONString());
            file.flush(); 
        }
        catch(Exception e){   
            
        }
    }   
    
    
    
    public Hashtable<String, String> getJson(){
        
        Hashtable<String, String> h = new Hashtable<>();
        
        try{
        //on recup le fihier JSON
      
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("connectData.json"));
        JSONObject data = (JSONObject) obj;
        
        
        
        
        //on associe au hashtable les valeur du json
        h.put("URL",(String) data.get("URL"));
        h.put("login",(String) data.get("login"));
        h.put("mdp",(String) data.get("mdp"));
        h.put("BDD",(String) data.get("BDD"));
        h.put("isChecked",(String) data.get("isChecked"));
        
        return h;
 
        }
        catch(Exception e){
            h.clear();
            h.put("erreur", e.getMessage());
            System.out.println(e.getMessage());
            return h;
        } 
    }
    
    //on teste l'existence du json
    public boolean jsonExist(){
        try {
            FileReader fr = new FileReader("connectData.json");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
