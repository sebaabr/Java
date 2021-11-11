package model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Continent {

    public static List<Continent> allContinents = new ArrayList<>();

    private ImageIcon continentIcon;
    private String name;
    private List<Country> countriesList = new ArrayList<>();
    private int x, y;

    public Continent(ImageIcon continentIcon, int x, int y, String name, List<Country> countriesList) {
        this.continentIcon = continentIcon;
        this.name = name;
        this.countriesList = countriesList;
        this.x = x;
        this.y = y;
        allContinents.add(this);
    }

    public ImageIcon getContinentIcon() {
        return continentIcon;
    }

    public String getName() {
        return name;
    }

    public List<Country> getCountriesList() {
        return countriesList;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
