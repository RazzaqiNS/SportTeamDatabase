package com.example.sportteamdatabasebyrazzaqins;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Team_ModelRealm extends RealmObject{
    @PrimaryKey
    private String TeamName;
    private String Country;
    private String Badge;
    private String TeamDescription;
    private Integer idTeam;

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


    public Integer getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(Integer idTeam) {
        this.idTeam = idTeam;
    }

    public String getTeamDescription() {
        return TeamDescription;
    }

    public void setTeamDescription(String teamDescription) {
        TeamDescription = teamDescription;
    }

}
