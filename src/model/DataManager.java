package model;

import java.util.ArrayList;

public class DataManager {
    private Area currentArea;
    public ArrayList<Area> areas;

    public DataManager() {
        this.areas = new ArrayList<>();
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
