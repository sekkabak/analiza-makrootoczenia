package model;

import java.util.ArrayList;

public class DataManager {
    private Area currentArea;
    public ArrayList<Area> areas;

    public ArrayList<FirstTwoScenarios> firstTwoScenarios;
    public ArrayList<SecondTwoScenarios> secondTwoScenarios;

    public DataManager() {
        this.areas = new ArrayList<>();

        firstTwoScenarios = new ArrayList<>();
        secondTwoScenarios = new ArrayList<>();
    }

    public void setCurrentArea(Area area) {
        currentArea = area;
    }

    public Area getCurrentArea() {
        if(currentArea == null)
            return currentArea = areas.get(0);

        return currentArea;
    }
}
