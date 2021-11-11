package model;

import game.Level;
import game.Score;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public
    class ModelBuilder {

    static Map<Level, Double> poczatowyDziennyWzrostZachorowan = new HashMap<>();
    static Map<Transports, Double> transportDodatkowyProcent = new HashMap<>();
    public static List<Score> highscores = new ArrayList<>();

    public static final int IMPROVEMENT_COST_CONST = 10000; //do manipuowania kosztem improvement
    public static final int POINT_FOR_10000_RECOVERED = 1;
    public static final int POINT_FOR_SAVED_COUNTRY = 1000;
    public static final int START_MONEY = 1000;

    public static void fillDatabase(){
        // czyszczenie
        poczatowyDziennyWzrostZachorowan.clear();
        transportDodatkowyProcent.clear();
        Continent.allContinents.clear();
        Improvement.allImprovements.clear();
        loadHighScores();

        poczatowyDziennyWzrostZachorowan.put(Level.Latwy, 0.005);
        poczatowyDziennyWzrostZachorowan.put(Level.Sredni, 0.015);
        poczatowyDziennyWzrostZachorowan.put(Level.Trudny, 0.03);

        transportDodatkowyProcent.put(Transports.AUTO, 0.005);
        transportDodatkowyProcent.put(Transports.CARGO, 0.003);
        transportDodatkowyProcent.put(Transports.PLANE, 0.0073);
        transportDodatkowyProcent.put(Transports.BUS, 0.002);
        transportDodatkowyProcent.put(Transports.TRAIN, 0.003);

        //AMERYKA PŁN
        List<Country> northAmericaCountries = new ArrayList<>();
        new Continent(new ImageIcon("src/maps/am-pln.jpg"), 280,170,"North America", northAmericaCountries);

        List<CountryTransport> srodkiTransportuUSA = new ArrayList<>();
        srodkiTransportuUSA.add(new CountryTransport(Transports.BUS, 0.5));
        srodkiTransportuUSA.add(new CountryTransport(Transports.AUTO, 0.5));
        srodkiTransportuUSA.add(new CountryTransport(Transports.PLANE, 0.5));
        northAmericaCountries.add(new Country("USA", 340, 450, 328200000L, srodkiTransportuUSA));

        List<CountryTransport> srodkiTransportuCanada = new ArrayList<>();
        srodkiTransportuCanada.add(new CountryTransport(Transports.BUS, 0.1));
        srodkiTransportuCanada.add(new CountryTransport(Transports.AUTO, 0.5));
        srodkiTransportuCanada.add(new CountryTransport(Transports.PLANE, 0.5));
        srodkiTransportuCanada.add(new CountryTransport(Transports.CARGO, 0.5));
        northAmericaCountries.add(new Country("Canada", 300, 300, 37590000L, srodkiTransportuCanada));

        List<CountryTransport> srodkiTransportuMexico = new ArrayList<>();
        srodkiTransportuMexico.add(new CountryTransport(Transports.BUS, 0.5));
        srodkiTransportuMexico.add(new CountryTransport(Transports.AUTO, 0.5));
        srodkiTransportuMexico.add(new CountryTransport(Transports.PLANE, 0.5));
        northAmericaCountries.add(new Country("Mexico", 360, 650, 126200000L, srodkiTransportuMexico));



        //AMERYKA PLD
        List<Country> southAmericaCountries = new ArrayList<>();
        new Continent(new ImageIcon("src/maps/am-pld.jpg"), 450,400,"South America", southAmericaCountries);

        List<CountryTransport> srodkiTransportuBrazil = new ArrayList<>();
        srodkiTransportuBrazil.add(new CountryTransport(Transports.BUS, 0.5));
        srodkiTransportuBrazil.add(new CountryTransport(Transports.AUTO, 0.5));
        srodkiTransportuBrazil.add(new CountryTransport(Transports.PLANE, 0.5));
        southAmericaCountries.add(new Country("Brazil", 380, 240, 209500000L, srodkiTransportuBrazil));

        List<CountryTransport> srodkiTransportuVenezuela = new ArrayList<>();
        srodkiTransportuVenezuela.add(new CountryTransport(Transports.BUS, 0.5));
        srodkiTransportuVenezuela.add(new CountryTransport(Transports.AUTO, 0.5));
        srodkiTransportuVenezuela.add(new CountryTransport(Transports.PLANE, 0.5));
        southAmericaCountries.add(new Country("Venezuela", 220, 50, 28870000L, srodkiTransportuVenezuela));

        List<CountryTransport> srodkiTransportuColumbia = new ArrayList<>();
        srodkiTransportuColumbia.add(new CountryTransport(Transports.BUS, 0.5));
        srodkiTransportuColumbia.add(new CountryTransport(Transports.AUTO, 0.5));
        srodkiTransportuColumbia.add(new CountryTransport(Transports.PLANE, 0.5));
        southAmericaCountries.add(new Country("Columbia", 90, 100, 49650000L, srodkiTransportuColumbia));

        List<CountryTransport> srodkiTransportuPeru = new ArrayList<>();
        srodkiTransportuPeru.add(new CountryTransport(Transports.BUS, 0.5));
        srodkiTransportuPeru.add(new CountryTransport(Transports.AUTO, 0.5));
        srodkiTransportuPeru.add(new CountryTransport(Transports.PLANE, 0.5));
        southAmericaCountries.add(new Country("Peru", 100, 240, 31990000L, srodkiTransportuPeru));

        List<CountryTransport> srodkiTransportuArgentina = new ArrayList<>();
        srodkiTransportuArgentina.add(new CountryTransport(Transports.BUS, 0.5));
        srodkiTransportuArgentina.add(new CountryTransport(Transports.AUTO, 0.5));
        srodkiTransportuArgentina.add(new CountryTransport(Transports.PLANE, 0.5));
        southAmericaCountries.add(new Country("Argentina", 250, 430, 44490000L, srodkiTransportuArgentina));

        List<CountryTransport> srodkiTransportuChile = new ArrayList<>();
        srodkiTransportuChile.add(new CountryTransport(Transports.BUS, 0.5));
        srodkiTransportuChile.add(new CountryTransport(Transports.AUTO, 0.5));
        srodkiTransportuChile.add(new CountryTransport(Transports.PLANE, 0.5));
        southAmericaCountries.add(new Country("Chile", 140, 500, 18730000L, srodkiTransportuChile));



        //EUROPA
        List<Country> europeCountries = new ArrayList<>();
        new Continent(new ImageIcon("src/maps/europa.jpg"), 765,160,"Europe", europeCountries);

        List<CountryTransport> srodkiTransportuUK = new ArrayList<>();
        srodkiTransportuUK.add(new CountryTransport(Transports.BUS, 0.5));
        srodkiTransportuUK.add(new CountryTransport(Transports.AUTO, 0.5));
        srodkiTransportuUK.add(new CountryTransport(Transports.PLANE, 0.5));
        europeCountries.add(new Country("UK", 120, 380, 66650000L, srodkiTransportuUK));

        List<CountryTransport> srodkiTransportuFrance = new ArrayList<>();
        srodkiTransportuFrance.add(new CountryTransport(Transports.BUS, 0.5));
        srodkiTransportuFrance.add(new CountryTransport(Transports.AUTO, 0.5));
        srodkiTransportuFrance.add(new CountryTransport(Transports.PLANE, 0.5));
        europeCountries.add(new Country("France", 150, 520, 66990000L, srodkiTransportuFrance));

        List<CountryTransport> srodkiTransportuSpain = new ArrayList<>();
        srodkiTransportuSpain.add(new CountryTransport(Transports.BUS, 0.5));
        srodkiTransportuSpain.add(new CountryTransport(Transports.AUTO, 0.5));
        srodkiTransportuSpain.add(new CountryTransport(Transports.PLANE, 0.5));
        europeCountries.add(new Country("Spain", 30, 630, 46940000L, srodkiTransportuSpain));

        List<CountryTransport> srodkiTransportuGermany = new ArrayList<>();
        srodkiTransportuGermany.add(new CountryTransport(Transports.BUS, 0.5));
        srodkiTransportuGermany.add(new CountryTransport(Transports.AUTO, 0.5));
        srodkiTransportuGermany.add(new CountryTransport(Transports.PLANE, 0.5));
        europeCountries.add(new Country("Germany", 250, 450, 83020000L, srodkiTransportuGermany));

        List<CountryTransport> srodkiTransportuPoland = new ArrayList<>();
        srodkiTransportuPoland.add(new CountryTransport(Transports.BUS, 0.5));
        srodkiTransportuPoland.add(new CountryTransport(Transports.AUTO, 0.5));
        srodkiTransportuPoland.add(new CountryTransport(Transports.PLANE, 0.5));
        europeCountries.add(new Country("Poland", 370, 400, 37970000L, srodkiTransportuPoland));

        List<CountryTransport> srodkiTransportuItaly = new ArrayList<>();
        srodkiTransportuItaly.add(new CountryTransport(Transports.BUS, 0.5));
        srodkiTransportuItaly.add(new CountryTransport(Transports.AUTO, 0.5));
        srodkiTransportuItaly.add(new CountryTransport(Transports.PLANE, 0.5));
        europeCountries.add(new Country("Italy", 290, 620, 60360000L, srodkiTransportuItaly));

        List<CountryTransport> srodkiTransportuNorway = new ArrayList<>();
        srodkiTransportuNorway.add(new CountryTransport(Transports.BUS, 0.5));
        srodkiTransportuNorway.add(new CountryTransport(Transports.AUTO, 0.5));
        srodkiTransportuNorway.add(new CountryTransport(Transports.PLANE, 0.5));
        europeCountries.add(new Country("Norway", 250, 180, 5368000L, srodkiTransportuNorway));

        List<CountryTransport> srodkiTransportuSweden = new ArrayList<>();
        srodkiTransportuSweden.add(new CountryTransport(Transports.BUS, 0.5));
        srodkiTransportuSweden.add(new CountryTransport(Transports.AUTO, 0.5));
        srodkiTransportuSweden.add(new CountryTransport(Transports.PLANE, 0.5));
        europeCountries.add(new Country("Sweden", 300, 250, 10230000L, srodkiTransportuSweden));

        List<CountryTransport> srodkiTransportuCenterCountrys = new ArrayList<>();
        srodkiTransportuCenterCountrys.add(new CountryTransport(Transports.BUS, 0.5));
        srodkiTransportuCenterCountrys.add(new CountryTransport(Transports.AUTO, 0.5));
        srodkiTransportuCenterCountrys.add(new CountryTransport(Transports.PLANE, 0.5));
        europeCountries.add(new Country("Greece", 450, 650, 10720000, srodkiTransportuCenterCountrys));

        //AFRYKA

        List<Country> africaCountries = new ArrayList<>();
        new Continent(new ImageIcon("src/maps/afryka.jpg"), 740,280,"Africa", africaCountries);

        List<CountryTransport> srodkiTransportuAlgeria = new ArrayList<>();
        srodkiTransportuAlgeria.add(new CountryTransport(Transports.BUS, 0.5));
        srodkiTransportuAlgeria.add(new CountryTransport(Transports.AUTO, 0.5));
        srodkiTransportuAlgeria.add(new CountryTransport(Transports.PLANE, 0.5));
        africaCountries.add(new Country("Algeria", 120, 100, 42230000L, srodkiTransportuAlgeria));

        List<CountryTransport> srodkiTransportuLibya = new ArrayList<>();
        srodkiTransportuLibya.add(new CountryTransport(Transports.BUS, 0.5));
        srodkiTransportuLibya.add(new CountryTransport(Transports.AUTO, 0.5));
        srodkiTransportuLibya.add(new CountryTransport(Transports.PLANE, 0.5));
        africaCountries.add(new Country("Libya", 270, 120, 6679000L, srodkiTransportuLibya));

        List<CountryTransport> srodkiTransportuEgypt = new ArrayList<>();
        srodkiTransportuEgypt.add(new CountryTransport(Transports.BUS, 0.5));
        srodkiTransportuEgypt.add(new CountryTransport(Transports.AUTO, 0.5));
        srodkiTransportuEgypt.add(new CountryTransport(Transports.PLANE, 0.5));
        africaCountries.add(new Country("Egypy", 390, 120, 98420000L, srodkiTransportuEgypt));

        List<CountryTransport> srodkiTransportuNigeria = new ArrayList<>();
        srodkiTransportuNigeria.add(new CountryTransport(Transports.BUS, 0.5));
        srodkiTransportuNigeria.add(new CountryTransport(Transports.AUTO, 0.5));
        srodkiTransportuNigeria.add(new CountryTransport(Transports.PLANE, 0.5));
        africaCountries.add(new Country("Nigeria", 120, 200, 195900000L, srodkiTransportuNigeria));

        List<CountryTransport> srodkiTransportuCenterCountysAfrica = new ArrayList<>();
        srodkiTransportuCenterCountysAfrica.add(new CountryTransport(Transports.BUS, 0.5));
        srodkiTransportuCenterCountysAfrica.add(new CountryTransport(Transports.AUTO, 0.5));
        srodkiTransportuCenterCountysAfrica.add(new CountryTransport(Transports.PLANE, 0.5));
        africaCountries.add(new Country("Congo", 350, 420, 5244000L, srodkiTransportuCenterCountysAfrica));

        List<CountryTransport> srodkiTransportuRPA = new ArrayList<>();
        srodkiTransportuRPA.add(new CountryTransport(Transports.BUS, 0.5));
        srodkiTransportuRPA.add(new CountryTransport(Transports.AUTO, 0.5));
        srodkiTransportuRPA.add(new CountryTransport(Transports.PLANE, 0.5));
        africaCountries.add(new Country("RPA", 350, 650, 57780000L, srodkiTransportuRPA));


        List<Country> asiaCountries = new ArrayList<>();
        new Continent(new ImageIcon("src/maps/azja.jpg"), 1050,130,"Asia", asiaCountries);

        List<CountryTransport> srodkiTransportuRusia = new ArrayList<>();
        srodkiTransportuRusia.add(new CountryTransport(Transports.BUS, 0.5));
        srodkiTransportuRusia.add(new CountryTransport(Transports.AUTO, 0.5));
        srodkiTransportuRusia.add(new CountryTransport(Transports.PLANE, 0.5));
        asiaCountries.add(new Country("Rusia", 300, 250, 144500000L, srodkiTransportuRusia));

        List<CountryTransport> srodkiTransportuIndia = new ArrayList<>();
        srodkiTransportuIndia.add(new CountryTransport(Transports.BUS, 0.5));
        srodkiTransportuIndia.add(new CountryTransport(Transports.AUTO, 0.5));
        srodkiTransportuIndia.add(new CountryTransport(Transports.PLANE, 0.5));
        asiaCountries.add(new Country("India", 240, 540, 1353000000L, srodkiTransportuIndia));

        List<CountryTransport> srodkiTransportuChina = new ArrayList<>();
        srodkiTransportuChina.add(new CountryTransport(Transports.BUS, 0.5));
        srodkiTransportuChina.add(new CountryTransport(Transports.AUTO, 0.5));
        srodkiTransportuChina.add(new CountryTransport(Transports.PLANE, 0.5));
        asiaCountries.add(new Country("China", 400, 450, 1393000000L, srodkiTransportuChina));

        List<CountryTransport> srodkiTransportuJapan = new ArrayList<>();
        srodkiTransportuJapan.add(new CountryTransport(Transports.BUS, 0.5));
        srodkiTransportuJapan.add(new CountryTransport(Transports.AUTO, 0.5));
        srodkiTransportuJapan.add(new CountryTransport(Transports.PLANE, 0.5));
        asiaCountries.add(new Country("Japan", 600, 450, 126500000L, srodkiTransportuJapan));

        List<CountryTransport> srodkiTransportuMongolia = new ArrayList<>();
        srodkiTransportuMongolia.add(new CountryTransport(Transports.BUS, 0.5));
        srodkiTransportuMongolia.add(new CountryTransport(Transports.AUTO, 0.5));
        srodkiTransportuMongolia.add(new CountryTransport(Transports.PLANE, 0.5));
        asiaCountries.add(new Country("Mongolia", 430, 380, 3170000L, srodkiTransportuMongolia));

        List<CountryTransport> srodkiTransportuKazakhstan = new ArrayList<>();
        srodkiTransportuKazakhstan.add(new CountryTransport(Transports.BUS, 0.5));
        srodkiTransportuKazakhstan.add(new CountryTransport(Transports.AUTO, 0.5));
        srodkiTransportuKazakhstan.add(new CountryTransport(Transports.PLANE, 0.5));
        asiaCountries.add(new Country("Kazakhstan", 140, 380, 12280000L, srodkiTransportuKazakhstan));

        List<CountryTransport> srodkiTransportuIran = new ArrayList<>();
        srodkiTransportuIran.add(new CountryTransport(Transports.BUS, 0.5));
        srodkiTransportuIran.add(new CountryTransport(Transports.AUTO, 0.5));
        srodkiTransportuIran.add(new CountryTransport(Transports.PLANE, 0.5));
        asiaCountries.add(new Country("Iran", 120, 490, 81800000L, srodkiTransportuIran));

        List<CountryTransport> srodkiTransportuSaudiArabia = new ArrayList<>();
        srodkiTransportuSaudiArabia.add(new CountryTransport(Transports.BUS, 0.5));
        srodkiTransportuSaudiArabia.add(new CountryTransport(Transports.AUTO, 0.5));
        srodkiTransportuSaudiArabia.add(new CountryTransport(Transports.PLANE, 0.5));
        asiaCountries.add(new Country("Saudi Arabia", 50, 530, 33700000L, srodkiTransportuSaudiArabia));


        //new Continent(new ImageIcon("src/north-america.jpg"), 100,100,"Arctic", northAmericaCountries);
        List<Country> australiaCountries = new ArrayList<>();
        new Continent(new ImageIcon("src/maps/australia.jpg"), 1270,480,"Australia", australiaCountries);

        List<CountryTransport> srodkiTransportuAustralia = new ArrayList<>();
        srodkiTransportuAustralia.add(new CountryTransport(Transports.BUS, 0.5));
        srodkiTransportuAustralia.add(new CountryTransport(Transports.AUTO, 0.5));
        srodkiTransportuAustralia.add(new CountryTransport(Transports.PLANE, 0.5));
        australiaCountries.add(new Country("Australia", 200, 450, 24990000L, srodkiTransportuAustralia));

        List<CountryTransport> srodkiTransportuNewZealand = new ArrayList<>();
        srodkiTransportuNewZealand.add(new CountryTransport(Transports.BUS, 0.5));
        srodkiTransportuNewZealand.add(new CountryTransport(Transports.AUTO, 0.5));
        srodkiTransportuNewZealand.add(new CountryTransport(Transports.PLANE, 0.5));
        australiaCountries.add(new Country("New Zealand", 640, 640, 4886000L, srodkiTransportuNewZealand));

        List<CountryTransport> srodkiTransportuPapuaNewGuinea = new ArrayList<>();
        srodkiTransportuPapuaNewGuinea.add(new CountryTransport(Transports.BUS, 0.5));
        srodkiTransportuPapuaNewGuinea.add(new CountryTransport(Transports.AUTO, 0.5));
        srodkiTransportuPapuaNewGuinea.add(new CountryTransport(Transports.PLANE, 0.5));
        australiaCountries.add(new Country("Papua New Guinea", 350, 250, 8606000L, srodkiTransportuPapuaNewGuinea));


        // --- Ulepszenia ---
        new Improvement("Nakaz maseczek", "Nakaz noszenia maseczek w miescach publicznych", 0.004, 0.006);
        new Improvement("Zamknięcie obiektów kultury", "Zamknięcie wyszystkich obiektów kultury, takich jak: muzea, kina, wystawy itp.", 0.004, 0.004);
        new Improvement("Zakaz masowych imprez", "Odwołanie wszystkich imprez amsowych oraz uroczystości powyżej 20 osób", 0.005, 0.007);
        new Improvement("Zakaz wychodzenia z domu", "Zakaz opuszczania miejsca zamieszkania bez ważnego powodu", 0.008, 0.02);
        new Improvement("Dofinansowanie służby zdrowia", "Dodatkowy fundusz na służbę zdrowia", 0.02, 0.06);
        new Improvement("Zakaz przemieszczania sie", "Zakaz podróżowania między miastami", 0.005, 0.02);
        new Improvement("Dezynfekcji miast", "Zostają powołane specjalne służby do dezynfekcji miejsc publicznych", 0.015, 0.06);
        new Improvement("Zamknięcie szkół", "Zostaną zamkniete wszystkie szkoły", 0.05, 0.09);
        new Improvement("Wprowadzenie kar", "Zostają wprowadzone kary za nieprzestrzeganie przepisów sanitarnych", 0.05, 0.09);

    }

    public static void loadHighScores() {

        File file = new File("zapisgry.plague");
        if(!file.exists())
            saveHighScores();

        try {
            ObjectInputStream streamIn = new ObjectInputStream(new FileInputStream("zapisgry.plague"));
            highscores = (List<Score>) streamIn.readObject();
            streamIn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Wystapil blad podczas odczytu pliku ze stanem gry.\n"+e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    public static void saveHighScores()
    {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("zapisgry.plague"));
            out.writeObject(highscores);
            out.close();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Wystapil blad podczas zapisu pliku ze stanem gry.\n"+e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
