import javax.swing.*;
import java.awt.*;


public class Bottom extends JPanel {
    JProgressBar progressBar= new JProgressBar();
    public static JButton next = new JButton("NEXT");
    public Bottom(Center center) {
        this.setPreferredSize(new Dimension(400, 50));
        this.setBackground(Color.LIGHT_GRAY);
        progressBar.setPreferredSize(new Dimension(300,30));
        this.add(progressBar);
        next.setPreferredSize(new Dimension(80,30));
        this.add(next);
        next.addActionListener(e -> center.change());
    }

}