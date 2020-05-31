package window;

import app.Config;
import app.Helper;
import layout.App;
import layout.ScrollPane;
import layout.Window;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Scenario extends Window {
    JPanel top;
    JPanel center;
    String name;

    public Scenario(App app, String name) {
        super(app);
        this.name = name;

        setBorder(new EmptyBorder(10, 10, 10, 10));
        BorderLayout bl = new BorderLayout();
        setLayout(bl);
        bl.setVgap(10);

        createTopPanel();
        center = new ScrollPane();
        createFactors();

        this.add(top);
        this.add(center);

        bl.addLayoutComponent(top, BorderLayout.PAGE_START);
        bl.addLayoutComponent(center, BorderLayout.CENTER);
    }

    private void createFactors() {
        // TODO change rows dynamically
        center.removeAll();
        center.revalidate();

        center.setLayout(new GridLayout(5, 2));
        center.setBackground(Config.color1);
        center.setForeground(Config.color4);
        center.setOpaque(true);
        center.setBorder(Config.border);
        center.setFont(Config.font);

//        center.add(Helper.createLabel("Czynniki"));
//        center.add(Helper.createLabel("Siła wpływu"));
//        center.add(Helper.createLabel("Czynnik 1"));
//        center.add(Helper.createLabel("Czynniki"));
//        center.add(Helper.createLabel("Czynniki"));
//        center.add(Helper.createLabel("Czynniki"));

        JLabel label1 = new JLabel("Czynnik 1", SwingConstants.CENTER);
        label1.setBorder(Config.border);
        label1.setFont(Config.font);
        label1.setBackground(Config.color3);
        label1.setForeground(Config.color4);
        label1.setOpaque(true);
        center.add(label1);

        JLabel label2 = new JLabel("siła wplywu 1", SwingConstants.CENTER);
        label2.setBorder(Config.border);
        label2.setFont(Config.font);
        label2.setBackground(Config.color3);
        label2.setForeground(Config.color4);
        label2.setOpaque(true);
        center.add(label2);

        JLabel label3 = new JLabel("Czynnik 2", SwingConstants.CENTER);
        label3.setBorder(Config.border);
        label3.setFont(Config.font);
        label3.setBackground(Config.color3);
        label3.setForeground(Config.color4);
        label3.setOpaque(true);
        center.add(label3);

        JLabel label4 = new JLabel("Siła wpływu 2", SwingConstants.CENTER);
        label4.setBorder(Config.border);
        label4.setFont(Config.font);
        label4.setBackground(Config.color3);
        label4.setForeground(Config.color4);
        label4.setOpaque(true);
        center.add(label4);


        // siły wpływu dla czynników
        JLabel label5 = new JLabel("Czynnik 3", SwingConstants.CENTER);
        label5.setBorder(Config.border);
        label5.setFont(Config.font);
        label5.setBackground(Config.color3);
        label5.setForeground(Config.color4);
        label5.setOpaque(true);
        center.add(label5);

        JLabel label6 = new JLabel("Siła wpływu 3", SwingConstants.CENTER);
        label6.setBorder(Config.border);
        label6.setFont(Config.font);
        label6.setBackground(Config.color3);
        label6.setForeground(Config.color4);
        label6.setOpaque(true);
        center.add(label6);

        // srednia
        JLabel average = new JLabel("Srednia", SwingConstants.CENTER);
        average.setBorder(Config.border);
        average.setFont(Config.font);
        average.setBackground(Config.color3);
        average.setForeground(Config.color4);
        average.setOpaque(true);
        center.add(average);
        //

        //średnia wynik
        JLabel label0 = new JLabel("wynik", SwingConstants.CENTER);
        label0.setBorder(Config.border);
        label0.setFont(Config.font);
        label0.setBackground(Config.color3);
        label0.setForeground(Config.color4);
        label0.setOpaque(true);
        center.add(label0);
    }

    private void createTopPanel() {
        top = new JPanel();
        top.setPreferredSize(new Dimension(100, 100));
        top.setBackground(Config.color2);
        top.setLayout(new GridLayout(2,2));

        top.add(Helper.createLabel("Scenariusz"));
        top.add(Helper.createLabel(name));
        top.add(Helper.createLabel("Nazwa sfery"));
        top.add(Helper.createLabel("........."));
    }

    @Override
    public void display() {
        // TODO
    }
}
