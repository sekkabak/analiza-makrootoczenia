package window;
import app.Helper;
import layout.App;
import layout.Window;

import app.Config;

import javax.swing.*;
import java.awt.*;

public class Welcome extends Window {
    JCheckBox checkBox;
    public Welcome(App app) {
        super(app);
        BorderLayout borderLayout = new BorderLayout();
        setLayout(borderLayout);
        JPanel center = new JPanel();
        JPanel bottom = new JPanel();
        center.setBackground(Config.color3);
        bottom.setBackground(Config.color3);
        center.setOpaque(true);
        bottom.setOpaque(true);
        add(center);
        add(bottom);
        borderLayout.addLayoutComponent(bottom, BorderLayout.SOUTH);
        borderLayout.addLayoutComponent(center, BorderLayout.CENTER);

        JLabel text = new JLabel(Config.AppDescription, SwingConstants.CENTER);
        text.setFont(Config.font);
        //TODO: text ma być w wielkości centera
        text.setPreferredSize(new Dimension(800,500));
        text.setForeground(Config.color1);
        text.setBackground(Config.color3);
        text.setOpaque(true);
        center.add(text);

        bottom.add(Helper.createLabel("Wykonał(a):"));
        JTextField creator = Helper.createBindedField("", app.dataManager, "creator");
        creator.setPreferredSize(new Dimension (300,50));
        bottom.add(creator);

        bottom.add(Helper.createLabel("Data:"));
        bottom.add(Helper.createBindedField("", app.dataManager, "date"));

        checkBox = new JCheckBox("Auto scenariusze", true);
        checkBox.addActionListener(e -> app.auto_fill = !app.auto_fill);
        checkBox.setBackground(Config.color3);
        checkBox.setFocusPainted(false);
        bottom.add(checkBox);
    }

    @Override
    public void display() {
        checkBox.setSelected(app.auto_fill);
    }
}
