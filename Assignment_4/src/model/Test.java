package model;

import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author kishore
 */
public class Test{
    
    String firstname;
    String lastname;
    int dob;
    String comm;
    String city;
    Date lastencounter;
    String number;
    int bp;
    String email;
    String status;
    String agegroup;
    int encounter;

    public int getEncounter() {
        return encounter;
    }

    public void setEncounter(int encounter) {
        this.encounter = encounter;
    }
   
  
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAgegroup() {
        return agegroup;
    }

    public void setAgegroup(String agegroup) {
        this.agegroup = agegroup;
    }
    
    
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getBp() {
        return bp;
    }

    public void setBp(int bp) {
        this.bp = bp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
   
    Test(String firstname, String lastname, int dob,String number, String email, String comm,String city, Date lastencounter, int bp){
        this.firstname= firstname;
        this.lastname = lastname;
        this.dob = dob;
        this.email = email;
        this.number = number;
        this.comm = comm;
        this.city=city;
        this.lastencounter = lastencounter;  
        this.bp = bp;
        this.encounter = 1;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getDob() {
        return dob;
    }

    public void setDob(int dob) {
        this.dob = dob;
    }

    public String getComm() {
        return comm;
    }

    public void setComm(String comm) {
        this.comm = comm;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getLastencounter() {
        return lastencounter;
    }

    public void setLastencounter(Date lastencounter) {
        this.lastencounter = lastencounter;
    }
    
    
    Test(){
        
    }
      
}
