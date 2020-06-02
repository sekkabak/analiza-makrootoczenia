package layout;

import app.Config;
import model.*;
import raport.Raport;
import window.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class App {
    public boolean auto_fill = true;
    public DataManager dataManager;
    public boolean areasChanged = false;

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

        windows.add(new Plot(this));

        windows.add(new Output(this));

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

    private void calculateScenariosWindows() {
        windows.removeIf(w -> w instanceof Scenario);

        int windowI = getLastFillFactorWindowIndex();
        if (windowI == -1)
            return;

        dataManager.clearScenarios();

        // optymistyczny
        for (Area area : dataManager.areas) {
            FirstTwoScenarios scenario = new FirstTwoScenarios(area);
            dataManager.optimisticScenario.add(scenario);
            windows.add(windowI, new Scenario(this, "optymistyczny", area, 1, scenario));
            windowI++;
        }

        // pesymistyczny
        for (Area area : dataManager.areas) {
            FirstTwoScenarios scenario = new FirstTwoScenarios(area);
            dataManager.pesimisticScenario.add(scenario);
            windows.add(windowI, new Scenario(this, "pesymistyczny", area, 1, scenario));
            windowI++;
        }

        // najbardziej prawdopodobny
        for (Area area : dataManager.areas) {
            SecondTwoScenarios scenario = new SecondTwoScenarios(area);
            dataManager.mostLikelyScenario.add(scenario);
            windows.add(windowI, new Scenario(this, "najbardziej prawdopodobny", area, 2, scenario));
            windowI++;
        }

        // niespodziankowy
        for (Area area : dataManager.areas) {
            SecondTwoScenarios scenario = new SecondTwoScenarios(area);
            dataManager.unexpectedScenario.add(scenario);
            windows.add(windowI, new Scenario(this, "niespodziankowy", area, 2, scenario));
            windowI++;
        }

        progressMax = windows.size() - 1;
    }

    private int getLastFillFactorWindowIndex() {
        int index = 0;
        boolean begin = false;
        for (Window w : windows) {
            if (!begin && w instanceof FillFactor) {
                begin = true;
            } else if (begin && !(w instanceof FillFactor)) {
                return index;
            }

            index++;
        }

        return -1;
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
        if (areasChanged && windowIndex > 0 && windows.get(windowIndex - 1) instanceof ChoosingAreas) {
            calculateFactorWindows();
            calculateScenariosWindows();
        } else if(windowIndex > 0 && windows.get(windowIndex - 1) instanceof Plot) {
            Raport.savePlotToImage(windows.get(windowIndex - 1));
        }

        bottom.adjustProgressBar();
        center.displayWindow(windows.get(windowIndex));

        areasChanged = false;
    }
}
