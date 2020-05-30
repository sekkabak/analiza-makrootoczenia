package window;

import app.Config;
import layout.App;
import layout.Window;
import model.Area;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AddFactors extends Window {
    Area area;

    public AddFactors(App app, Area area) {
        super(app);
        this.factorWindow = true;
        this.area = area;

//        this.setPreferredSize(new Dimension(Config.windowX, Config.windowY));
        this.setBackground(Config.color2);
        this.setBorder(new EmptyBorder(10, 0, 10, 0));

        BorderLayout bL = new BorderLayout();
        this.setLayout(bL);
        bL.setVgap(10);

        //top
        JPanel top = new JPanel();
        GridLayout grid = new GridLayout(2, 2, 5, 5);
        top.setLayout(grid);
//        top.setPreferredSize(new Dimension(Config.windowX, 150));
        top.setBackground(Config.color2);
        JLabel nameSphere = new JLabel("Nazwa sfery", SwingConstants.CENTER);
        nameSphere.setBackground(Config.color3);
        nameSphere.setOpaque(true);
        nameSphere.setForeground(Config.color4);
        nameSphere.setBorder(Config.border);
        nameSphere.setFont(Config.font);

        //TODO: tutaj będzie nazwa sfery :D
        JLabel nameSphereText = new JLabel("", SwingConstants.CENTER);
        nameSphereText.setBackground(Config.color3);
        nameSphereText.setOpaque(true);
        nameSphereText.setBorder(Config.border);
        nameSphereText.setFont(Config.font);

        JLabel nameFactor = new JLabel("Nazwa czynnika", SwingConstants.CENTER);
        nameFactor.setBackground(Config.color3);
        nameFactor.setOpaque(true);
        nameFactor.setForeground(Config.color4);
        nameFactor.setBorder(Config.border);
        nameFactor.setFont(Config.font);

        //TODO: tutaj użytkownik wprowadza nazwę czynnika
        JTextField nameFactorText = new JTextField();
        nameFactorText.setBackground(Config.color3);
        nameFactorText.setOpaque(true);
        nameFactorText.setBorder(Config.border);
        nameFactorText.setFont(Config.font);
        nameFactorText.setHorizontalAlignment(JTextField.CENTER);
        nameFactorText.setForeground(Config.color1);

        top.add(nameSphere);
        top.add(nameSphereText);
        top.add(nameFactor);
        top.add(nameFactorText);
        //


        //center
        JPanel center = new JPanel();
        GridLayout gL = new GridLayout(3, 3, 2, 2);
        center.setLayout(gL);
        center.setPreferredSize(new Dimension(380, 170));
        center.setBackground(Config.color2);

        JLabel label = new JLabel("Wzrost", SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(150, 50));
        label.setBorder(Config.border);
        label.setOpaque(true);
        label.setForeground(Config.color4);
        label.setFont(Config.font);
        label.setBackground(Config.color3);

        center.add(label);

        //TODO: tutaj użytkownik wprowadza siłę wpływu do wzrostu
        JTextField text1 = new JTextField(SwingConstants.CENTER);
        text1.setPreferredSize(new Dimension(150, 50));
        text1.setBorder(Config.border);
        text1.setOpaque(true);
        text1.setFont(Config.font);
        text1.setBackground(Config.color3);
        text1.setForeground(Config.color1);
        text1.setHorizontalAlignment(JTextField.CENTER);

        center.add(text1);

        //TODO: tutaj użytkownik wprowadza prawdopodobieństwo do wzrostu
        JTextField text2 = new JTextField(SwingConstants.CENTER);
        text2.setPreferredSize(new Dimension(150, 50));
        text2.setBackground(Config.color3);
        text2.setOpaque(true);
        text2.setFont(Config.font);
        text2.setBorder(Config.border);
        text2.setForeground(Config.color1);
        text2.setHorizontalAlignment(JTextField.CENTER);

        center.add(text2);

        JLabel label3 = new JLabel("Stabilizacja", SwingConstants.CENTER);
        label3.setPreferredSize(new Dimension(150, 50));
        label3.setBackground(Config.color3);
        label3.setOpaque(true);
        label3.setForeground(Config.color4);
        label3.setFont(Config.font);
        label3.setBorder(Config.border);

        center.add(label3);

        //TODO: tutaj użytkownik wprowadza siłę wpływu do stabilizacji
        JTextField text4 = new JTextField(SwingConstants.CENTER);
        text4.setPreferredSize(new Dimension(150, 50));
        text4.setBackground(Config.color3);
        text4.setOpaque(true);
        text4.setFont(Config.font);
        text4.setBorder(Config.border);
        text4.setForeground(Config.color1);
        text4.setHorizontalAlignment(JTextField.CENTER);

        center.add(text4);

        //TODO: tutaj użytkownik wprowadza prawdopodobieństwo do stabilizacji
        JTextField text5 = new JTextField(SwingConstants.CENTER);
        text5.setPreferredSize(new Dimension(150, 50));
        text5.setBackground(Config.color3);
        text5.setOpaque(true);
        text5.setFont(Config.font);
        text5.setBorder(Config.border);
        text5.setForeground(Config.color1);
        text5.setHorizontalAlignment(JTextField.CENTER);

        center.add(text5);

        JLabel label6 = new JLabel("Regres", SwingConstants.CENTER);
        label6.setPreferredSize(new Dimension(150, 50));
        label6.setBackground(Config.color3);
        label6.setFont(Config.font);
        label6.setForeground(Config.color4);
        label6.setOpaque(true);
        label6.setBorder(Config.border);

        center.add(label6);

        //TODO: tutaj użytkownik wprowadza siłę wpływu do regresu
        JTextField text7 = new JTextField(SwingConstants.CENTER);
        text7.setPreferredSize(new Dimension(150, 50));
        text7.setBackground(Config.color3);
        text7.setOpaque(true);
        text7.setFont(Config.font);
        text7.setForeground(Config.color1);
        text7.setBorder(Config.border);
        text7.setHorizontalAlignment(JTextField.CENTER);

        center.add(text7);

        //TODO: tutaj użytkownik wprowadza prawdopodobieństwo do regresu
        JTextField text8 = new JTextField(SwingConstants.CENTER);
        text8.setPreferredSize(new Dimension(150, 50));
        text8.setBackground(Config.color3);
        text8.setOpaque(true);
        text8.setFont(Config.font);
        text8.setForeground(Config.color1);
        text8.setBorder(Config.border);
        text8.setHorizontalAlignment(JTextField.CENTER);

        center.add(text8);


        //bottom
        JPanel south = new JPanel();
        FlowLayout fL = new FlowLayout(FlowLayout.RIGHT);
        south.setLayout(fL);
//        south.setPreferredSize(new Dimension(Config.windowX, 35));
        south.setBackground(Config.color2);

        //TODO: przycisk do dodawania czynnika
        JButton southButton = new JButton("+");
        southButton.setPreferredSize(new Dimension(50, 30));
        southButton.setFont(Config.font);
        southButton.setBackground(Config.color1);
        southButton.setForeground(Config.color3);
        south.add(southButton);
        //

        this.add(top);
        this.add(center);
        this.add(south);

        bL.addLayoutComponent(top, BorderLayout.NORTH);
        bL.addLayoutComponent(center, BorderLayout.CENTER);
        bL.addLayoutComponent(south, BorderLayout.SOUTH);

    }

    @Override
    public void display() {

    }
}
