import javax.swing.*;
import java.awt.*;

public class Center extends JPanel {
    //FirstWindow first = new FirstWindow();
    AddFactors addFactors = new AddFactors();
    public Center(){
        this.setPreferredSize(new Dimension(400, 400));
        this.setBackground(Color.BLACK);
        //this.add(first);
        this.add(addFactors);

    }
}
