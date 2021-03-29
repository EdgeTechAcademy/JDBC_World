package com.edgetech.controllers;

import com.edgetech.models.Crime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CrimeController {

    Connection con;

    public CrimeController() {
        //String url = "jdbc:mysql://localhost:3306/Crime?useSSL=false"; //	user=root;password=password";
        String url = "jdbc:sqlserver://localhost:1433;databaseName=NYCrime;";

        try {
            con = DriverManager.getConnection(url, "root", "password");
        } catch (Exception except) {
            System.out.println(except.getMessage());
            SQLException ex = new SQLException("Connection Failed: " + except.getMessage());
        }
    }

    @RequestMapping(value = "crimes", method = RequestMethod.GET)
    public String crimeStart(Model model) throws Exception {
        model.addAttribute("title", "First JDBC Application");
//        model.addAttribute("crimes", []);
        return "crimes";
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String crimeList(@RequestParam String description, Model model) throws Exception {

        List<Crime> crimes = new ArrayList<>();
        try {
            String query = "SELECT top 100 ReportDate, OffenseDesc, DescriptionCd, " +
                    " Borough, LocationOfOccurrence, PremiseType, ParksName " +
                    " FROM NewYork ";
            if (description != null && description.length() > 0) {
                query += "WHERE OffenseDesc like '%" + description + "%' ";
            }
            //query += " ORDER BY ComplaintNum ";
            System.out.println(query);

            Statement stmt = con.createStatement();
            ResultSet results = stmt.executeQuery(query);

            //  retrieve all rows from the result set
            while (results.next()) {
                    Crime crime = new Crime( results.getString("reportDate"), results.getString("offenseDesc"),
                            results.getString("descriptionCd"), results.getString("borough"), results.getString("locationOfOccurrence"),
                            results.getString("premiseType"), results.getString("parksName"));
                crimes.add(crime);                       //  this will be our list of crimes to save to our web page
            }
        } catch (Exception except) {
            System.out.println(except.getMessage());
            SQLException ex = new SQLException("Query or Connection Failed: " + except.getMessage());
        }
        model.addAttribute("title", "First JDBC Application");
        model.addAttribute("crimes", crimes);
        return "crimes";
    }
}
