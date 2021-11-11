import game.Menu;
import model.ModelBuilder;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(()-> {
            new Menu();
        });
    }
}
