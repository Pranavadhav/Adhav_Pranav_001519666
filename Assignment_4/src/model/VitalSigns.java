/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.Scanner;

/**
 *
 * @author bdurga
 */
public class VitalSigns {

    double respiratoryRate;
    double heartRate;
    double systolicBP;
    double weightInKilos;
    double weightInPounds;
    Boolean areVitalSignsNormal;
    
//    Boolean isRespiratoryRateNormal;
//    Boolean isHeartRateNormal;
//    Boolean isSystolicBPNormal;
//    Boolean isWeightNormal;
    
    public VitalSigns(){
        Boolean good = false;
        Scanner scanner = new Scanner(System.in);
        while(!good) {
            try {
                scanner = new Scanner(System.in);
                System.out.println("\nEnter following Vital Sign Details: \n");
                System.out.println("RespiratoryRate\t\tHeartRate\tSystolicBP\tWeightInKilos");
                this.respiratoryRate = scanner.nextDouble();
                this.heartRate = scanner.nextDouble();
                this.systolicBP = scanner.nextDouble();
                this.weightInKilos = scanner.nextDouble();
                this.weightInPounds = this.weightInKilos * 2.20462262;
                good = true;
            }
            catch(Exception e){
                System.out.println("Enter values in correct format.\n");
            }
        }
//        this.vitalSignDatetime = LocalDateTime.now();
    }
    
    public VitalSigns(double respiratoryRate, double heartRate, double systolicBP, double weightInKilos) { //, double weightInPounds){
        this.respiratoryRate = respiratoryRate;
        this.heartRate = heartRate;
        this.systolicBP = systolicBP;
        this.weightInKilos = weightInKilos;
        this.weightInPounds = weightInKilos * 2.20462262;
//        this.vitalSignDatetime = LocalDateTime.now();
        
    }
    
    public void updateVitalSigns(double respiratoryRate, double heartRate, double systolicBP, double weightInKilos) { //, double weightInPounds){
        this.respiratoryRate = respiratoryRate;
        this.heartRate = heartRate;
        this.systolicBP = systolicBP;
        this.weightInKilos = weightInKilos;
        this.weightInPounds = weightInKilos * 2.20462262;
//        this.vitalSignDatetime = LocalDateTime.now();
    }

    public double getRespiratoryRate() {
        return respiratoryRate;
    }

    public void setRespiratoryRate(double respiratoryRate) {
        this.respiratoryRate = respiratoryRate;
    }

    public double getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(double heartRate) {
        this.heartRate = heartRate;
    }

    public double getSystolicBP() {
        return systolicBP;
    }

    public void setSystolicBP(double systolicBP) {
        this.systolicBP = systolicBP;
    }

    public double getWeightInKilos() {
        return weightInKilos;
    }

    public void setWeightInKilos(double weightInKilos) {
        this.weightInKilos = weightInKilos;
    }

    public double getWeightInPounds() {
        return weightInPounds;
    }

    public void setWeightInPounds(double weightInPounds) {
        this.weightInPounds = weightInPounds;
    }

    public Boolean getAreVitalSignsNormal() {
        return areVitalSignsNormal;
    }

    public void setAreVitalSignsNormal(Boolean areVitalSignsNormal) {
        this.areVitalSignsNormal = areVitalSignsNormal;
    }

    
    
    Boolean isRespiratoryRateNormal(Patient patient){
        
        VitalSignsNormal vitalSignsNormal = new VitalSignsNormal();
        VitalSigns vitalSigns = patient.getVitalSigns();
        String ageGroup = patient.getAgeGroup();
        
        return vitalSigns.respiratoryRate >= Double.parseDouble(vitalSignsNormal.respiratoryRate.get(ageGroup).split("-")[0]) && 
                vitalSigns.respiratoryRate <= Double.parseDouble(vitalSignsNormal.respiratoryRate.get(ageGroup).split("-")[1]);
    }
    
    Boolean isHeartRateNormal(Patient patient){
        
        VitalSignsNormal vitalSignsNormal = new VitalSignsNormal();
        VitalSigns vitalSigns = patient.getVitalSigns();
        String ageGroup = patient.getAgeGroup();
        
        return vitalSigns.heartRate >= Double.parseDouble(vitalSignsNormal.heartRate.get(ageGroup).split("-")[0]) && 
                vitalSigns.heartRate <= Double.parseDouble(vitalSignsNormal.heartRate.get(ageGroup).split("-")[1]);
    }
    
    Boolean isSystolicBPNormal(Patient patient){
        
        VitalSignsNormal vitalSignsNormal = new VitalSignsNormal();
        VitalSigns vitalSigns = patient.getVitalSigns();
        String ageGroup = patient.getAgeGroup();
        
        return vitalSigns.systolicBP >= Double.parseDouble(vitalSignsNormal.systolicBP.get(ageGroup).split("-")[0]) && 
                vitalSigns.systolicBP <= Double.parseDouble(vitalSignsNormal.systolicBP.get(ageGroup).split("-")[1]);
    }
    
    Boolean isWeightNormal(Patient patient){
        
        VitalSignsNormal vitalSignsNormal = new VitalSignsNormal();
        VitalSigns vitalSigns = patient.getVitalSigns();
        String ageGroup = patient.getAgeGroup();
        
        return vitalSigns.weightInKilos >= Double.parseDouble(vitalSignsNormal.weightInKilos.get(ageGroup).split("-")[0]) && 
                vitalSigns.weightInKilos <= Double.parseDouble(vitalSignsNormal.weightInKilos.get(ageGroup).split("-")[1]);
    }

    Boolean areVitalSignsNormal(Patient patient) {
//        return compareVitalSigns(patient.getVitalSigns(),patient.getAgeGroup()) == 0;
        return isRespiratoryRateNormal(patient) && isHeartRateNormal(patient) && isSystolicBPNormal(patient) && isWeightNormal(patient);
    }

    private int compareVitalSigns(VitalSigns vitalSigns, String ageGroup) {
        int result = 0;
        
        VitalSignsNormal vitalSignsNormal = new VitalSignsNormal();
        
        if(vitalSigns.respiratoryRate >= Double.parseDouble(vitalSignsNormal.respiratoryRate.get(ageGroup).split("-")[0]) && 
                vitalSigns.respiratoryRate <= Double.parseDouble(vitalSignsNormal.respiratoryRate.get(ageGroup).split("-")[1]))
            result = 0;
        
        else 
            return 1;
        
        if(vitalSigns.heartRate >= Double.parseDouble(vitalSignsNormal.heartRate.get(ageGroup).split("-")[0]) && 
                vitalSigns.heartRate <= Double.parseDouble(vitalSignsNormal.heartRate.get(ageGroup).split("-")[1]))
            result = 0;
        else 
            return 2;
        
        if(vitalSigns.systolicBP >= Double.parseDouble(vitalSignsNormal.systolicBP.get(ageGroup).split("-")[0]) && 
                vitalSigns.systolicBP <= Double.parseDouble(vitalSignsNormal.systolicBP.get(ageGroup).split("-")[1]))
            result = 0;
        else 
            return 3;
        
        if(vitalSigns.weightInKilos >= Double.parseDouble(vitalSignsNormal.weightInKilos.get(ageGroup).split("-")[0]) && 
                vitalSigns.weightInKilos <= Double.parseDouble(vitalSignsNormal.weightInKilos.get(ageGroup).split("-")[1]))
            result = 0;
        else 
            return 4;
        
        if(vitalSigns.weightInPounds >= Double.parseDouble(vitalSignsNormal.weightInPounds.get(ageGroup).split("-")[0]) && 
                vitalSigns.weightInPounds <= Double.parseDouble(vitalSignsNormal.weightInPounds.get(ageGroup).split("-")[1]))
            result = 0;
        else 
            return 5;
        
        return result;
    }
    
    private int compareVitalSigns(Patient patient) {
        int result = 0;
        
        if(!isRespiratoryRateNormal(patient))
            return 1;
        
        if(!isHeartRateNormal(patient))
            return 2;
        
        if(!isSystolicBPNormal(patient))
            return 3;
        
        if(!isWeightNormal(patient))
            return 4;
        
        return result;
    }
    
    @Override
    public String toString(){
        return //"  DateTime: " + this.vitalSignDatetime + 
                "\n  Respiratory Rate: "+ this.respiratoryRate + "\n  Heart Rate: "+this.heartRate
                + "\n  Systolic Blood Pressure: "+this.systolicBP+"\n  Weight(KG): "+this.weightInKilos
                + String.format("\n  Weight(lbs): %6.2f", this.weightInPounds);
    }

    Boolean isThisVitalSignNormal(Patient patient, String vitalSign) {
      
        if(vitalSign.toLowerCase().equals("Respiratory Rate".toLowerCase()))
            return isRespiratoryRateNormal(patient);
        else if (vitalSign.toLowerCase().equals("Heart Rate".toLowerCase()))
            return isHeartRateNormal(patient);
        else if (vitalSign.toLowerCase().equals("SystolicBP".toLowerCase()))
            return isSystolicBPNormal(patient);
        else if (vitalSign.toLowerCase().equals("Weight".toLowerCase()))
            return isWeightNormal(patient);
        else
            System.out.println("Given Vital Sign details are not found.\n");
        
//        switch (vitalSign) {
//            case "Respiratory Rate":
//                return isRespiratoryRateNormal(patient);
//            case "Heart Rate":
//                return isHeartRateNormal(patient);
//            case "SystolicBP":
//                return isSystolicBPNormal(patient);
//            case "Weight":
//                return isWeightNormal(patient);
//            default:
//                System.out.println("Given Vital Sign details are not found.\n");
//                break;
//        }
        
        return null;
    }
    
}
