package GUI.c6.z3;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public
class MyFrame
        extends JFrame {

    LayoutManager layout;

    MyFrame(LayoutManager layout) {


        this.layout = layout;
        setLayout(layout);

        addButton();


        setBackground(Color.gray);
        setVisible(true);
        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    MyFrame() {

        this.layout = new BorderLayout();

        setLayout(this.layout);

        addAllButton();

        pack();
        setBackground(Color.gray);
        setVisible(true);
        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addButton() {
        JButton jb1 = new JButton("Przycisk 1");
        JButton jb2 = new JButton("Przycisk 2");
        JButton jb3 = new JButton("P3");
        JButton jb4 = new JButton("P 4");
        JButton jb5 = new JButton("Duży przycisk o numerze 5");
        JButton back = new JButton("Go back");

        if (this.layout instanceof BorderLayout) {

            JPanel jp = new JPanel();
            jp.setLayout(new GridLayout(0, 1));
            jp.add(jb4);
            jp.add(jb5);
            this.add(jb1, BorderLayout.NORTH);
            this.add(jb2, BorderLayout.WEST);
            this.add(jb3, BorderLayout.EAST);
//            this.add(jb4, BorderLayout.NORTH);
//            this.add(jb5, BorderLayout.CENTER);
            this.add(jp, BorderLayout.CENTER);
            this.add(back, BorderLayout.SOUTH);
        } else {
            this.add(jb1);
            this.add(jb2);
            this.add(jb3);
            this.add(jb4);
            this.add(jb5);
            this.add(back);
        }

        back.addActionListener(
                (e) -> {
                    new Main();
                    this.setVisible(false);
                    dispose();
                }
        );


    }

    private void addAllButton() {


        this.add(addPanel(new GridLayout(1, 0)), BorderLayout.NORTH);
        this.add(addPanel(new FlowLayout(FlowLayout.LEFT)), BorderLayout.EAST);
        this.add(addPanel(new FlowLayout(FlowLayout.RIGHT)), BorderLayout.WEST);
        this.add(addPanel(new GridLayout(0, 1)), BorderLayout.CENTER);

        this.add(addPanel(new FlowLayout()), BorderLayout.SOUTH);


        this.setSize(new Dimension(1000, 800));

    }

    private JPanel addPanel(LayoutManager lm) {
        JPanel jp = new JPanel();
        jp.setLayout(lm);
        jp.setPreferredSize(new Dimension(200, 200));
        for (int i = 1; i <= 10; i++) {
            jp.add(new JButton("Button " + i));
        }

        JButton back = new JButton("Back");
        jp.add(back);

        back.addActionListener(
                (e) -> {
                    new Main();
                    this.setVisible(false);
                    dispose();
                }
        );
        jp.setBackground(new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
        return jp;
    }


}
