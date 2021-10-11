/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author pranav
 */
public class Uber {
    private String manufacturer;
    private int year;
    private int seat;
    private long serial;
    private String model;
    private String city;
    private String date ;
    private String availabel;

    public String getAvailabel() {
        return availabel;
    }

    public void setAvailabel(String availabel) {
        this.availabel = availabel;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public long getSerial() {
        return serial;
    }

    public void setSerial(long serial) {
        this.serial = serial;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getdate() {
        return date;
    }

    public void setdate(String date) {
        this.date = date;
    }
    
    @Override
    public String toString(){
    return manufacturer;
    }
    
}
