package model;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 *
 * @author bdurga
 */
public class PatientDirectory {

    private ArrayList<Test> patientList;
    
    
    private String UpdatedDate;
    
    public PatientDirectory() throws ParseException {
       
        this.patientList = new ArrayList();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        
      
        
    }
    
    public ArrayList<Test> getPatientList() {
        return patientList;
    }
    
    public void setPatientList(ArrayList<Test> patientList) {
        this.patientList = patientList;
        
    }
    
    public String getUpdatedDate() {
        return UpdatedDate;
    }
    
    public void setUpdatedDate(String UpdatedDate) {
        this.UpdatedDate = UpdatedDate;
    }
    
    public Test addCar() {
        Test p = new Test();
        p.setLastencounter(new Date());
        patientList.add(p);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();   
        setUpdatedDate(dtf.format(now));
        return p;
    }
    
    public void deleteAccount(Test test) {
        patientList.remove(test);
        updatedTime();
    }
    
    public void updatedTime() {
        List<Date> datesList = new ArrayList<>();
        for (Test p : patientList) {
            datesList.add(p.getLastencounter());
        }
        Collections.sort(datesList, Comparator.reverseOrder());
        setUpdatedDate(datesList.get(0).toString());
    }
}
       