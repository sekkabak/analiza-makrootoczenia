package window;

import layout.App;
import layout.Window;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;

import app.Config;
import model.Area;

public class ChoosingAreas extends Window {
    JPanel disabledAreas = new JPanel();
    JPanel enabledAreas = new JPanel();

    JButton addNewArea = new JButton("+");
    JTextField newAreaName = new JTextField();

    ArrayList<Area> disabledAreasList = new ArrayList<>();
    ArrayList<Area> enabledAreasList = new ArrayList<>();

    ArrayList<Area> areasBin = new ArrayList<>();

    public ChoosingAreas(App app) {
        super(app);

        setBackground(Config.color2);
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new GridLayout(2, 2, 5, 5));

        disabledAreas.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        disabledAreas.setLayout(new GridLayout(4, 1));
        disabledAreas.setPreferredSize(new Dimension(200, 400));

        enabledAreas.setPreferredSize(new Dimension(200, 400));
        enabledAreas.setBorder(new EmptyBorder(0, 0, 0, 10));
        enabledAreas.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        enabledAreas.setLayout(new GridLayout(4, 1));

        initList();
        badd.addActionListener(e -> {
            if(!jfieldForSphere.getText().equals("")) {
                listToBin.add(jfieldForSphere.getText());
                listToPut.add(jfieldForSphere.getText());
                areasBin.add(area);
                enabledAreasList.add(area);
            }
            newAreaName.setText("");
            redo(enabledAreas, disabledAreas, disabledAreasList);
        });

        add(disabledAreas);
        add(enabledAreas);
        add(newAreaName);
        add(addNewArea);

        addToJpanel(disabledAreas, enabledAreas);
        setVisible(false);
    }

    /**
     * Metoda rysująca całe jPanele na nowo
     */
    public void redo(JPanel panel, JPanel panel2, ArrayList<Area> list) {
        panel.setLayout(new GridLayout(list.size(), 1));
        panel.removeAll();
        for (Area x : list) {
            JLabel jLabel = new JLabel(x.name, SwingConstants.CENTER);
            jLabel.setFont(Config.font);
            jLabel.setBackground(Config.color3);
            jLabel.setOpaque(true);
            jLabel.setForeground(Config.color1);
            jLabel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
            jLabel.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (checkIfAdd(areasBin, jLabel.getText())) {
                        disabledAreasList.add(new Area(jLabel.getText()));
                    }
                    enabledAreasList.removeIf(x -> x.name.equals(jLabel.getText()));
                    panel.remove(jLabel);
                    panel.setLayout(new GridLayout(list.size(), 1));
                    panel.revalidate();
                    panel.repaint();
                    addToJpanel(panel2, panel);
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
            });
            panel.add(jLabel);
        }
        panel.revalidate();
    }

    public void addToJpanel(JPanel panel, JPanel panel2) {
        panel.setLayout(new GridLayout(enabledAreasList.size(), 1));
        panel.removeAll();
        for (int i = 0; i < disabledAreasList.size(); i++) {
            JLabel jLabel = new JLabel(disabledAreasList.get(i).name, SwingConstants.CENTER);
            jLabel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
            jLabel.setFont(Config.font);
            jLabel.setBackground(Config.color3);
            jLabel.setOpaque(true);
            jLabel.setForeground(Config.color1);
            panel.add(jLabel);
            jLabel.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    enabledAreasList.add(new Area(jLabel.getText()));
                    disabledAreasList.removeIf(x -> x.name.equals(jLabel.getText()));
                    panel.remove(jLabel);
                    GridLayout gr = new GridLayout(disabledAreasList.size(), 1);
                    panel.setLayout(gr);
                    panel.validate();
                    panel.repaint();
                    redo(panel2, panel, enabledAreasList);
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
            });
        }
        panel.validate();

        // TODO tutaj jest zmiana danych ...
        app.dataManager.areas = new ArrayList<>(this.enabledAreasList);
    }

    public void addToDisabledAreas() {

    }

    public void addToEnabledAreas() {

    }

    public boolean checkIfAdd(ArrayList<Area> areas, String name) {
        if (areas.size() != 0) {
            for (Area x : areas) {
                if (name.equals(x.name))
                    return false;
            }
        }
        return true;
    }

    public void initList() {
        for (String x : Config.initialAreas) {
            disabledAreasList.add(new Area(x));
        }
    }

    @Override
    public void display() {
        // TODO przenieść wszystkie ustawienia danych do tej metody
        // dane należy pobierać z app.dataManager
    }
}


