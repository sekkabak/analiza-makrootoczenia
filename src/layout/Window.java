package layout;

import app.Config;

import javax.swing.*;

public abstract class Window extends JPanel {
    public App app;
    public boolean factorWindow = false;

    public Window(App app) {
        this.app = app;
        this.setBackground(Config.color2);
        this.setVisible(true);
    }

    /**
     * Ta metoda będzie wywoływana co wyświetlenie ekranu.
     * Wszystkie dane zależne od źródeł zewnętrznych powinny być ustawiane w niej
     */
    public abstract void display();
}
