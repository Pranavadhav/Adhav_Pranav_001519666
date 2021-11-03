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

public class Community extends City{

    String communityName;

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }
    
    @Override
    public String toString(){
        return "\nCommunity : " + this.communityName + super.toString();
    }
    
}
