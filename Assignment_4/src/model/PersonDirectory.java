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
public class PersonDirectory {

    ArrayList<Person> directory;
    
    public PersonDirectory() {
        directory = new ArrayList<Person>();
    }

    public ArrayList<Person> getDirectory() {
        return directory;
    }
    
    public Person newPerson(){
        Person person = new Person();
        person.initializePerson();
        directory.add(person);
        return person;
    }
    
        @Override
    public String toString(){
        String returnString = new String();
        int i = 0;
        for (Person person: directory){
            returnString += "Patient "+i+":\n";
            returnString += person + "\n";
            i++;
        }
        
        return returnString;
    }
    
}
