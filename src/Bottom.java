import javax.swing.*;
import java.awt.*;

public class Bottom extends JPanel {
    JProgressBar progressBar= new JProgressBar();
    JButton next = new JButton("NEXT");
    public Bottom() {
        this.setPreferredSize(new Dimension(400, 50));
        this.setBackground(Color.LIGHT_GRAY);
        progressBar.setPreferredSize(new Dimension(300,30));
        this.add(progressBar);
        next.setPreferredSize(new Dimension(80,30));
        this.add(next);
    }
}