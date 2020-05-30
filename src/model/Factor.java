package model;

public class Factor {
    public String name;
    public FactorPart growth;
    public FactorPart stabilization;
    public FactorPart recourse;

    public Factor(String name) {
        this.name = name;
        this.growth = new FactorPart("Wzrost", 0, "0");
        this.stabilization = new FactorPart("Stabilizacja", 0, "0");
        this.recourse = new FactorPart("Regres", 0, "0");
    }
}
