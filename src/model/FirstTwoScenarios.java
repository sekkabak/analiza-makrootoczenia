package model;

import app.Helper;
import layout.AvgGetter;

import java.util.ArrayList;

public class FirstTwoScenarios {
    public Area area;
    public ArrayList<String> infuences;
    public String average;

    public AvgGetter getAverage = this::calculateAvarage;

    public FirstTwoScenarios(Area area) {
        this.area = area;
        this.infuences = new ArrayList<>();

        for(int i = 0; i <  this.area.factors.size(); i++) {
            this.infuences.add("");
        }

        this.average = "0";
    }

    public void setInfuences(SIPair pair) {
        this.infuences.set(pair.integer, pair.string);
    }

    public String calculateAvarage() {
        double avg = 0.0;
        for (String x : infuences) {
            try {
                avg += Double.parseDouble(x);
            } catch (Exception ignored) {
            }
        }
        avg /= infuences.size();
        return average = Helper.getValidAverageForm(avg);
    }
}
