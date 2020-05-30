package layout;

import app.Config;
import model.Area;
import model.DataManager;
import window.AddFactors;
import window.ChoosingAreas;
import window.Welcome;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class App {
    DataManager dataManager;

    JFrame frame;
    Top top;
    Bottom bottom;
    Left left;
    Right right;
    Center center;

    int progress = -1;
    int progressMax = -1;

    ArrayList<Window> windows;
    int windowIndex = -1;

    public App() {
        dataManager = new DataManager();

        // TODO DEBUG
        Area a1 = new Area("Sfera 1");
        Area a2 = new Area("Sfera 2");
        dataManager.areas.add(a1);
        dataManager.areas.add(a2);

        init();
        createWindows();
        nextPage();
    }

    private void init() {
        frame = new JFrame(Config.AppName);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(Config.initialState);
        frame.setMinimumSize(Config.initialState);
//        frame.setResizable(false);

        BorderLayout borderLayout = new BorderLayout();
        top = new Top(this);
        center = new Center(this);
        bottom = new Bottom(this);
        left = new Left();
        right = new Right();

        borderLayout.preferredLayoutSize(frame);
        frame.setLayout(borderLayout);

        frame.add(top);
        frame.add(bottom);
        frame.add(right);
        frame.add(left);
        frame.add(center);

        borderLayout.addLayoutComponent(top, BorderLayout.NORTH);
        borderLayout.addLayoutComponent(bottom, BorderLayout.SOUTH);
        borderLayout.addLayoutComponent(right, BorderLayout.EAST);
        borderLayout.addLayoutComponent(left, BorderLayout.WEST);
        borderLayout.addLayoutComponent(center, BorderLayout.CENTER);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void createWindows() {
        windows = new ArrayList<>();

        windows.add(new Welcome(this));
        windows.add(new ChoosingAreas(this));

        windows.add(new AddFactors(this, dataManager.currentArea));

        progressMax = windows.size() - 1;
    }

    public void prevPage() {
        progress--;
        windowIndex--;

        bottom.adjustProgressBar();

        center.displayWindow(windows.get(windowIndex));
    }

    public void nextPage() {
        progress++;
        windowIndex++;

        bottom.adjustProgressBar();

        center.displayWindow(windows.get(windowIndex));
    }
}
