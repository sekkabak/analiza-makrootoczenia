package model;

import app.Config;

import java.util.Arrays;

public class FactorPart {
    public String name;
    private float probability;
    private String influence;

    public FactorPart(String name, float probability, String influence) {
        this.name = name;
        setProbability(probability);
        setInfluence(influence);
    }

    public float getProbability() {
        return probability;
    }

    public void setProbability(float probability) {
        // TODO throw error
        if(probability < 0 || probability > 1) {
            this.probability = 0;
        }

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
