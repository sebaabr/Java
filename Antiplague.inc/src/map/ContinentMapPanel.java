package map;

import game.CountryControlWindow;
import model.Continent;
import model.Country;

import javax.swing.*;
import java.awt.*;

public
    class ContinentMapPanel
    extends JPanel {

    Continent continent;
    JFrame window;

    public ContinentMapPanel(JFrame window, Continent continent){
        setLayout(null);
        this.continent = continent;
        this.window = window;

        for(Country country : continent.getCountriesList())
        {
            JButton button = new JButton(country.getName());
            button.setLocation(country.getX(), country.getY());
            button.setSize(150, 30);
            button.addActionListener(e-> {
                window.dispose();
                new CountryControlWindow(country);
            });
            add(button);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(continent.getContinentIcon().getImage(), 0, 0, null);
    }
}
