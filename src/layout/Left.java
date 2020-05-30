package layout;

import app.Config;

import javax.swing.*;
import java.awt.*;

public class Left extends JPanel {
    public Left() {
        this.setPreferredSize(new Dimension(Config.frameMarginLeftRight, 0));
        this.setBackground(Config.color3);
    }
}
