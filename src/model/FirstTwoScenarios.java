package model;

import java.util.ArrayList;

public class FirstTwoScenarios {
    public Area area;
    public ArrayList<String> infuences;
    public double average;

    public FirstTwoScenarios(Area area) {
        this.area = area;
        this.infuences = new ArrayList<>();

        this.average = 0.0;
    }

    public double calculateAvarage() throws NumberFormatException {
        average = 0.0;
        for (String x : infuences) {
           average += Double.parseDouble(x);
        }

        return average;
    }
}
