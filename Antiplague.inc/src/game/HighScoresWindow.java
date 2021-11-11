package game;

import model.ModelBuilder;
import javax.swing.*;
import java.awt.*;

public
    class HighScoresWindow
    extends JFrame {

    public HighScoresWindow(){
        JPanel panel = new JPanel();
        ModelBuilder.loadHighScores();

        int id = 0;
        String[] dane = new String[ModelBuilder.highscores.size()];

        for (Score score : ModelBuilder.highscores) {
            dane[id++] = "Lp. "+id+" Nick: "+score.getName()+", Dni: "+score.getDays()+", Poziom: "+score.getLevel()+", Wynik: "+score.getScore();
        }
        JList<String> jList = new JList<>(dane);

        JButton wroc = new JButton("Wroc do menu");
        wroc.addActionListener((e)->{
            dispose();
            new Menu();
        });

        panel.add(new JScrollPane(jList));
        panel.add(wroc);

        add(panel);
        setLocationRelativeTo(null);
        setPreferredSize(new Dimension(400,400));
        pack();
        setVisible(true);
    }
}
