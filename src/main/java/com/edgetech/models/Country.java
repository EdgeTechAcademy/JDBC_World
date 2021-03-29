package com.edgetech.models;

public class Country {
    String name;
    String region;
    String continent;
    String headOfState;
    String governmentForm;
    String code;
    String capital;
    int population;
    int surfaceArea;
    int indepYear;
    int GNP;

    public Country(String name, String region, String continent, String headOfState, String code, int population, String capital) {
        this.name = name;
        this.region = region;
        this.continent = continent;
        this.headOfState = headOfState;
        this.code = code;
        this.population = population;
        this.capital = capital;
    }

    public String getCapital() {
        return capital;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getHeadOfState() {
        return headOfState;
    }

    public void setHeadOfState(String headOfState) {
        this.headOfState = headOfState;
    }

    public String getGovernmentForm() {
        return governmentForm;
    }

    public void setGovernmentForm(String governmentForm) {
        this.governmentForm = governmentForm;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getSurfaceArea() {
        return surfaceArea;
    }

    public void setSurfaceArea(int surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    public int getIndepYear() {
        return indepYear;
    }

    public void setIndepYear(int indepYear) {
        this.indepYear = indepYear;
    }

    public int getGNP() {
        return GNP;
    }

    public void setGNP(int GNP) {
        this.GNP = GNP;
    }

    public Country() {

    }
}
