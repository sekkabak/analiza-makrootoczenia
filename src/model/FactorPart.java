package model;

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
        this.probability = probability;
    }

    public String getInfluence() {
        return influence;
    }

    public void setInfluence(String influence) {
        this.influence = influence;
    }
}
