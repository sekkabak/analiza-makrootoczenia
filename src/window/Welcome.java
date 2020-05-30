package window;
import layout.App;
import layout.Window;

import app.Config;

import javax.swing.*;

public class Welcome extends Window {
    public Welcome(App app) {
        super(app);

        JLabel text = new JLabel(Config.AppDescription, SwingConstants.CENTER);
        text.setFont(Config.font);
        text.setForeground(Config.color1);
        this.add(text);
    }

    @Override
    public void display() {

    }
}
