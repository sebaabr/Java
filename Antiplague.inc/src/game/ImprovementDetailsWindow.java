package game;

import model.Country;
import model.Improvement;

import javax.swing.*;
import java.awt.*;

public
    class ImprovementDetailsWindow
    extends JFrame {

    JPanel panel = new JPanel();
    Country country;

    ImprovementDetailsWindow(Improvement improvement, Country country) {
        this.country = country;
        panel.setLayout(new GridLayout(0,2,10,10));
        panel.add(new JLabel("Nazwa"));
        panel.add(new JLabel(improvement.getName()));
        panel.add(new JLabel("Koszt"));
        panel.add(new JLabel(improvement.getCost()+""));
        panel.add(new JLabel("Opis"));
        JTextArea area = new JTextArea(improvement.getDescription());
        area.setLineWrap(true);
        area.setEditable(false);
        panel.add(area);

        JButton buy = new JButton("kup");
        if(improvement.getCost() > Status.money) {
            buy.setEnabled(false);
        }

        JButton anuluj = new JButton("anuluj");
        buy.addActionListener(e -> buyImprovement(improvement));
        anuluj.addActionListener(e -> dispose());
        panel.add(buy);
        panel.add(anuluj);

        add(panel);
        setLocationRelativeTo(null);
        setPreferredSize(new Dimension(400,200));
        pack();
        setVisible(true);
    }

    void buyImprovement(Improvement improvement)
    {
        if(improvement.getCost() <= Status.money) {
            Status.money -= improvement.getCost();
            country.getUsedImprovement().add(improvement);
            dispose();
        }
    }
}
