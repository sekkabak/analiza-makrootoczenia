package window;

import app.Config;
import layout.App;
import layout.AreasTable;
import layout.Window;
import model.Area;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;


public class ChoosingAreas extends Window {
    public ArrayList<Area> disabledAreas = new ArrayList<>();
    public ArrayList<Area> enabledAreas = new ArrayList<>();
    public ArrayList<Area> areasToThrow = new ArrayList<>();

    AreasTable disabledAreaTable;
    AreasTable enabledAreaTable = new AreasTable(enabledAreas, this);

    JButton buttonForAdd = new JButton("+");
    JTextField textFieldForAddingSpheres = new JTextField();
    ArrayList<String> listToTakeFrom = new ArrayList<>();



    public ChoosingAreas(App app) {
        super(app);
        setBackground(Config.color2);
        // TODO trzeba to zrobić chociaż trochę responsywnie
        setBorder(new EmptyBorder(10, 0, 10, 0));
        setLayout(new GridLayout());

        //TODO rzeczy do ustawienia: disabledAreas, enabledAreas, buttonForAdd, textFieldForAddingSpheres

        initArea();
        disabledAreaTable = new AreasTable(disabledAreas, this);

        add(disabledAreaTable);
        add(enabledAreaTable);

        buttonForAdd.addActionListener(e -> {
            if(!textFieldForAddingSpheres.getText().equals("")) {
                areasToThrow.add(new Area(textFieldForAddingSpheres.getText()));
                enabledAreas.add(new Area(textFieldForAddingSpheres.getText()));
            }
            textFieldForAddingSpheres.setText("");
            app.dataManager.areas = enabledAreas;
            this.remove(enabledAreaTable); this.remove(disabledAreaTable);
            enabledAreaTable = new AreasTable(enabledAreas, this);
            disabledAreaTable = new AreasTable(disabledAreas, this);
            this.add(disabledAreaTable); this.add(enabledAreaTable);
            this.validate();
        });

        add(buttonForAdd);
        add(textFieldForAddingSpheres);
        
    }

    public void change(String s) {
        // TODO
        boolean ifInEnabledTable = false;
        int PositionInEnabledTable = 0;
        boolean ifInDisabledTable = false;
        int PositionInDisabledTable = 0;
        boolean ifToTrash = false;
        for(Area a: disabledAreas){
            if(a.name.equals(s)){
                ifInDisabledTable = true;
                break;
            }
            PositionInDisabledTable++;
        }
        for(Area a: enabledAreas){
            if(a.name.equals(s)){
                ifInEnabledTable = true;
                break;
            }
            PositionInEnabledTable++;
        }
        for(Area a: areasToThrow){
            if(a.name.equals(s)){
                ifToTrash = true;
            }
        }
        if(ifInDisabledTable){
            if(!ifToTrash) {
                enabledAreas.add(disabledAreas.get(PositionInDisabledTable));
            }
            disabledAreas.remove(PositionInDisabledTable);
        }
        if(ifInEnabledTable){
            if(!ifToTrash) {
                disabledAreas.add(enabledAreas.get(PositionInEnabledTable));
            }
            enabledAreas.remove(PositionInEnabledTable);
        }
        app.dataManager.areas = enabledAreas;
        this.remove(enabledAreaTable); this.remove(disabledAreaTable);
        enabledAreaTable = new AreasTable(enabledAreas, this);
        disabledAreaTable = new AreasTable(disabledAreas, this);
        this.add(disabledAreaTable); this.add(enabledAreaTable);
        this.validate();
    }

    public void initArea() {
        Collections.addAll(listToTakeFrom, Config.initialAreas);
        for (String s : listToTakeFrom) {
            disabledAreas.add(new Area(s));
        }
    }

    @Override
    public void display() {
        enabledAreas = app.dataManager.areas;
    }
}