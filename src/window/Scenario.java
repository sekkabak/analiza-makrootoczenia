package window;

import app.Config;
import app.Helper;
import layout.App;
import layout.AvgGetter;
import layout.AvgRefresh;
import layout.Window;
import model.*;

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
            if (app.auto_fill) {
                autoFill1();
            }
            createFactors1();
        } else {
            if (app.auto_fill) {
                autoFill2();
            }
            createFactors2();
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
        for (int i = 0; i < size; i++) {
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
        int size = area.factors.size();
        for (int i = 0; i < size; i++) {
            Factor f = area.factors.get(i);
            String res = "";
            if (this.name.equals("optymistyczny")) {
                double max = -5;
                for (FactorPart x : f.rows) {
                    try {
                        double tmp = Double.parseDouble(x.getInfluence());
                        if (max < tmp) {
                            max = tmp;
                            res = x.getInfluence();
                        }
                    } catch (Exception ignored) {
                    }
                }
            } else if (this.name.equals("pesymistyczny")) {
                double min = 5;
                for (FactorPart x : f.rows) {
                    try {
                        double tmp = Double.parseDouble(x.getInfluence());
                        if (min > tmp) {
                            min = tmp;
                            res = x.getInfluence();
                        }
                    } catch (Exception ignored) {
                    }
                }
            }

            scenario1.infuences.set(i, res);
            scenario1.calculateAvarage();
        }
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

        int size = area.factors.size();
        for (int i = 0; i < size; i++) {
            Factor x = area.factors.get(i);
            center.add(Helper.createLabel(x.getName()));
            center.add(Helper.createBindedFieldList(scenario2.probability.get(i), scenario2, "probability", i, refresh));
            center.add(Helper.createBindedFieldList(scenario2.negativeInfuences.get(i), scenario2, "negativeInfuences", i, refresh));
            center.add(Helper.createBindedFieldList(scenario2.positiveInfuences.get(i), scenario2, "positiveInfuences", i, refresh));
        }

        center.add(Helper.createLabel("Średnia"));
        center.add(Helper.createLabel(""));

        JLabel negativeInfuenceAverage = Helper.createLabel(scenario2.negativeInfuenceAverage);
        labelsToUpdate.put(scenario2.getNegativeInfuenceAverage, negativeInfuenceAverage);
        center.add(negativeInfuenceAverage);

        JLabel positiveInfuenceAverage = Helper.createLabel(scenario2.positiveInfuenceAverage);
        labelsToUpdate.put(scenario2.getPositiveInfuenceAverage, positiveInfuenceAverage);
        center.add(positiveInfuenceAverage);

        center.repaint();
    }

    private void autoFill2() {
        int size = area.factors.size();
        for (int i = 0; i < size; i++) {
            Factor f = area.factors.get(i);
            String res = "";
            String res1 = "";
            boolean isNegative = false;
            if (this.name.equals("najbardziej prawdopodobny")) {
                double max = 0;
                for (FactorPart x : f.rows) {
                    try {
                        double tmp = Double.parseDouble(x.getProbability());
                        if (max < tmp) {
                            isNegative = Double.parseDouble(x.getInfluence()) < 0;
                            max = tmp;
                            res = x.getProbability();
                            res1 = x.getInfluence();
                        }
                    } catch (Exception ignored) {
                    }
                }
            } else if (this.name.equals("niespodziankowy")) {
                double min = 1.0;
                for (FactorPart x : f.rows) {
                    try {
                        double tmp = Double.parseDouble(x.getProbability());
                        if (min > tmp) {
                            isNegative = Double.parseDouble(x.getInfluence()) < 0;
                            min = tmp;
                            res = x.getProbability();
                            res1 = x.getInfluence();
                        }
                    } catch (Exception ignored) {
                    }
                }
            }

            scenario2.probability.set(i, res);
            if(isNegative) {
                scenario2.negativeInfuences.set(i, res1);
            } else {
                scenario2.positiveInfuences.set(i, res1);
            }

//            scenario2.calculateProbabilityAverage();
            scenario2.calculateNegativeInfuenceAverage();
            scenario2.calculatePositiveInfuenceAverage();
        }
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
