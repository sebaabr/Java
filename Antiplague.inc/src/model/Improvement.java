package model;

import java.util.ArrayList;
import java.util.List;


public class Improvement {
    public static List<Improvement> allImprovements = new ArrayList<>();

    String name;
    String description;
    int cost;
    double decreaseInfectedPercent;

    public Improvement(String name, String description, double decreaseInfectedPercentMin, double decreaseInfectedPercentMax) {
        this.name = name;
        this.description = description;
        this.decreaseInfectedPercent = (Math.random() * (decreaseInfectedPercentMax-decreaseInfectedPercentMin)) + decreaseInfectedPercentMin;
        this.cost = (int) (ModelBuilder.IMPROVEMENT_COST_CONST * this.decreaseInfectedPercent);
        allImprovements.add(this);
    }

    public static List<Improvement> getAllImprovements() {
        return allImprovements;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCost() {
        return cost;
    }

    public double getDecreaseInfectedPercent() {
        return decreaseInfectedPercent;
    }
}
