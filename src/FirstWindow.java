import javax.swing.*;
import java.awt.*;

public class FirstWindow extends JPanel {
    public FirstWindow() {
        JLabel text = new JLabel("TEKST XDD");
        text.setPreferredSize(new Dimension(380, 80));
        text.setBackground(Color.lightGray);
        this.add(text);
    }
}
