package layout;

import javax.swing.*;
import java.awt.*;


public class Center extends JPanel{
    App app;

    public Center(App app){
        this.app = app;
        setBackground(Color.lightGray);

        // ustawienie na cały ekran
        setLayout(new GridLayout(0, 1));
    }

    public void displayWindow(Window window) {
        removeAll();
        revalidate();
        window.setVisible(true);
        window.display();
        add(window);
        repaint();
    }
}
