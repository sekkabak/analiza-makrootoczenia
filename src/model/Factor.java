package model;

public class Factor {
    private String name;
    public FactorPart[] rows = new FactorPart[3];

    public Factor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
