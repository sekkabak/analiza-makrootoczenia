import javax.swing.*;
import java.awt.*;

public class AddFactors extends JPanel {
    public AddFactors(){
        this.setPreferredSize(new Dimension(380,380));
        this.setLayout(null);
        //NAZWA SFERY
        JPanel panel1 = new JPanel();
        panel1.setBorder(BorderFactory.createLineBorder(Color.black,3));
        panel1.setBounds(160,30,200,60);
        JLabel text1 = new JLabel("Nazwa sfery");
        text1.setFont(new Font("Consolas", Font.BOLD, 15));
        JLabel sphereName = new JLabel("(nazwa sfery)");
        sphereName.setFont(new Font("Consolas", Font.BOLD, 15));
        panel1.add(text1);
        panel1.add(sphereName);
        this.add(panel1);

        //NAZWA CZYNNIKA
        JPanel panel2 = new JPanel();
        panel2.setBounds(160,110,200,60);
        panel2.setBorder(BorderFactory.createLineBorder(Color.black,3));
        JLabel text2 = new JLabel("Nazwa czynnika");
        text2.setFont(new Font("Consolas", Font.BOLD, 15));
        JTextField factorName = new JTextField();
        factorName.setPreferredSize(new Dimension(100,20));
        factorName.setFont(new Font("Consolas", Font.BOLD, 15));
        panel2.add(text2);
        panel2.add(factorName);
        this.add(panel2);


        //WZROST
        JLabel wzrost = new JLabel(" Wzrost");
        wzrost.setBounds(20,200,125,30);
        wzrost.setBorder(BorderFactory.createLineBorder(Color.black,3));
        wzrost.setFont(new Font("Consolas", Font.BOLD, 15));
        this.add(wzrost);

        //STABILIZACJA
        JLabel stabilizacja = new JLabel(" Stabilizacja");
        stabilizacja.setBounds(20,230,125,30);
        stabilizacja.setBorder(BorderFactory.createLineBorder(Color.black,3));
        stabilizacja.setFont(new Font("Consolas", Font.BOLD, 15));
        this.add(stabilizacja);

        //REGRES
        JLabel regres = new JLabel(" Regres");
        regres.setBounds(20,260,125,30);
        regres.setBorder(BorderFactory.createLineBorder(Color.black,3));
        regres.setFont(new Font("Consolas", Font.BOLD, 15));
        this.add(regres);

        //SILY WPLYWU
        JTextField silaWzrost = new JTextField();
        silaWzrost.setBounds(160,200,60,30);
        silaWzrost.setBorder(BorderFactory.createLineBorder(Color.black,3));
        silaWzrost.setFont(new Font("Consolas", Font.BOLD, 15));
        this.add(silaWzrost);


        JTextField silaStabilizacja = new JTextField();
        silaStabilizacja.setBounds(160,230,60,30);
        silaStabilizacja.setBorder(BorderFactory.createLineBorder(Color.black,3));
        silaStabilizacja.setFont(new Font("Consolas", Font.BOLD, 15));
        this.add(silaStabilizacja);


        JTextField silaRegres = new JTextField();
        silaRegres.setBounds(160,260,60,30);
        silaRegres.setBorder(BorderFactory.createLineBorder(Color.black,3));
        silaRegres.setFont(new Font("Consolas", Font.BOLD, 15));
        this.add(silaRegres);

        //PRAWDOPODOBIENSTWO
        JTextField prawdopodobienstwoWzrost = new JTextField();
        prawdopodobienstwoWzrost.setBounds(235,200,60,30);
        prawdopodobienstwoWzrost.setBorder(BorderFactory.createLineBorder(Color.black,3));
        prawdopodobienstwoWzrost.setFont(new Font("Consolas", Font.BOLD, 15));
        this.add(prawdopodobienstwoWzrost);


        JTextField prawdopodobienstwoStabilizacja = new JTextField();
        prawdopodobienstwoStabilizacja.setBounds(235,230,60,30);
        prawdopodobienstwoStabilizacja.setBorder(BorderFactory.createLineBorder(Color.black,3));
        prawdopodobienstwoStabilizacja.setFont(new Font("Consolas", Font.BOLD, 15));
        this.add(prawdopodobienstwoStabilizacja);


        JTextField prawdopodobienstwoRegres = new JTextField();
        prawdopodobienstwoRegres.setBounds(235,260,60,30);
        prawdopodobienstwoRegres.setBorder(BorderFactory.createLineBorder(Color.black,3));
        prawdopodobienstwoRegres.setFont(new Font("Consolas", Font.BOLD, 15));
        this.add(prawdopodobienstwoRegres);


        //BUTTON ADD
        JButton plus = new JButton(" + ");
        plus.setBounds(280,320,80,30);
        plus.setFont(new Font("Consolas", Font.BOLD, 15));
        this.add(plus);
    }
}
