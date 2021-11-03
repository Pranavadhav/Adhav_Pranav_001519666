/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.ArrayList;

/**
 *
 * @author kishore
 */
class EncounterHistory {
    
    ArrayList<Encounter> history;
    
    public EncounterHistory() {
        history = new ArrayList<Encounter>();
    } 

    public ArrayList<Encounter> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<Encounter> history) {
        this.history = history;
    }
    
    public void printHistory() {
        System.out.println(this);
    }
    
    public String toString() {
        String returnString = new String();
        returnString += "";
        int i = 1;
        for (Encounter visit: getHistory()) {
            returnString+="\nEncounter "+i+": \n";
            returnString += visit;
            i++;
        }
        return returnString;
    }
    
}
