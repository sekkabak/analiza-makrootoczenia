package window;

import layout.App;
import layout.AreasTable;
import layout.Window;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

import app.Config;
import model.Area;
import model.DataManager;

public class ChoosingAreas extends Window {
    public ArrayList<Area> disabledAreas = new ArrayList<>();
    public ArrayList<Area> enabledAreas = new ArrayList<>();

    JPanel pToTakeFrom = new JPanel();
    JPanel pToPutInside = new JPanel();
    JButton badd = new JButton("+");
    JTextField jfieldForSphere= new JTextField();
    ArrayList<String> listToTakeFrom = new ArrayList<>();
    ArrayList<String> listToPut = new ArrayList<>();
    ArrayList<String> listToBin = new ArrayList<>();

    public ChoosingAreas(App app) {
        super(app);
        setBackground(Config.color2);
        // TODO trzeba to zrobić chociaż trochę responsywnie
        setBorder(new EmptyBorder(10, 0, 10, 0));
        setLayout(new GridLayout());
        pToTakeFrom.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        pToTakeFrom.setLayout(new GridLayout(4, 1));
        pToTakeFrom.setPreferredSize(new Dimension(120,130));

        pToPutInside.setPreferredSize(new Dimension(120, 130));
        pToPutInside.setBorder(new EmptyBorder(0,0,0,10));
//        pToTakeFrom.setBounds(60, 100, 120, 130);
        pToPutInside.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        pToPutInside.setLayout(new GridLayout(4, 1));
//        pToPutInside.setBounds(220, 100, 120, 130);
//        badd.setBounds(300, 300, 50, 30);
        initList();

        new AreasTable(this);

        // dodaje nową sfere
        badd.addActionListener(e -> {
            if(!jfieldForSphere.getText().equals("")) {
                listToBin.add(jfieldForSphere.getText());
                listToPut.add(jfieldForSphere.getText());
            }
            jfieldForSphere.setText("");
            redo(pToPutInside, pToTakeFrom, listToPut);
        });

//        jfieldForSphere.setBounds(200, 300, 90, 30);
        add(pToTakeFrom);
        add(pToPutInside);
        add(badd);
        add(jfieldForSphere);

//        borderLayout.addLayoutComponent(pToTakeFrom, BorderLayout.WEST);
//        borderLayout.addLayoutComponent(pToTakeFrom, BorderLayout.EAST);
//        borderLayout.addLayoutComponent(jfieldForSphere, BorderLayout.SOUTH);
//        borderLayout.addLayoutComponent(badd, BorderLayout.SOUTH);


        addToJpanel(pToTakeFrom, pToPutInside);
        setVisible(false);
    }

    public void redo (JPanel panel, JPanel panel2,ArrayList<String> list){
        panel.setLayout(new GridLayout(list.size(), 1));
        panel.removeAll();
        for (String s : list) {
            JLabel jLabel = new JLabel(s, SwingConstants.CENTER);
            jLabel.setFont(Config.font);
            jLabel.setBackground(Config.color3);
            jLabel.setOpaque(true);
            jLabel.setForeground(Config.color1);
            jLabel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
            jLabel.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(checkIfAdd(listToBin, jLabel.getText())){
                        listToTakeFrom.add(jLabel.getText());
                    }
                    listToPut.remove(jLabel.getText());
                    panel.remove(jLabel);
//                    GridLayout gr = new GridLayout(list.size(), 1);
//                    panel.setLayout(gr);
                    panel.validate();
                    panel.repaint();
                    addToJpanel(panel2, panel);
                }
                @Override
                public void mousePressed(MouseEvent e) { }
                @Override
                public void mouseReleased(MouseEvent e) { }
                @Override
                public void mouseEntered(MouseEvent e) { }
                @Override
                public void mouseExited(MouseEvent e) { }
            });
            panel.add(jLabel);
        }
        panel.validate();

        recalcData();
    }
    public void addToJpanel(JPanel panel, JPanel panel2) {
        panel.setLayout(new GridLayout(listToTakeFrom.size(), 1));
        panel.removeAll();
        for(int i = 0; i < listToTakeFrom.size(); i++) {
            JLabel jLabel = new JLabel(listToTakeFrom.get(i), SwingConstants.CENTER);
            jLabel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
            jLabel.setFont(Config.font);
            jLabel.setBackground(Config.color3);
            jLabel.setOpaque(true);
            jLabel.setForeground(Config.color1);
            panel.add(jLabel);
            jLabel.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    listToPut.add(jLabel.getText());
                    listToTakeFrom.remove(jLabel.getText());
                    panel.remove(jLabel);
                    GridLayout gr = new GridLayout(listToTakeFrom.size(), 1);
                    panel.setLayout(gr);
                    panel.validate();
                    panel.repaint();
                    redo(panel2, panel, listToPut);
                }
                @Override
                public void mousePressed(MouseEvent e) { }
                @Override
                public void mouseReleased(MouseEvent e) { }
                @Override
                public void mouseEntered(MouseEvent e) { }
                @Override
                public void mouseExited(MouseEvent e) { }
            });
        }
        panel.validate();

        recalcData();
    }

    public boolean checkIfAdd(ArrayList<String> s, String name){
        if(s.size() != 0) {
            for (String value : s) {
                if (name.equals(value))
                    return false;
            }
        }
        return true;
    }

    public void initList(){
        Collections.addAll(listToTakeFrom, Config.initialAreas);

//        for (String x : Config.initialAreas) {
//            allAreas.add(new Area(x));
//        }
//
//        DataManager a = app.dataManager;
    }

    // TODO XD!
    public void recalcData() {
//        enabledAreas.clear();
//
//        for(String s : listToPut) {
//            ArrayList<Area> res = (ArrayList<Area>) allAreas.stream()
//                    .filter(x -> x.name.equals(s))
//                    .collect(Collectors.toList());
//            if(res.size() > 0)
//                enabledAreas.add(res.get(0));
//        }
//
//        app.dataManager.areas = enabledAreas;
    }

    public void change() {
        // TODO
        app.dataManager.areas = enabledAreas;
    }

    @Override
    public void display() {
        // TODO przenieść wszystkie ustawienia danych do tej metody
        // dane należy pobierać z app.dataManager


    }
}