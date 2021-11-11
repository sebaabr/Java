package map;

import game.Status;
import model.Continent;

import javax.swing.*;
import java.awt.*;

public
    class WorldMapPanel
    extends JPanel {

    ImageIcon icMap = new ImageIcon("src/maps/swiat.jpg");
    JFrame window;
    boolean windowOpened = true;

    public WorldMapPanel(JFrame window) {
        this.window = window;
        setLayout(null);

        new Thread(()->{
            while(windowOpened)
            {
                if(Status.gameOver)
                {
                    windowOpened = false;
                    window.dispose();
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        for(Continent continent : Continent.allContinents)
        {
            JButton button = new JButton(continent.getName());
            button.setLocation(continent.getX(), continent.getY());
            button.setSize(150, 30);
            button.addActionListener(e-> {
                windowOpened = false;
                window.dispose();
                new ContinentMapWindow(continent);
            });
            add(button);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(icMap.getImage(), 0, 0, null);
    }
}
