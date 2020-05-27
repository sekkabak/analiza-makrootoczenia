import javax.swing.*;
import java.awt.*;

public class Center extends JPanel {
    //WelcomeWindow first = new WelcomeWindow();
    AddFactors aF = new AddFactors();
    public Center(){
        this.setPreferredSize(new Dimension(400, 400));
        this.setBorder(Config.border);
        this.setBackground(Config.color2);
        //this.add(first);
        this.add(aF);
    }
}
