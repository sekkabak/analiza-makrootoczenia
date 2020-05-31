package window;

import app.Config;
import app.Helper;
import layout.App;
import layout.Window;
import model.Area;
import model.Factor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Scenario extends Window {
    int scenarioType;

    JPanel top;
    JPanel center;

    JLabel areaName;

    String name;
    Area area;

    public Scenario(App app, String name, Area area, int type) {
        super(app);
        this.area = area;
        this.name = name;
        this.scenarioType = type;

        setBorder(new EmptyBorder(10, 10, 10, 10));
        BorderLayout bl = new BorderLayout();
        setLayout(bl);
        bl.setVgap(10);

        createTopPanel();
        center = new JPanel();
        createFactors();

        JScrollPane scrollPane = new JScrollPane(center, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        this.add(top);
        this.add(scrollPane);

        bl.addLayoutComponent(top, BorderLayout.PAGE_START);
        bl.addLayoutComponent(scrollPane, BorderLayout.CENTER);
    }

    private void createFactors() {
        if(scenarioType == 1)
            createFactors1();
        else
            createFactors2();
    }

    private void createFactors1() {
        center.removeAll();
        center.revalidate();

        center.setLayout(new GridLayout(area.factors.size() + 2, 2));
        center.setBackground(Config.color1);
        center.setForeground(Config.color4);
        center.setOpaque(true);
        center.setBorder(Config.border);
        center.setFont(Config.font);

        center.add(Helper.createLabel("Czynniki"));
        center.add(Helper.createLabel("Siła wpływu"));

        for (Factor x : area.factors) {
            center.add(Helper.createLabel(x.getName()));
            center.add(Helper.createField(""));
        }

        center.add(Helper.createLabel("Średnia"));
        center.add(Helper.createLabel(""));

        center.repaint();
    }

    private void createFactors2() {
        center.removeAll();
        center.revalidate();

        center.setLayout(new GridLayout(area.factors.size() + 2, 4));
        center.setBackground(Config.color1);
        center.setForeground(Config.color4);
        center.setOpaque(true);
        center.setBorder(Config.border);
        center.setFont(Config.font);

        center.add(Helper.createLabel("Czynniki"));
        center.add(Helper.createLabel("Prawdopodobieństwo"));
        center.add(Helper.createLabel("Siła wpływu „ujemna”"));
        center.add(Helper.createLabel("Siła wpływu „dodatnia”"));

        for (Factor x : area.factors) {
            center.add(Helper.createLabel(x.getName()));
            center.add(Helper.createField(""));
            center.add(Helper.createField(""));
            center.add(Helper.createField(""));
        }

        // TODO
        center.add(Helper.createLabel("Średnia"));
        center.add(Helper.createLabel(""));
        center.add(Helper.createLabel(""));
        center.add(Helper.createLabel(""));

        center.repaint();
    }

    private void createTopPanel() {
        top = new JPanel();
        top.setPreferredSize(new Dimension(100, 100));
        top.setBackground(Config.color2);
        top.setLayout(new GridLayout(2,2));

        top.add(Helper.createLabel("Scenariusz"));
        top.add(Helper.createLabel(name));
        top.add(Helper.createLabel("Nazwa sfery"));

        areaName = Helper.createLabel("");
        top.add(areaName);
    }

    @Override
    public void display() {
        areaName.setText(area.name);

        createFactors();
    }
}
