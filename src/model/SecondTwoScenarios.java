package model;

import layout.AvgGetter;

import java.util.ArrayList;

public class SecondTwoScenarios {
    public Area area;

    public ArrayList<String> probability;
    public ArrayList<String> negativeInfuences;
    public ArrayList<String> positiveInfuences;

    public String probabilityAverage = "0.0";
    public String negativeInfuenceAverage = "0.0";
    public String positiveInfuenceAverage = "0.0";

    public AvgGetter getProbabilityAverage = this::calculateProbabilityAverage;
    public AvgGetter getNegativeInfuenceAverage = this::calculateNegativeInfuenceAverage;
    public AvgGetter getPositiveInfuenceAverage = this::calculatePositiveInfuenceAverage;

    public SecondTwoScenarios(Area area) {
        this.area = area;

        this.probability = new ArrayList<>();
        for(int i = 0; i <  this.area.factors.size(); i++) {
            this.probability.add("");
        }

        this.negativeInfuences = new ArrayList<>();
        for(int i = 0; i <  this.area.factors.size(); i++) {
            this.negativeInfuences.add("");
        }

        this.positiveInfuences = new ArrayList<>();
        for(int i = 0; i <  this.area.factors.size(); i++) {
            this.positiveInfuences.add("");
        }
    }

    public void setProbability(SIPair pair) {
        this.probability.set(pair.integer, pair.string);
    }

    public void setNegativeInfuences(SIPair pair) {
        this.negativeInfuences.set(pair.integer, pair.string);
    }

    public void setPositiveInfuences(SIPair pair) {
        this.positiveInfuences.set(pair.integer, pair.string);
    }


    public String calculateProbabilityAverage() throws NumberFormatException {
        double avg = 0.0;
        for (String x : probability) {
            try {
                avg += Double.parseDouble(x);
            } catch (Exception ignored) {
            }
        }
        avg /= probability.size();
        return probabilityAverage = String.valueOf(avg);
    }

    public String calculateNegativeInfuenceAverage() throws NumberFormatException {
        double avg = 0.0;
        for (String x : negativeInfuences) {
            try {
                avg += Double.parseDouble(x);
            } catch (Exception ignored) {
            }
        }
        avg /= negativeInfuences.size();
        return negativeInfuenceAverage = String.valueOf(avg);
    }

    public String calculatePositiveInfuenceAverage() throws NumberFormatException {
        double avg = 0.0;
        for (String x : positiveInfuences) {
            try {
                avg += Double.parseDouble(x);
            } catch (Exception ignored) {
            }
        }
        avg /= positiveInfuences.size();
        return positiveInfuenceAverage = String.valueOf(avg);
    }
}
