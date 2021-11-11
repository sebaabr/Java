package map;

import game.Status;
import model.Continent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import static javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW;

public
    class ContinentMapWindow
    extends JFrame {
    ContinentMapPanel panel;

    public ContinentMapWindow(Continent continent)
    {
        panel = new ContinentMapPanel(this, continent);
        int width = continent.getContinentIcon().getIconWidth();
        int height = continent.getContinentIcon().getIconHeight();
//        System.out.println("W" + width);
//        System.out.println("H" + height);
        setPreferredSize(new Dimension(width, height));
        add(panel);
        pack();
        setVisible(true);

        KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK, true);
        this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, "Q");
        this.getRootPane().getActionMap().put("Q", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                Status.gameInterrupted = true;
                Status.gameOver = true;
                dispose();
                new game.Menu();
            }
        });
    }
}
