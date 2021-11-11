package game;

import map.WorldMapWindow;
import model.ModelBuilder;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public
    class SelectLevelWindow
    extends JFrame {

    public SelectLevelWindow(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Wybierz poziom");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JComboBox<Level> comboBox = new JComboBox<>(Level.values());
        comboBox.setSelectedItem(0);

        JButton ok = new JButton("GRAJ");
        ok.setAlignmentX(Component.CENTER_ALIGNMENT);
        ok.addActionListener((e)-> {
            Status.money = ModelBuilder.START_MONEY;
            Status.score = 0;
            Status.date = LocalDate.now();
            Status.gameOver = false;
            Status.level = (Level)comboBox.getSelectedItem();

            ModelBuilder.fillDatabase();
            Simulator simulator = new Simulator();
            simulator.start();

            WorldMapWindow w = new WorldMapWindow();
            dispose();
        });

        panel.add(Box.createVerticalStrut(30));
        panel.add(title);
        panel.add(Box.createVerticalStrut(10));
        panel.add(comboBox);
        panel.add(Box.createVerticalStrut(10));
        panel.add(ok);
        panel.add(Box.createVerticalStrut(50));

        add(panel);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setPreferredSize(new Dimension(300,200));
        pack();
        setResizable(false);
        setVisible(true);
    }
}
