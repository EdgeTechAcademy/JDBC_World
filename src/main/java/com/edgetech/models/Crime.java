package com.edgetech.models;

public class Crime {

    String  reportDate;
    String	offenseDesc;
    String  descriptionCd;
    String  borough;
    String  locationOfOccurence;
    String  premiseType;
    String  parksName;

    public String getReportDate() {
        return reportDate;
    }
    public String getOffenseDesc() {
        return offenseDesc;
    }
    public String getDescriptionCd() {
        return descriptionCd;
    }
    public String getBorough() {
        return borough;
    }
    public String getLocationOfOccurence() {
        return locationOfOccurence;
    }
    public String getPremiseType() {
        return premiseType;
    }
    public String getParksName() {
        return parksName;
    }

    public Crime(String reportDate, String	offenseDesc, String descriptionCd, String borough,
                 String locationOfOccurence, String premiseType, String parksName) {
        this.reportDate = reportDate;
        this.offenseDesc = offenseDesc;
        this.descriptionCd = descriptionCd;
        this.borough = borough;
        this.locationOfOccurence = locationOfOccurence;
        this.premiseType = premiseType;
        this.parksName = parksName;
    }
}
