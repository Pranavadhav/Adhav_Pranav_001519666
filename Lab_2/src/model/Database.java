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
public class Database {

   

    private final Object[][] databseData;


    public Object[][] getDatabseData() {
        return databseData;
    }
    
    public Database() {
        this.databseData = new Object[][]
        {
            {"toyota","innova", 2020,5,12345,"boston","2022-08-10","yes"},
            {"bmw", "x6",2018,2,22334,"new york","2025-05-12","yes"},
            {"ford", "mustang",2019,4,67894,"dallas","2019-05-10","no"},
            {"audi", "x6",2015,5,12012,"new york","2032-03-10","no"},
            {"bmw", "X3",2018,5,12345,"las vegas","2024-02-10","yes"},
            {"ferrari","zl", 2012,3,12645,"chicago","2018-01-15","yes"}
        };
    }
    
    
    
}
