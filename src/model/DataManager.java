package model;

import java.util.ArrayList;

public class DataManager {
    private Area currentArea;
    public ArrayList<Area> areas;

    public ArrayList<FirstTwoScenarios> optimisticScenario;
    public ArrayList<FirstTwoScenarios> pesimisticScenario;
    public ArrayList<SecondTwoScenarios> mostLikelyScenario;
    public ArrayList<SecondTwoScenarios> unexpectedScenario;

    public String creator;
    public String date;
    public String conclusions;

    public DataManager() {
        this.areas = new ArrayList<>();

        optimisticScenario = new ArrayList<>();
        pesimisticScenario = new ArrayList<>();
        mostLikelyScenario = new ArrayList<>();
        unexpectedScenario = new ArrayList<>();

        conclusions = "";
        creator = "";
        date = "";
    }

    public void setCurrentArea(Area area) {
        currentArea = area;
    }

    public Area getCurrentArea() {
        if(currentArea == null)
            return currentArea = areas.get(0);

        return currentArea;
    }

    public void clearScenarios() {
        optimisticScenario.clear();
        pesimisticScenario.clear();
        mostLikelyScenario.clear();
        unexpectedScenario.clear();
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setConclusions(String conclusions) {
        this.conclusions = conclusions;
    }
}
