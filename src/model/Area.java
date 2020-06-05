package model;

import app.Config;

import java.util.ArrayList;

public class Area {
    public String name;
    public ArrayList<Factor> factors;

    public Area(String name) {
        this.name = name;
        this.factors = new ArrayList<>();

        for (int i = 0; i < Config.defaultFactorsCount; i++) {
            this.factors.add(new Factor("Nowy czynnik"));
        }
    }
}
