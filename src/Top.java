import javax.swing.*;
import java.awt.*;

public class Top extends JPanel {
    JLabel title = new JLabel("ANALIZA MAKROOTOCZENIA");
    JLabel metodTitle = new JLabel("SCENARIUSZE STANÓW OTOCZENIA");
    public Top() {
        this.setPreferredSize(new Dimension(400, 80));
        this.setBackground(Color.LIGHT_GRAY);
        title.setFont(new Font("Consolas",Font.BOLD,30));
        title.setPreferredSize(new Dimension(400,30));
        metodTitle.setFont(new Font("Consolas",Font.BOLD,20));
        metodTitle.setPreferredSize(new Dimension(400,30));
        this.add(title);
        this.add(metodTitle);
    }
}
