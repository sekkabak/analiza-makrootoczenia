package layout;

import javax.swing.*;
import java.awt.*;

public class ScrollPane extends JPanel {
    private JScrollPane vertical;
    private JTextArea console;

    public ScrollPane()
    {
        setPreferredSize(new Dimension(200, 250));
        console = new JTextArea(15, 15);

        vertical = new JScrollPane(console);
        vertical.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(vertical);
    }
}