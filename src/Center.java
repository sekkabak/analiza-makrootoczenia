import javax.swing.*;
import java.awt.*;

public class Center extends JPanel {
    JLabel text;
    public Center(){
        this.setPreferredSize(new Dimension(400, 100));
        this.setBackground(Color.WHITE);
        firstWindow();
    }
    private void firstWindow(){
        text = new JLabel("TEKST XDD");
        text.setPreferredSize(new Dimension(380,80));
        text.setBackground(Color.lightGray);
        this.add(text);
    }

    private void secondWindow(){

    }
}
