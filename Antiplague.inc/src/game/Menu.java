package game;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public
    class Menu
    extends JFrame {

    JPanel panel = new JPanel();


    public Menu(){
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.DARK_GRAY);
        panel.setBorder(new EmptyBorder(175,50,50,50));

        JPanel buttonPanel = new JPanel();
        //buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
        JButton newGameButton = new JButton("Nowa gra");
        JButton highScoresButton = new JButton("High scores");
        JButton exitGameButton = new JButton("Wyjdź z gry");

        JPanel logoPanel = new JPanel();
        logoPanel.setLayout(new BoxLayout(logoPanel, BoxLayout.X_AXIS));
        JLabel logo = new JLabel(new ImageIcon("src/logo.png"));
        logo.setSize(new Dimension(50,50));
        logoPanel.setBackground(Color.DARK_GRAY);

        JLabel name = new JLabel("ANTIPLAGUE.INC");
        name.setForeground(Color.lightGray);
        name.setFont(new Font("ZapfDingbats", Font.ITALIC , 80));
        logoPanel.add(logo);
        logoPanel.add(name);

        newGameButton.setPreferredSize(new Dimension(100, 50));
        highScoresButton.setPreferredSize(new Dimension(150, 50));
        exitGameButton.setPreferredSize(new Dimension(100, 50));

        buttonPanel.add(newGameButton);
        buttonPanel.add(highScoresButton);
        buttonPanel.add(exitGameButton);
        buttonPanel.setBackground(Color.DARK_GRAY);

        newGameButton.addActionListener((a)-> {
            new SelectLevelWindow();
            this.dispose();
        });

        highScoresButton.addActionListener((a)->{
            new HighScoresWindow();
            this.dispose();
        });

        exitGameButton.addActionListener((e)->{
            System.exit(0);
        });

        panel.add(logoPanel);
        panel.add(buttonPanel);
        add(panel);
        setPreferredSize(new Dimension(1024,768));
        pack();
        setVisible(true);
    }

}
