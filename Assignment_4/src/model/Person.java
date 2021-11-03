/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

/**
 *
 * @author kishore
 */
public class Person extends House{

    String firstName;
    String lastName;
    LocalDate dob;
    String ageGroup;

    public Person(){
        
    }
    
    public void initializePerson() {
        Scanner scanner = new Scanner(System.in);
        updateInfo();
        System.out.println("Enter House No: ");
        this.houseNum = scanner.nextLine();
        
        System.out.println("Enter Community: ");
        this.communityName = scanner.nextLine();
        
        System.out.println("Enter City: ");
        this.cityName = scanner.nextLine();
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getFullName() {
        return firstName + " " + lastName;
    }

    public LocalDate getDOB() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
        Period age = Period.between(dob, LocalDate.now());
        if(age.getYears() == 0 && age.getMonths() == 0){
            this.setAgeGroup("Newborn");
        }
        else if(age.getYears() == 0 && age.getMonths() <= 11 && age.getDays() <= 31){
            this.setAgeGroup("Infant");
        }
        else if(age.getYears() < 3 && age.getMonths() <= 11 && age.getDays() <= 31) {
            this.setAgeGroup("Toddler");
        }
        else if(age.getYears() < 5 && age.getMonths() <= 11 && age.getDays() <= 31) {
            this.setAgeGroup("Preschooler");
        }
        else if(age.getYears() < 12 && age.getMonths() <= 11 && age.getDays() <= 31){
            this.setAgeGroup("SchoolAge");
        }
        else {
            this.setAgeGroup("Adolescent");
        }
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }
    
    void updateInfo() {
        System.out.println("\nEnter Person First Name: ");
        Scanner scanner = new Scanner(System.in);
        this.firstName = scanner.nextLine();
        System.out.println("\nEnter Person Last Name: ");
        this.lastName = scanner.nextLine();
        System.out.println("\nEnter DOB (YYYY-MM-DD): ");
        Boolean goodDate = false;
        while(!goodDate) {
            try {
                this.setDob(LocalDate.parse(scanner.nextLine()));
                goodDate = true;
                if(Period.between(dob, LocalDate.now()).getDays() < 0 ||
                        Period.between(dob, LocalDate.now()).getMonths() < 0 ||
                        Period.between(dob, LocalDate.now()).getYears() < 0)
                {
                    System.out.println("\nWhy are you like this? Enter the past date not future.");
                    System.out.print("Enter DOB in correct format (YYYY-MM-DD): ");
                    goodDate = false;
                }
            }
            catch (Exception e) {
                System.out.print("\nEnter DOB in correct format (YYYY-MM-DD): ");
            }
        }
    }    
        
    public String toString(){
        return "\nName: " + this.getFullName() + "\nDOB: " + this.dob + "(Age Group: " + this.ageGroup + ")"
                + super.toString();
    }
    
}
