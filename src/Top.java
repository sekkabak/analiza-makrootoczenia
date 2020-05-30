import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Top extends JPanel {
    JLabel title = new JLabel("ANALIZA MAKROOTOCZENIA", SwingConstants.CENTER);
    JLabel metodTitle = new JLabel("SCENARIUSZE STANÓW OTOCZENIA", SwingConstants.CENTER);

    public Top() {
        this.setPreferredSize(new Dimension(400, 80));
        this.setBackground(Config.color3);
        this.setBorder(new EmptyBorder(10, 0, 10, 0));
        title.setFont(new Font("Consolas", Font.BOLD, 30));
        title.setForeground(Config.color5);
        title.setPreferredSize(new Dimension(400, 30));
        metodTitle.setFont(new Font("Consolas", Font.BOLD, 20));
        metodTitle.setForeground(Config.color5);
        metodTitle.setPreferredSize(new Dimension(400, 30));
        this.add(title);
        this.add(metodTitle);
    }
}
