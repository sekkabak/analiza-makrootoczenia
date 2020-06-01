package window;

import app.Config;
import app.Helper;
import layout.App;
import layout.AvgGetter;
import layout.AvgRefresh;
import layout.Window;
import model.Area;
import model.Factor;
import model.FirstTwoScenarios;
import model.SecondTwoScenarios;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Scenario extends Window {
    int scenarioType;

    JPanel top;
    JPanel center;

    JLabel areaName;

    String name;
    Area area;

    FirstTwoScenarios scenario1;
    SecondTwoScenarios scenario2;

    Map<AvgGetter, JLabel> labelsToUpdate = new HashMap<>();
    AvgRefresh refresh = this::updateLabels;

    public Scenario(App app, String name, Area area, int type, Object scenario) {
        super(app);
        this.area = area;
        this.name = name;
        this.scenarioType = type;

        if (type == 1) {
            scenario1 = (FirstTwoScenarios) scenario;
        } else {
            scenario2 = (SecondTwoScenarios) scenario;
        }

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
        if (scenarioType == 1) {
            createFactors1();
            if(app.auto_fill) {
                autoFill1();
            }
        } else {
            createFactors2();
            if(app.auto_fill) {
                autoFill2();
            }
        }
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

        int size = area.factors.size();
        for (int i = 0; i < size; i++)
        {
            Factor x = area.factors.get(i);
            center.add(Helper.createLabel(x.getName()));
            center.add(Helper.createBindedFieldList(scenario1.infuences.get(i), scenario1, "infuences", i, refresh));
        }

        center.add(Helper.createLabel("Średnia"));
        JLabel average = Helper.createLabel(scenario1.average);
        labelsToUpdate.put(scenario1.getAverage, average);

        center.add(average);

        center.repaint();
    }

    private void autoFill1() {

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

    private void autoFill2() {

    }

    /**
     * Odświerza wszystkie sumy / średnie
     */
    public void updateLabels() {
        for (Map.Entry me : labelsToUpdate.entrySet()) {
            JLabel label = (JLabel) me.getValue();
            AvgGetter getter = (AvgGetter) me.getKey();
            label.setText(getter.getAverage());
        }
    }

    private void createTopPanel() {
        top = new JPanel();
        top.setPreferredSize(new Dimension(100, 100));
        top.setBackground(Config.color2);
        top.setLayout(new GridLayout(2, 2));

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
