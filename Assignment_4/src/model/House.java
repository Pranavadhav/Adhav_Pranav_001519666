/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package model;
/**
 *
 * @author kishore
 */
public class House extends Community{

    String houseNum;
    
    public String getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(String houseNum) {
        this.houseNum = houseNum;
    }
    
    @Override
    public String toString(){
        return "\nHouse Number : " + this.houseNum + super.toString();
    }
    
}
