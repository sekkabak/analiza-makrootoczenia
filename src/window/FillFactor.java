package window;

import app.Config;
import layout.App;
import layout.BindedTextField;
import layout.Window;
import model.Area;
import model.Factor;
import model.FactorPart;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FillFactor extends Window {
    Area area;
    Factor factor;

    JPanel top;
    JLabel areaName;
    JTextField factorName;

    JPanel center;

    JPanel south;

    public FillFactor(App app, Area area) {
        super(app);
        this.area = area;

        this.factor = new Factor("Nowy czynnik");
        this.area.factors.add(this.factor);

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
        top.setBackground(Config.color2);
        top.setLayout(new GridLayout(2, 2, 5, 5));

        // label nazwa sfery
        JLabel areaNameLabel = new JLabel("Nazwa sfery", SwingConstants.CENTER);
        areaNameLabel.setBackground(Config.color3);
        areaNameLabel.setOpaque(true);
        areaNameLabel.setForeground(Config.color4);
        areaNameLabel.setBorder(Config.border);
        areaNameLabel.setFont(Config.font);

        // Label nazwa sfery
        areaName = new JLabel("", SwingConstants.CENTER);
        areaName.setBackground(Config.color3);
        areaName.setOpaque(true);
        areaName.setBorder(Config.border);
        areaName.setFont(Config.font);

        // label nazwa czynnika
        JLabel factorNameLabel = new JLabel("Nazwa czynnika", SwingConstants.CENTER);
        factorNameLabel.setBackground(Config.color3);
        factorNameLabel.setOpaque(true);
        factorNameLabel.setForeground(Config.color4);
        factorNameLabel.setBorder(Config.border);
        factorNameLabel.setFont(Config.font);

        // textbox wprowadzenie nazwy czynnika
        factorName = new JTextField("");
        factorName.setBackground(Config.color3);
        factorName.setOpaque(true);
        factorName.setBorder(Config.border);
        factorName.setFont(Config.font);
        factorName.setHorizontalAlignment(JTextField.CENTER);
        factorName.setForeground(Config.color1);
        factorName.getDocument().addDocumentListener(new BindedTextField(factor, "name"));

        top.add(areaNameLabel);
        top.add(areaName);
        top.add(factorNameLabel);
        top.add(factorName);
    }

    private void createCenterPanel() {
        center = new JPanel();
        center.setLayout(new GridLayout(3, 3, 2, 2));
        center.setBackground(Config.color2);

        for (int i = 0; i < 3; i++) {
            factor.rows[i] = new FactorPart(Config.factorPartsName[i]);
            JLabel label = new JLabel(Config.factorPartsName[i], SwingConstants.CENTER);
            label.setPreferredSize(new Dimension(150, 50));
            label.setBorder(Config.border);
            label.setOpaque(true);
            label.setForeground(Config.color4);
            label.setFont(Config.font);
            label.setBackground(Config.color3);

            center.add(label);

            // wpływ
            center.add(createFactorPartsField(factor.rows[i].getInfluence(), factor.rows[i], "influence"));

            // prawdopodobieństwo
            center.add(createFactorPartsField(factor.rows[i].getProbability(), factor.rows[i], "probability"));
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

    private JTextField createFactorPartsField(String value, Object model, String fieldName) {
        JTextField text = new JTextField(value, SwingConstants.CENTER);
        text.setPreferredSize(new Dimension(150, 50));
        text.setBorder(Config.border);
        text.setOpaque(true);
        text.setFont(Config.font);
        text.setBackground(Config.color3);
        text.setForeground(Config.color1);
        text.setHorizontalAlignment(JTextField.CENTER);
        text.getDocument().addDocumentListener(new BindedTextField(model, fieldName));

        return text;
    }

    public void addNewFactor() {
        app.addFactorPage();
    }

    @Override
    public void display() {
        this.app.dataManager.setCurrentArea(area);

        this.areaName.setText(area.name);
        this.factorName.setText(factor.getName());

        // TODO zmiana wartośći po wczytaniu nowego czynnika
    }
}
