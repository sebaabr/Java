package map;

import model.Continent;
import model.Country;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class WorldStatistics extends JPanel {
    JProgressBar swiatProgressbar;
    boolean refresh = true;
    Map<Country, JProgressBar> countryProgress = new HashMap<>();

    public WorldStatistics(){
        JPanel panel = new JPanel();
       // panel.setBounds(10, 10, 200, 200);
        panel.setLayout(new GridLayout(0,7, 15, 15));

        JPanel swiatPanel = new JPanel();
        swiatPanel.setLayout(new BoxLayout(swiatPanel, BoxLayout.Y_AXIS));

        JLabel swiatLabel = new JLabel("Swiat");
        swiatLabel.setFont(new Font("Arial", Font.BOLD, 24));
        swiatProgressbar = new JProgressBar(0, 100);

        swiatPanel.add(swiatLabel);
        swiatPanel.add(swiatProgressbar);
        panel.add(swiatPanel);


        JLabel kontynentyLabel = new JLabel("Kontynenty");
        kontynentyLabel.setFont(new Font("Arial", Font.BOLD, 22));

        for(Continent continent : Continent.allContinents)
        {
            JPanel kontynentPanel = new JPanel();
            kontynentPanel.setLayout(new BoxLayout(kontynentPanel, BoxLayout.Y_AXIS));

            JLabel kontynentLabel = new JLabel(continent.getName());
            kontynentLabel.setFont(new Font("Arial", Font.BOLD, 18));
            kontynentPanel.add(kontynentLabel);
            for(Country country : continent.getCountriesList())
            {
                JLabel countryLabel = new JLabel(country.getName());
                JProgressBar progressBar = new JProgressBar(0, 100);
                kontynentPanel.add(progressBar);
                kontynentPanel.add(countryLabel);
                kontynentPanel.add(Box.createVerticalStrut(15));
                countryProgress.put(country, progressBar);
            }
            panel.add(kontynentPanel);
        }
        add(panel);

        new Thread(()->{
            while(refresh) {
                updateStatistics();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    void stopThread(){
        refresh = false;
    }

    void updateStatistics(){
        double sumPercentage = 0;
        int countries = 0;
        for(Continent continent : Continent.allContinents) {
            for (Country country : continent.getCountriesList()) {
                JProgressBar progressBar = countryProgress.get(country);
                progressBar.setValue((int)(country.getProcentZarazonych()*100));
                sumPercentage += country.getProcentZarazonych();
                countries++;

                if(country.getProcentZarazonych() >= 0.8)
                    progressBar.setForeground(Color.red);
            }
        }
        swiatProgressbar.setValue((int)(sumPercentage/countries*100d));
    }

}
