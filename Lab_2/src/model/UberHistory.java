/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author pranav
 */
public class UberHistory {
    private ArrayList<Uber> history ;
    
    public UberHistory(){
    
    this.history = new ArrayList<Uber>();
    }

    public ArrayList<Uber> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<Uber> history) {
        this.history = history;
    }
    
    public Uber addNewData () {
        Uber newData = new Uber(); 
        history.add(newData);
        return newData ;
    }
    public void deleteData(Uber ub){
    history.remove(ub);
    }
}
