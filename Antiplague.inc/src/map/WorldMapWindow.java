package map;

import game.Status;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public
    class WorldMapWindow
    extends JFrame {

    WorldMapPanel worldMapPanel;
    WorldStatistics worldStatistics;

    @Override
    public void dispose() {
        super.dispose();
        worldStatistics.stopThread();
    }

    public WorldMapWindow(){
        worldMapPanel = new WorldMapPanel(this);
        int w = worldMapPanel.icMap.getIconWidth();
        int h = worldMapPanel.icMap.getIconHeight();

        worldStatistics = new WorldStatistics();

        JTabbedPane tp = new JTabbedPane();
        tp.add("Mapa", worldMapPanel);
        tp.add("Statystyki", worldStatistics);

        add(tp);
        setPreferredSize(new Dimension(w, h));
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
