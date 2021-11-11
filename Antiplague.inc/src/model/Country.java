package model;

//import com.sun.org.apache.xpath.internal.operations.Bool;

import game.Status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Country {
    private String name;
    int x, y;
    long liczbaZakazonych = 1;
    long liczbaWyzdrowialych = 0;
    long populacja;
    boolean uratowany = false;
    boolean stracony = false;

    double dodatkowyProcentRandom = 0d;

    List<CountryTransport> transports;
    ///Map<Improvement, Boolean>
    Set<Improvement> usedImprovement = new HashSet<>();

    public Country(String name, int x, int y, long populacja, List<CountryTransport> transports) {
        this.transports = transports;
        this.name = name;
        this.y = y;
        this.x = x;
        this.populacja = populacja;
        this.dodatkowyProcentRandom = Math.random() * 0.015;
    }

    public void setUratowany(boolean uratowany) {
        this.uratowany = uratowany;
    }

    public boolean isUratowany() {
        return uratowany;
    }

    public boolean isStracony() {
        return stracony;
    }

    public void setStracony(boolean stracony) {
        this.stracony = stracony;
    }

    public void setLiczbaWyzdrowialych(long liczbaWyzdrowialych) {
        this.liczbaWyzdrowialych = liczbaWyzdrowialych;
    }

    public Set<Improvement> getUsedImprovement() {
        return usedImprovement;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public long getLiczbaZakazonych() {
        return liczbaZakazonych;
    }

    public long getPopulacja() {
        return populacja;
    }

    public double getDziennyWzrostZachorowan() {

        double procent = ModelBuilder.poczatowyDziennyWzrostZachorowan.get(Status.level) - dodatkowyProcentRandom;

        for (CountryTransport transport : getTransports())
        {
            if(!transport.czyZamkniete())
            {
                procent += ModelBuilder.transportDodatkowyProcent.get(transport.getTransport());
            }
        }

        for(Improvement improvement : getUsedImprovement())
        {
            procent -= improvement.getDecreaseInfectedPercent();
        }

        return procent;
    }

    public double getProcentZarazonych() {
        return (double)liczbaZakazonych / (double)populacja;
    }

    public long getLiczbaWyzdrowialych() {
        return liczbaWyzdrowialych;
    }

    public List<CountryTransport> getTransports() {
        return transports;
    }

    public void setLiczbaZakazonych(long liczbaZakazonych) {
        this.liczbaZakazonych = liczbaZakazonych;
    }
}
