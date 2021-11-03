/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kishore
 */
public class VitalSignsNormal {
    
    Map<String, String> respiratoryRate;
    Map<String, String> heartRate;
    Map<String, String> systolicBP;
    Map<String, String> weightInKilos;
    Map<String, String> weightInPounds;
    
    public VitalSignsNormal(){
        
        respiratoryRate = new HashMap<String, String>() {
            {
                put("NewBorn","30-50");
                put("Infant","20-30");
                put("Toddler","20-30");
                put("Preschooler","20-30");
                put("SchoolAge","20-30");
                put("Adolescent","12-20");
            }
        };
        
        heartRate = new HashMap<String, String>() {
            {
                put("NewBorn","120-160");
                put("Infant","80-140");
                put("Toddler","80-130");
                put("Preschooler","80-120");
                put("SchoolAge","70-110");
                put("Adolescent","55-105");
            }
        };
        
        systolicBP = new HashMap<String, String>() {
            {
                put("NewBorn","50-70");
                put("Infant","70-100");
                put("Toddler","80-110");
                put("Preschooler","80-110");
                put("SchoolAge","80-120");
                put("Adolescent","110-120");
            }
        };
        
        weightInKilos = new HashMap<String, String>() {
            {
                put("NewBorn","2-3");
                put("Infant","4-10");
                put("Toddler","10-14");
                put("Preschooler","14-18");
                put("SchoolAge","20-42");
                put("Adolescent","50.00001-9999");
            }
        };
        
        weightInPounds = new HashMap<String, String>() {
            {
                put("NewBorn","4.5-7");
                put("Infant","9-22");
                put("Toddler","22-31");
                put("Preschooler","31-40");
                put("SchoolAge","41-92");
                put("Adolescent","110.0001-24999");
            }
        };
    }
    
}
