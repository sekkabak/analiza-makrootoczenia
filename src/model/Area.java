package model;

import java.util.ArrayList;

public class Area {
    public String name;
    public ArrayList<Factor> factors;

    public Area(String name) {
        this.name = name;
        this.factors = new ArrayList<>();
    }
}
