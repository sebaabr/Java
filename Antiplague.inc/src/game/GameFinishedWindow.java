package game;


import model.ModelBuilder;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public
    class GameFinishedWindow
    extends JFrame {

    public GameFinishedWindow(String message){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(message);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField input = new JTextField();
        input.setPreferredSize(new Dimension(300,50));
        input.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton ok = new JButton("OK");
        ok.setAlignmentX(Component.CENTER_ALIGNMENT);
        ok.addActionListener((e)->
        {
            ModelBuilder.highscores.add(new Score(input.getText(), Status.level, (int)ChronoUnit.DAYS.between(LocalDate.now(), Status.date), Status.score));
            ModelBuilder.saveHighScores();
            dispose();
            new Menu();
        });

        panel.add(Box.createVerticalStrut(30));
        panel.add(title);
        panel.add(Box.createVerticalStrut(10));
        panel.add(input);
        panel.add(Box.createVerticalStrut(10));
        panel.add(ok);
        panel.add(Box.createVerticalStrut(50));

        add(panel);
        setLocationRelativeTo(null);
        setPreferredSize(new Dimension(300,200));
        pack();
        setResizable(false);
        setVisible(true);
    }

}
