package game;

import model.Continent;
import model.Country;
import model.ModelBuilder;

public
    class Simulator
    extends Thread {

    int DAY_LENGTH = 3; //in seconds
    int countries = 0;

    public Simulator() {
        for(Continent continent : Continent.allContinents){
            countries += continent.getCountriesList().size();
        }
    }

    @Override
    public void run(){
        while (true) {
            int liczbaStraconych = 0;
            int liczbaUratowanych = 0;

            for (Continent continent : Continent.allContinents) {
                for (Country country : continent.getCountriesList()) {
                    if(country.isStracony()) {
                        liczbaStraconych++;
                        continue;
                    }
                    if (country.isUratowany()) {
                        liczbaUratowanych++;
                        continue;
                    }
                    long zarazonychWczoraj = country.getLiczbaZakazonych();
                    country.setLiczbaZakazonych((long) (country.getPopulacja() * country.getDziennyWzrostZachorowan() + country.getLiczbaZakazonych()));

                    if(country.getLiczbaZakazonych() >= country.getPopulacja()) {
                        country.setStracony(true);
                        country.setLiczbaZakazonych(country.getPopulacja());
                        System.out.println("Kraj stracony: " + country.getName());
                        continue;
                    }
                    if(country.getLiczbaZakazonych() <= 0) {
                        country.setUratowany(true);
                        Status.money += ModelBuilder.POINT_FOR_SAVED_COUNTRY;
                        country.setLiczbaZakazonych(0);
                        System.out.println("Kraj uratowany: " + country.getName());
                        continue;
                    }

                    //ludzie zdrowieja
                    long wyzdrowialychDzisiaj = 0;
                    if(country.getDziennyWzrostZachorowan() < 0) {
                        wyzdrowialychDzisiaj = zarazonychWczoraj -  country.getLiczbaZakazonych();
                        Status.money += (wyzdrowialychDzisiaj) / 10000 * ModelBuilder.POINT_FOR_10000_RECOVERED;
                        Status.score += (wyzdrowialychDzisiaj) / 10000 * ModelBuilder.POINT_FOR_10000_RECOVERED;
                        country.setLiczbaWyzdrowialych(country.getLiczbaWyzdrowialych()+wyzdrowialychDzisiaj);
                    }
                }
            }

            // przegrana gdy 25 lub wiecej zarazonych
            // wygrana gdy 24 max zarazone i 11 wyzdrowiale

            int krajeNiewiadome = countries - liczbaStraconych - liczbaUratowanych;

            if(liczbaUratowanych >= 11 && krajeNiewiadome == 0){
                Status.gameOver = true;
                new GameFinishedWindow("Wygrales! Podaj swoj nick:");
                return;
            }

            if(liczbaStraconych >= 25){
                Status.gameOver = true;
                new GameFinishedWindow("Przegrales! Podaj swoj nick:");
                return;
            }

            if(Status.gameInterrupted)
            {
                return;
            }

            try {
                Status.date = Status.date.plusDays(1);
                Thread.sleep(1000*DAY_LENGTH);
                //Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
