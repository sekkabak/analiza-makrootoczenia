import javax.swing.*;
import java.awt.*;

public class Center extends JPanel {
    FirstWindow first = new FirstWindow();
    public Center(){
        this.setPreferredSize(new Dimension(400, 100));
        this.setBackground(Color.WHITE);
        this.add(first);
    }
}
