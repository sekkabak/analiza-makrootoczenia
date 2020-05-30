package layout;

import app.Config;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Bottom extends JPanel {
    App app;

    JProgressBar progressBar;
    JButton prev;
    JButton next;
    public Bottom(App app) {
        this.app = app;

        this.setPreferredSize(new Dimension(400, 50));
        this.setBackground(Config.color3);
        this.setBorder(new EmptyBorder(5,0,5,0));

        prev = new JButton("POWRÓT");
        prev.setFocusPainted(false);
        prev.setPreferredSize(new Dimension(100,30));
        prev.setBackground(Config.color1);
        prev.setForeground(Config.color3);
        prev.setFont(Config.font);
        prev.addActionListener(e -> app.prevPage());
        this.add(prev);

        progressBar = new JProgressBar(0, 10);
        progressBar.setPreferredSize(new Dimension(315,30));
        this.add(progressBar);

        next = new JButton("DALEJ");
        next.setFocusPainted(false);
        next.setPreferredSize(new Dimension(100,30));
        next.setBackground(Config.color1);
        next.setForeground(Config.color3);
        next.setFont(Config.font);
        next.addActionListener(e -> app.nextPage());
        this.add(next);
    }

    public void adjustProgressBar() {
        prev.setEnabled(app.progress != 0);
        next.setEnabled(app.progress != app.progressMax);

        progressBar.setValue(app.progress);
        progressBar.setMaximum(app.progressMax);
    }
}