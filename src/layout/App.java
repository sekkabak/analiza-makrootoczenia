package layout;

import app.Config;
import model.Area;
import model.DataManager;
import model.Factor;
import window.FillFactor;
import window.ChoosingAreas;
import window.Scenario;
import window.Welcome;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class App {
    public DataManager dataManager;

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

        windows.add(new Scenario(this));
        windows.add(new Welcome(this));

        progressMax = windows.size() - 1;
    }

    private void calculateFactorWindows() {
        windows.removeIf(w -> w instanceof FillFactor);

        int windowI = windowIndex;
        for (Area area : dataManager.areas) {
            for (int i = 0; i < area.factors.size(); i++, windowI++) {
                windows.add(windowI, new FillFactor(this, area, area.factors.get(i)));
            }
        }
        progressMax = windows.size() - 1;
    }

    public void addFactorPage() {
        Area currentArea = dataManager.getCurrentArea();
        Factor newFactor = new Factor("Nowy czynnik");
        currentArea.factors.add(newFactor);

        windows.add(windowIndex + 1, new FillFactor(this, currentArea, newFactor));
        progressMax = windows.size() - 1;
        bottom.adjustProgressBar();
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

        // tworzy ekrany dla czynników po wybraniu sfer
        if (windowIndex > 0 && windows.get(windowIndex - 1) instanceof ChoosingAreas)
            calculateFactorWindows();

        bottom.adjustProgressBar();
        center.displayWindow(windows.get(windowIndex));
    }
}
