package model;

import app.Config;

import java.util.Arrays;

public class FactorPart {
    public String name;
    private String probability = "0";
    private String influence = "0";

    public FactorPart(String name) {
        this.name = name;
    }

    public String getProbability() {
        return probability;
    }

    public void setProbability(String probability) {
//        // TODO throw error
//        if(probability < 0 || probability > 1) {
//            this.probability = 0;
//        }

        this.probability = probability;
    }

    public String getInfluence() {
        return influence;
    }

    public void setInfluence(String influence) {
        // TODO throw error
        if(!Arrays.asList(Config.influenceValidList).contains(influence))
        {
            this.influence = "0";
        }

        this.influence = influence;
    }
}
