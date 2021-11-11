package GUI.c6.z3;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public
    class Main {

    public Main() {
        main(new String[]{""});
    }

    public static void main(String[] args) {

        Map<JButton, LayoutManager> mapLayouts = new HashMap<>();

        JFrame jf = new JFrame();
        JPanel jp = new JPanel();
        jf.add(jp);
        jp.setLayout(new GridLayout(0,1));



        JButton jb1 = new JButton("BorderLayout");
        JButton jb2 = new JButton("FlowLayout (left)");
        JButton jb3 = new JButton("FlowLayout (right)");
        JButton jb4 = new JButton("FlowLayout");
        JButton jb5 = new JButton("GridLayout (1 row)");
        JButton jb6 = new JButton("GridLayout (1 column)");
        JButton jb7 = new JButton("GridLayout (3 rows, 2 columns)");
        JButton jb8 = new JButton("All layouts");
        JButton jb9 = new JButton("Quit");


        jp.add(jb1);
        jp.add(jb2);
        jp.add(jb3);
        jp.add(jb4);
        jp.add(jb5);
        jp.add(jb6);
        jp.add(jb7);
        jp.add(jb8);
        jp.add(jb9);


        mapLayouts.put(jb1, new BorderLayout());
        mapLayouts.put(jb2, new FlowLayout(FlowLayout.LEFT));
        mapLayouts.put(jb3, new FlowLayout(FlowLayout.RIGHT));
        mapLayouts.put(jb4, new FlowLayout());
        mapLayouts.put(jb5, new GridLayout(1,0));
        mapLayouts.put(jb6, new GridLayout(0,1));
        mapLayouts.put(jb7, new GridLayout(3,2));


        mapLayouts.forEach((k,v) -> {
            k.addActionListener(
                    (e) -> {
                        new MyFrame(v);
//                        System.out.println(v);
                        jf.setVisible(false);
                        jf.dispose();
                    }
            );
        });

        jb8.addActionListener(
                (e) ->{
                    new MyFrame();
                    jf.setVisible(false);
                    jf.dispose();
                }
        );

        jb9.addActionListener(
                (e) ->{
                    jf.setVisible(false);
                    jf.dispose();
                }
        );


        jf.setVisible(true);
        jf.setSize(500,600);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
