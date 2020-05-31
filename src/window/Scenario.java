package window;

import app.Config;
import layout.App;
import layout.Window;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Scenario extends Window {
    JPanel center;

    public Scenario(App app) {
        super(app);

//        this.setPreferredSize(new Dimension(Config.windowX, Config.windowY));
        this.setBackground(Config.color2);
        this.setBorder(new EmptyBorder(10, 0, 10, 0));

        BorderLayout borLay = new BorderLayout();
        this.setLayout(borLay);
        borLay.setVgap(10);

        //top
        JPanel top = new JPanel();
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        top.setLayout(gridBagLayout);
//        top.setPreferredSize(new Dimension(Config.windowX, 100));
        top.setBackground(Config.color2);
        c.fill = GridBagConstraints.HORIZONTAL;

        // TODO
        JLabel scenarioName = new JLabel("Scenariusz " + "TODO", SwingConstants.CENTER);
        scenarioName.setBackground(Config.color3);
        scenarioName.setForeground(Config.color4);
        scenarioName.setOpaque(true);
//        scenarioName.setPreferredSize(new Dimension(Config.windowX,50));
        scenarioName.setBorder(Config.border);
        scenarioName.setFont(Config.font);
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;
        top.add(scenarioName,c);

        JLabel nameSphere = new JLabel("Nazwa sfery", SwingConstants.CENTER);
        nameSphere.setBorder(Config.border);
        nameSphere.setFont(Config.font);
//        nameSphere.setPreferredSize(new Dimension(Config.windowX/2,50));
        nameSphere.setBackground(Config.color3);
        nameSphere.setForeground(Config.color4);
        nameSphere.setOpaque(true);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        top.add(nameSphere,c);

        JLabel label = new JLabel(".........", SwingConstants.CENTER);
        label.setBorder(Config.border);
        label.setFont(Config.font);
//        label.setPreferredSize(new Dimension(Config.windowX/2,50));
        label.setBackground(Config.color3);
        label.setForeground(Config.color4);
        label.setOpaque(true);
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 1;
        top.add(label,c);
        //

        //center
        //TODO: do zrobienia center i przesuwanie się :D
        center = new JPanel();
        createSphere();
        //

        //east
        JScrollBar scrollBar = new JScrollBar();
//        scrollBar.setPreferredSize(new Dimension(20, Config.windowY));
        scrollBar.setBackground(Config.color3);
        //

        this.add(top);
        this.add(center);
        this.add(scrollBar);

        borLay.addLayoutComponent(top, BorderLayout.NORTH);
        borLay.addLayoutComponent(center, BorderLayout.CENTER);
        borLay.addLayoutComponent(scrollBar, BorderLayout.EAST);
    }

    private void createSphere() {
        GridLayout gL = new GridLayout(5, 2);
        center.setLayout(gL);
        center.setBackground(Config.color1);
        center.setForeground(Config.color4);
//        center.setPreferredSize(new Dimension(Config.windowX, 50));
        center.setOpaque(true);
        center.setBorder(Config.border);
        center.setFont(Config.font);

        JLabel factor = new JLabel("Czynniki", SwingConstants.CENTER);
        factor.setBorder(Config.border);
        factor.setFont(Config.font);
//        factor.setPreferredSize(new Dimension(Config.windowX/2, 50));
        factor.setBackground(Config.color3);
        factor.setForeground(Config.color4);
        factor.setOpaque(true);
        center.add(factor);

        //labele na czynniki

        JLabel powerOfInfluence = new JLabel("Siła wpływu", SwingConstants.CENTER);
        powerOfInfluence.setBorder(Config.border);
        powerOfInfluence.setFont(Config.font);
//        powerOfInfluence.setPreferredSize(new Dimension(Config.windowX/2, 50));
        powerOfInfluence.setBackground(Config.color3);
        powerOfInfluence.setForeground(Config.color4);
        powerOfInfluence.setOpaque(true);
        center.add(powerOfInfluence);

        JLabel label1 = new JLabel("Czynnik 1", SwingConstants.CENTER);
        label1.setBorder(Config.border);
        label1.setFont(Config.font);
//        label1.setPreferredSize(new Dimension(Config.windowX/2, 50));
        label1.setBackground(Config.color3);
        label1.setForeground(Config.color4);
        label1.setOpaque(true);
        center.add(label1);

        JLabel label2 = new JLabel("siła wplywu 1", SwingConstants.CENTER);
        label2.setBorder(Config.border);
        label2.setFont(Config.font);
//        label2.setPreferredSize(new Dimension(Config.windowX/2, 50));
        label2.setBackground(Config.color3);
        label2.setForeground(Config.color4);
        label2.setOpaque(true);
        center.add(label2);

        JLabel label3 = new JLabel("Czynnik 2", SwingConstants.CENTER);
        label3.setBorder(Config.border);
        label3.setFont(Config.font);
//        label3.setPreferredSize(new Dimension(Config.windowX/2, 50));
        label3.setBackground(Config.color3);
        label3.setForeground(Config.color4);
        label3.setOpaque(true);
        center.add(label3);

        JLabel label4 = new JLabel("Siła wpływu 2", SwingConstants.CENTER);
        label4.setBorder(Config.border);
        label4.setFont(Config.font);
//        label4.setPreferredSize(new Dimension(Config.windowX/2, 50));
        label4.setBackground(Config.color3);
        label4.setForeground(Config.color4);
        label4.setOpaque(true);
        center.add(label4);


        // siły wpływu dla czynników
        JLabel label5 = new JLabel("Czynnik 3", SwingConstants.CENTER);
        label5.setBorder(Config.border);
        label5.setFont(Config.font);
//        label5.setPreferredSize(new Dimension(Config.windowX/2, 50));
        label5.setBackground(Config.color3);
        label5.setForeground(Config.color4);
        label5.setOpaque(true);
        center.add(label5);

        JLabel label6 = new JLabel("Siła wpływu 3", SwingConstants.CENTER);
        label6.setBorder(Config.border);
        label6.setFont(Config.font);
//        label6.setPreferredSize(new Dimension(Config.windowX/2, 50));
        label6.setBackground(Config.color3);
        label6.setForeground(Config.color4);
        label6.setOpaque(true);
        center.add(label6);

        // srednia
        JLabel average = new JLabel("Srednia", SwingConstants.CENTER);
        average.setBorder(Config.border);
        average.setFont(Config.font);
//        average.setPreferredSize(new Dimension(Config.windowX/2, 50));
        average.setBackground(Config.color3);
        average.setForeground(Config.color4);
        average.setOpaque(true);
        center.add(average);
        //

        //średnia wynik
        JLabel label0 = new JLabel("wynik", SwingConstants.CENTER);
        label0.setBorder(Config.border);
        label0.setFont(Config.font);
//        label0.setPreferredSize(new Dimension(Config.windowX/2, 50));
        label0.setBackground(Config.color3);
        label0.setForeground(Config.color4);
        label0.setOpaque(true);
        center.add(label0);
    }

    @Override
    public void display() {

    }
}
