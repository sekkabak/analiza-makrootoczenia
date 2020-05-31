package window;

import app.Config;
import app.Helper;
import layout.App;
import layout.BindedTextField;
import layout.Window;
import model.Area;
import model.Factor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class FillFactor extends Window {
    Area area;
    Factor factor;

    JPanel top;
    JLabel areaName;
    JTextField factorName;

    JPanel center;
    ArrayList<JTextField> influenceTextFields = new ArrayList<>();
    ArrayList<JTextField> probabilityTextFields = new ArrayList<>();

    JPanel south;

    public FillFactor(App app, Area area, Factor factor) {
        super(app);
        this.area = area;
        this.factor = factor;

        this.setBorder(new EmptyBorder(10, 10, 10, 10));

        BorderLayout bL = new BorderLayout();
        this.setLayout(bL);
        bL.setVgap(10);

        createTopPanel();
        createCenterPanel();
        createSouthPanel();

        this.add(top);
        this.add(center);
        this.add(south);

        bL.addLayoutComponent(top, BorderLayout.NORTH);
        bL.addLayoutComponent(center, BorderLayout.CENTER);
        bL.addLayoutComponent(south, BorderLayout.SOUTH);
    }

    private void createTopPanel() {
        top = new JPanel();
        top.setPreferredSize(new Dimension(100, 100));
        top.setBackground(Config.color2);
        top.setLayout(new GridLayout(2, 2));

        top.add(Helper.createLabel("Nazwa sfery"));

        // Label nazwa sfery
        areaName = Helper.createLabel("");
        top.add(areaName);

        top.add(Helper.createLabel("Nazwa czynnika"));

        // textbox wprowadzenie nazwy czynnika
        factorName = Helper.createBindedField("", factor, "name");
        top.add(factorName);
    }

    private void createCenterPanel() {
        center = new JPanel();
        center.setLayout(new GridLayout(3, 3, 2, 2));
        center.setBackground(Config.color2);

        for (int i = 0; i < 3; i++) {
            center.add(Helper.createLabel(Config.factorPartsName[i]));

            // wpływ
            JTextField influence = Helper.createBindedField(factor.rows.get(i).getInfluence(), factor.rows.get(i), "influence");
            influenceTextFields.add(influence);
            center.add(influence);

            // prawdopodobieństwo
            JTextField probability = Helper.createBindedField(factor.rows.get(i).getProbability(), factor.rows.get(i), "probability");
            probabilityTextFields.add(probability);
            center.add(probability);
        }
    }

    private void createSouthPanel() {
        south = new JPanel();
        south.setLayout(new FlowLayout(FlowLayout.RIGHT));
        south.setBackground(Config.color2);

        // dodawanie nowego czynnika
        JButton b = new JButton("+");
        b.setFocusPainted(false);
        b.setPreferredSize(new Dimension(50, 30));
        b.setFont(Config.font);
        b.setBackground(Config.color1);
        b.setForeground(Config.color3);
        b.addActionListener(e -> addNewFactor());
        south.add(b);
    }

    public void addNewFactor() {
        app.addFactorPage();
    }

    @Override
    public void display() {
        this.app.dataManager.setCurrentArea(area);

        this.areaName.setText(area.name);
        this.factorName.setText(factor.getName());

        for (int i = 0; i < 3; i++) {
            influenceTextFields.get(i).setText(factor.rows.get(i).getInfluence());
            probabilityTextFields.get(i).setText(factor.rows.get(i).getProbability());
        }
    }
}
