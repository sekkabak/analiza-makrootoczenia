package model;

import java.util.ArrayList;

public class SecondTwoScenarios {
    public Area area;

    public ArrayList<String> probability;
    public ArrayList<String> negativeInfuences;
    public ArrayList<String> positiveInfuences;

    public double negativeInfuencesAverage;
    public double positiveInfuencesAverage;

    public SecondTwoScenarios(Area area) {
        this.area = area;

        this.probability = new ArrayList<>();
        this.negativeInfuences = new ArrayList<>();
        this.positiveInfuences = new ArrayList<>();

        negativeInfuencesAverage = 0.0;
        positiveInfuencesAverage = 0.0;
    }

    public void calculateAvarages() throws NumberFormatException {
        negativeInfuencesAverage = 0.0;
        for (String x : negativeInfuences) {
            negativeInfuencesAverage += Double.parseDouble(x);
        }

        positiveInfuencesAverage = 0.0;
        for (String x : positiveInfuences) {
            positiveInfuencesAverage += Double.parseDouble(x);
        }
    }
}
