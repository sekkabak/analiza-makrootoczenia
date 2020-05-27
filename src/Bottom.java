import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Bottom extends JPanel {
    JProgressBar progressBar= new JProgressBar();
    JButton next = new JButton("DALEJ");
    public Bottom() {
        this.setPreferredSize(new Dimension(400, 50));
        this.setBackground(Config.color3);
        this.setBorder(new EmptyBorder(5,0,5,0));
        progressBar.setPreferredSize(new Dimension(315,30));
        this.add(progressBar);
        next.setPreferredSize(new Dimension(80,30));
        next.setBackground(Config.color1);
        next.setForeground(Config.color3);
        next.setFont(Config.font);
        this.add(next);
    }
}