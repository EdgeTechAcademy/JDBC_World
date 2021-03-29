package com.edgetech.controllers;

import com.edgetech.models.City;
import com.edgetech.models.Country;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    Connection con;         //  this is my connection to the database

    public IndexController() {      //  controller constructor. Gets called by SpringBoot when the web app is up and ready to go
//        String url = "jdbc:mysql://localhost:3306/world?useSSL=false"; //	user=root;password=password";
        String url = "jdbc:sqlserver://localhost:1433;databaseName=world;";

        try {
            con = DriverManager.getConnection(url, "root", "password");         //  connect to the database
        } catch (Exception except) {
            System.out.println(except.getMessage());
            SQLException ex = new SQLException("Connection Failed: " + except.getMessage());
        }
    }

    //  http://localhost:8080/districts?district
    //  http://localhost:8080/districts?district=Utah
    @RequestMapping("districts")                //  listen for the browser to request the /districts resource
    public String index(@RequestParam String district, Model model) throws Exception {
        //  begin with the end in mind
        //  at the end of this method I need all of the data to pass
        //      back to the web page
        //  so we need a place to put that data
        List<City> cities = new ArrayList<>();      //  all cities we found
        int totalPop = 0;                           //  get total pop of all cities

        try {
            String query = "SELECT Name, District, CountryCode, Population " +
                            " FROM City ";
            if (district != null && district.length() > 0) {
                query += "WHERE District like '%" + district + "%' ";
            }
            query += "ORDER BY Name";
            System.out.println(query);

            Statement stmt = con.createStatement();         //  create a sql statement
            ResultSet results = stmt.executeQuery(query);   //  execute it

            //  retrieve all rows from the result set
            while (results.next()) {
                totalPop += results.getInt("population");        //  sum up the population for the cities in the result set
                City city = new City(results.getString("name"),
                                     results.getString("district"),
                                     results.getInt("population"));
                cities.add(city);                       //  this will be our list of cities to save to our web page
            }
        } catch (Exception except) {
            System.out.println(except.getMessage());
            SQLException ex = new SQLException("Query or Connection Failed: " + except.getMessage());
        }
                        //  tag data with a name for the HTML (Thymeleaf) to put on web page
                                            //  this is the data
        model.addAttribute("title",         "First JDBC Application");
        model.addAttribute("listOfCities",  cities);
        model.addAttribute("population",    totalPop);
        model.addAttribute("district",      district);
        model.addAttribute("dude",      "where's my data?");
        return "cities";
    }

    //  http://localhost:8080/districts?district
    //  http://localhost:8080/districts?district=Utah
    @RequestMapping("countries")
    public String country(@RequestParam String region, Model model) throws Exception {

        List<Country> countries = new ArrayList<>();
        int population = 0;

        try {
            String query = "SELECT Code, Country.Name, HeadOfState, IndepYear, Region, country.population, Continent, city.name as capital" +
                    " FROM Country " +
                    " join city on Capital = city.ID ";
            if (region != null && region.length() > 0) {
                query += "WHERE region like '%" + region + "%' ";
            }
            query += "ORDER BY Name";
            System.out.println(query);

            Statement stmt = con.createStatement();
            ResultSet results = stmt.executeQuery(query);

            //  retrieve all rows from the result set
            while (results.next()) {
                population += results.getInt("population");        //  sum up the population for the cities in the result set
                    Country country = new Country(              results.getString("name"),
                            results.getString("region"),        results.getString("continent"),
                            results.getString("headOfState"),   results.getString("code"),
                            results.getInt("IndepYear"),        results.getString("capital"));
                countries.add(country);                       //  this will be our list of cities to save to our web page
            }
        } catch (Exception except) {
            System.out.println(except.getMessage());
            SQLException ex = new SQLException("Query or Connection Failed: " + except.getMessage());
        }
        model.addAttribute("title", "First JDBC Application");
        model.addAttribute("countries", countries);
        model.addAttribute("population", population);
        model.addAttribute("region", region);
        return "countries";
    }

}
