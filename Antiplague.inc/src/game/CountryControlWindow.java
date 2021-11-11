package game;

import map.WorldMapWindow;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public
    class CountryControlWindow
    extends JFrame
    implements Runnable {

    // statystyki
    private final JLabel dzisiejszaData;
    private final JLabel dniOdStartu;
    private final JLabel liczbaZakazonych;
    private final JLabel procentZakazonych;
    private final JLabel pieniadze;
    private final JLabel liczbaWyzdrowialych;
    private final JLabel dziennyWzrost;
    private final JProgressBar procentZakazonychBar;

    private Map<CountryTransport, JLabel> transportsLabels = new HashMap<>();
    private Map<CountryTransport, JButton> transportsButtons = new HashMap<>();
    private Map<Improvement, JButton> improvementButton = new HashMap<>();

    private JPanel panel, rightTop, leftTop, bottom;
    private Country country;
    private boolean refresh = true;

    public CountryControlWindow(Country country) {
        this.panel = new JPanel();
        this.country = country;
        setTitle("Zarzadzanie " + country.getName());
        panel.setLayout(new GridBagLayout());

        leftTop = new JPanel();
        rightTop = new JPanel();
        bottom = new JPanel();

        // --- LEWA GORA----
        leftTop.setLayout(new BoxLayout(leftTop, BoxLayout.Y_AXIS));
        leftTop.setBorder(BorderFactory.createTitledBorder("Zarzadzanie"));

        JPanel zarzanieTransportem = new JPanel();
        zarzanieTransportem.setLayout(new GridLayout(0, 3));

        for (CountryTransport countryTransport : country.getTransports()) {
            zarzanieTransportem.add(new JLabel(countryTransport.getTransport().toString()));

            JLabel status = new JLabel();
            JButton zakmnij = new JButton("zamknij");

            transportsLabels.put(countryTransport, status);
            transportsButtons.put(countryTransport, zakmnij);

            if(countryTransport.czyZamkniete()) {
                status.setText("zamkniete");
                status.setForeground(Color.GREEN);
                zakmnij.setEnabled(false);
            }
            else {
                status.setText("otwarte");
                status.setForeground(Color.RED);
            }
            zarzanieTransportem.add(status);
            zarzanieTransportem.add(zakmnij);
            zakmnij.addActionListener(e -> {
                if(country.getProcentZarazonych() >= countryTransport.getIleProcentZarazonychDoUzycia()) {
                    countryTransport.setCzyZamkniete(true);
                    zakmnij.setEnabled(false);
                    status.setForeground(Color.GREEN);
                    zakmnij.setEnabled(false);
                }
            });
            zakmnij.setEnabled(false);
        }

        JLabel countryName = new JLabel("Kraj: "+country.getName());
        countryName.setAlignmentX(Component.CENTER_ALIGNMENT);
        countryName.setFont(new Font("Verdana", Font.BOLD, 23));

        JButton backToMap = new JButton("Wróć do mapy");
        backToMap.setAlignmentX(Component.CENTER_ALIGNMENT);
        backToMap.addActionListener((e) ->{
            refresh = false;
            this.dispose();
            new WorldMapWindow();
        });

        leftTop.add(countryName);
        leftTop.add(Box.createVerticalStrut(10));
        leftTop.add(zarzanieTransportem);
        leftTop.add(Box.createVerticalStrut(30));
        leftTop.add(backToMap);

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 30;
        panel.add(leftTop, c);

        // --- PRAWA GORA ---
        rightTop.setBorder(BorderFactory.createTitledBorder("Statystyki"));
        GridBagConstraints c2 = new GridBagConstraints();
        c2.gridx = 1;
        c2.gridy = 0;
        c2.ipadx = 20;
        c2.ipady = 20;
        //rightTop.setPreferredSize(new Dimension(getWidth() / 2, 200));

        dzisiejszaData = new JLabel();
        dniOdStartu = new JLabel();
        pieniadze = new JLabel();
        liczbaZakazonych = new JLabel();
        procentZakazonych = new JLabel();
        liczbaWyzdrowialych = new JLabel();
        dziennyWzrost = new JLabel();
        procentZakazonychBar = new JProgressBar(0, 100);


        rightTop.add(dzisiejszaData);
        rightTop.add(Box.createVerticalStrut(5));
        rightTop.add(dniOdStartu);
        rightTop.add(Box.createVerticalStrut(5));
        rightTop.add(pieniadze);
        rightTop.add(Box.createVerticalStrut(5));
        rightTop.add(liczbaZakazonych);
        rightTop.add(Box.createVerticalStrut(5));
        rightTop.add(procentZakazonych);
        rightTop.add(Box.createVerticalStrut(5));
        rightTop.add(liczbaWyzdrowialych);
        rightTop.add(Box.createVerticalStrut(5));
        rightTop.add(procentZakazonych);
        rightTop.add(Box.createVerticalStrut(5));
        rightTop.add(procentZakazonychBar);
        rightTop.add(Box.createVerticalStrut(5));
        rightTop.add(dziennyWzrost);

        rightTop.setLayout(new BoxLayout(rightTop, BoxLayout.Y_AXIS));

        panel.add(rightTop, c2);

        //--- DOL ---

        bottom.setBorder(BorderFactory.createTitledBorder("Ulepszenia"));
        bottom.setLayout(new GridLayout(0, 3, 10, 10));
        GridBagConstraints c3 = new GridBagConstraints();
        c3.gridx = 0;
        c3.gridy = 1;
        c3.gridwidth = 2;

        for (Improvement improvement : Improvement.allImprovements) {
            JButton button = new JButton(improvement.getName());
            improvementButton.put(improvement, button);

            if(country.getUsedImprovement().contains(improvement))
                button.setEnabled(false);

            button.addActionListener(e -> new ImprovementDetailsWindow(improvement, country));
            bottom.add(button);
        }

        panel.add(bottom, c3);

        add(panel);
        setPreferredSize(new Dimension(650,500));
        pack();
        setVisible(true);
        Thread refreshThread = new Thread(this);
        refreshThread.start();

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

    @Override
    public void run(){
        while(refresh) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(Status.gameOver){
                dispose();
                refresh = false;
            }

            dzisiejszaData.setText("Dzień: "+Status.date);
            dniOdStartu.setText("Uplynelo dni: "+ ChronoUnit.DAYS.between(LocalDate.now(), Status.date));
            pieniadze.setText("Pieniadze: "+ Status.money);
            liczbaZakazonych.setText("Liczba zakazonych: " + country.getLiczbaZakazonych());
            procentZakazonych.setText(String.format("Zakazonych: %.2f", country.getProcentZarazonych()*100)+"%");
            liczbaWyzdrowialych.setText("Liczba wyzdrowialych: " + country.getLiczbaWyzdrowialych());
            procentZakazonychBar.setValue((int) (country.getProcentZarazonych() * 100));
            dziennyWzrost.setText(String.format("Dzienny wzrost zakazonych: %.2f",country.getDziennyWzrostZachorowan()*100)+"%");

            if(country.isStracony() || country.isUratowany()){
                for(Map.Entry<Improvement, JButton> entry : improvementButton.entrySet()) {
                    entry.getValue().setEnabled(false);
                }

                for(Map.Entry<CountryTransport, JButton> entry : transportsButtons.entrySet()) {
                    entry.getValue().setEnabled(false);
                }

                continue;
            }

            for (CountryTransport countryTransport : country.getTransports()) {
                JLabel status = transportsLabels.get(countryTransport);
                JButton zamknij = transportsButtons.get(countryTransport);

                if(countryTransport.czyZamkniete()) {
                    status.setText("zamkniete");
                    status.setForeground(Color.GREEN);
                    zamknij.setEnabled(false);
                }
                else {
                    status.setText("otwarte");
                    status.setForeground(Color.RED);

                    if (country.getProcentZarazonych() >= countryTransport.getIleProcentZarazonychDoUzycia()) {
                        zamknij.setEnabled(true);
                    }
                }
            }

            for (Improvement improvement : country.getUsedImprovement()) {
                JButton button = improvementButton.get(improvement);
                button.setEnabled(false);
            }
        }
    }
}
