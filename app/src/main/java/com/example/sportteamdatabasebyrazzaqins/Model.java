package com.example.sportteamdatabasebyrazzaqins;

public class Model {

     String TeamName;
    String Country;
    String Badge;
    String TeamDescription;
     int idTeam;


    public String getTeamName() {
        return TeamName;
    }

    public void setTeamName(String teamName) {
        TeamName = teamName;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getBadge() {
        return Badge;
    }

    public void setBadge(String badge) {
        Badge = badge;
    }

    public int getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(int idTeam) {
        this.idTeam = idTeam;
    }

    public String getTeamDescription() {
        return TeamDescription;
    }

    public void setTeamDescription(String teamDescription) {
        TeamDescription = teamDescription;
    }


}
