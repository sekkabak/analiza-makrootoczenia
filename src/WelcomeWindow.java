import javax.swing.*;
import java.awt.*;

public class WelcomeWindow extends JPanel {
    public WelcomeWindow() {
        JLabel text = new JLabel("Tu będzie opis XD", SwingConstants.CENTER);
        text.setFont(Config.font);
        text.setForeground(Config.color1);
        text.setPreferredSize(new Dimension(Config.windowX, Config.windowY));
        this.setBackground(Config.color2);
        this.add(text);
    }
}
