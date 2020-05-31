package window;
import layout.App;
import layout.Window;

import app.Config;

import javax.swing.*;
import java.awt.*;

public class Welcome extends Window {
    JCheckBox checkBox;
    public Welcome(App app) {
        super(app);
        JLabel text = new JLabel(Config.AppDescription, SwingConstants.CENTER);
        text.setFont(Config.font);
        text.setForeground(Config.color1);
        this.add(text);

        checkBox = new JCheckBox("Auto scenariusze", true);
        checkBox.addActionListener(e -> app.auto_scenario = !app.auto_scenario);
        add(checkBox);
    }

    @Override
    public void display() {
        checkBox.setSelected(app.auto_scenario);
    }
}
