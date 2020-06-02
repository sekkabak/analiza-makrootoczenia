package window;
import app.Helper;
import layout.App;
import layout.Window;

import app.Config;

import javax.swing.*;

public class Welcome extends Window {
    JCheckBox checkBox;
    public Welcome(App app) {
        super(app);
        JLabel text = new JLabel(Config.AppDescription, SwingConstants.CENTER);
        text.setFont(Config.font);
        text.setForeground(Config.color1);
        add(text);

        add(Helper.createLabel("Wykonał(a):"));
        add(Helper.createBindedField("", app.dataManager, "creator"));

        add(Helper.createLabel("Data:"));
        add(Helper.createBindedField("", app.dataManager, "date"));

        checkBox = new JCheckBox("Auto scenariusze", true);
        checkBox.addActionListener(e -> app.auto_fill = !app.auto_fill);
        add(checkBox);
    }

    @Override
    public void display() {
        checkBox.setSelected(app.auto_fill);
    }
}
