package model;

import app.Config;

import java.util.ArrayList;

public class Factor {
    private String name;
    public ArrayList<FactorPart> rows = new ArrayList<>();

    public Factor(String name) {
        this.name = name;
        for (int i = 0; i < 3; i++) {
            rows.add(new FactorPart(Config.factorPartsName[i]));
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
