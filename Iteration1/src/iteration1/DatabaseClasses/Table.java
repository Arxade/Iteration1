/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iteration1.DatabaseClasses;

import java.util.ArrayList;


/**
 *
 * @author Kazed
 */
public class Table {
    private final String name;
    private ArrayList<Attribute> attributes;
    
    public Table(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public ArrayList<Attribute> getAttributes() {
        return attributes;
    }
}
