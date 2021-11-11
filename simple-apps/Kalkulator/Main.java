package GUI.c7.z2;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public
    class Main
    extends JFrame {

    private int system = 0;
    private final Map<JButton, String> buttons = new HashMap<>();
    private JTextField jtf;
    private JPanel jp;

    public Main() {
        setLayout(new FlowLayout());
        setTitle("Kalkulator");

        JPanel jp1 = new JPanel();
        JPanel jp2 = new JPanel();
        JPanel jp3 = new JPanel();
        jp1.setSize(new Dimension(350, 60));
        jp2.setPreferredSize(new Dimension(350, 60));
        jp3.setSize(new Dimension(350, 350));

        jp1.add(addJTF());
        jp2.add(addCheckBox());
        jp3.add(addButtons());

        add(jp1);
        add(jp2);
        add(jp3);

        pack();
        setResizable(false);
        setVisible(true);
        setSize(new Dimension(400, 450));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {


        SwingUtilities.invokeLater(
                () -> {
                    new Main();
                }
        );
    }

    private JPanel addJTF() {
        jp = new JPanel();
        jtf = new JTextField();
        jtf.setHorizontalAlignment(JTextField.RIGHT);
        jtf.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        jtf.setPreferredSize(new Dimension(350, 50));

        jp.add(jtf);
        return jp;
    }

    private JPanel addCheckBox() {
        jp = new JPanel();
        jp.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.black), "system liczbowy")
        );
        jp.setLayout(new FlowLayout());
        jp.setPreferredSize(new Dimension(350, 50));

        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton s = new JRadioButton("szesnastkowy", true);
        JRadioButton d = new JRadioButton("dziesietny");
        JRadioButton b = new JRadioButton("binarny");
        buttonGroup.add(s);
        buttonGroup.add(b);
        buttonGroup.add(d);

        jp.add(s);
        jp.add(d);
        jp.add(b);

        s.addActionListener(
                (e) -> {
                    if (s.isSelected()) {
//                       System.out.println("szesnastkowy");
                        this.system = 0;
                        jtf.setText("");
                        buttons.forEach((k, v) -> k.setEnabled(true));
                    }
                }
        );

        d.addActionListener(
                (e) -> {
                    if (d.isSelected()) {
//                       System.out.println("dzies");
                        this.system = 1;
                        jtf.setText("");
                        buttons.forEach((k, v) -> {

                            if ((v.equals("A") || v.equals("B") || v.equals("C") || v.equals("D") || v.equals("E") || v.equals("F")))
                                k.setEnabled(false);
                            else
                                k.setEnabled(true);

                        });
                    }
                }
        );

        b.addActionListener(
                (e) -> {
                    if (b.isSelected()) {
//                       System.out.println("bin");
                        this.system = 2;
                        jtf.setText("");
                        buttons.forEach((k, v) -> {
                            if (!(v.equals("0") || v.equals("1") || v.equals("CE") || v.equals("-") || v.equals("+") || v.equals("=")))
                                k.setEnabled(false);
                            else
                                k.setEnabled(true);
                        });
                    }

                }
        );

        return jp;
    }

    private JPanel addButtons() {
        jp = new JPanel();
        jp.setPreferredSize(new Dimension(350, 220));
        GridLayout gl = new GridLayout(5, 0);
        gl.setHgap(5);
        gl.setVgap(5);
        jp.setLayout(gl);

        for (int i = 0; i < 10; i++) {
            JButton jb = new JButton(i + "");
            jb.setPreferredSize(new Dimension(70, 50));
            buttons.put(jb, jb.getText());
            jb.addActionListener(
                    (e) -> jtf.setText(jtf.getText() + jb.getText())

            );
            jp.add(jb);
        }

        for (int i = 'A'; i <= 'F'; i++) {
            JButton jb = new JButton((char) i + "");
            jb.setPreferredSize(new Dimension(70, 50));

            buttons.put(jb, jb.getText());
            jb.addActionListener(
                    (e) -> jtf.setText(jtf.getText() + jb.getText())

            );
            jp.add(jb);
        }

        JButton jbce = new JButton("CE");
        JButton jbm = new JButton("-");
        JButton jbd = new JButton("+");
        JButton jbr = new JButton("=");
        jbce.setPreferredSize(new Dimension(70, 50));
        jbm.setPreferredSize(new Dimension(70, 50));
        jbd.setPreferredSize(new Dimension(70, 50));
        jbr.setPreferredSize(new Dimension(70, 50));

        jbce.addActionListener(
                (e) -> jtf.setText("")

        );
        jbm.addActionListener(
                (e) -> jtf.setText(jtf.getText() + jbm.getText())

        );
        jbd.addActionListener(
                (e) -> jtf.setText(jtf.getText() + jbd.getText())

        );
        jbr.addActionListener(
                (e) -> {
                    try {
                        jtf.setText("" + doCalc(system, jtf.getText()));
                    } catch (Exception exception) {
                        jtf.setText("Incorrect data!");

                        new Timer().schedule(
                                new TimerTask() {
                                    @Override
                                    public void run() {
                                        jtf.setText("");
                                    }
                                }, 3000
                        );

                        exception.printStackTrace();
                    }
                }
        );

        jp.add(jbce);
        jp.add(jbm);
        jp.add(jbd);
        jp.add(jbr);

        return jp;
    }

    public String doCalc(int sys, String txt) {

        txt = txt + "+";
        String[] tab = new String[txt.split("[+-]").length];

        int c = 0;
        int index = 0;
        for (int i = 0; i < txt.length(); i++) {
            if (txt.charAt(i) == '+' || txt.charAt(i) == '-') {
                tab[index] = txt.substring(c, i);
                c = i;
                index++;
            }
        }

//        System.out.println(Arrays.toString(tab));

        if (sys == 0) {
            int res = 0;
            for (String i : tab) {
                res += Integer.parseInt(i, 16);
//                System.out.println(res);
            }

//            System.out.println(res);

            return Integer.toHexString(res).toUpperCase();
        } else if (sys == 1) {
            int res = 0;
            for (String i : tab) {
                res += Integer.parseInt(i);
            }

            return res + "";
        } else {
            int res = 0;
            for (String i : tab) {
                res += Integer.parseInt(i, 2);
            }

            return Integer.toBinaryString(res);
        }

    }

}
