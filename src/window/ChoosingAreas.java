package window;

import layout.App;
import layout.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class ChoosingAreas extends Window {
    JPanel pToTakeFrom = new JPanel();
    JPanel pToPutInside = new JPanel();
    JButton badd = new JButton("+");
    JTextField jfieldForSphere= new JTextField();
    ArrayList<String> listToTakeFrom = new ArrayList<>();
    ArrayList<String> listToPut = new ArrayList<>();
    ArrayList<String> listToBin = new ArrayList<>();

    public ChoosingAreas(App app) {
        super(app);

        // TODO trzeba to zrobić chociaż trochę responsywnie
//        setPreferredSize(new Dimension(400, 394));
        setBorder(BorderFactory.createLineBorder(Color.black, 3));
        setBackground(Color.lightGray);
        setLayout(null);
        listToTakeFrom.add("Pierwszy");
        listToTakeFrom.add("Drugi");
        listToTakeFrom.add("Trzeci");
        listToTakeFrom.add("Czwarty");
        pToTakeFrom.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        pToTakeFrom.setLayout(new GridLayout(4, 1));
        pToTakeFrom.setBounds(60, 100, 120, 130);
        pToPutInside.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        pToPutInside.setLayout(new GridLayout(4, 1));
        pToPutInside.setBounds(220, 100, 120, 130);
        badd.setBounds(300, 300, 50, 30);
        badd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!jfieldForSphere.getText().equals("")) {
                    listToBin.add(jfieldForSphere.getText());
                    listToPut.add(jfieldForSphere.getText());
                }
                jfieldForSphere.setText("");
                redo(pToPutInside, pToTakeFrom, listToPut);
                System.out.println(listToBin);
            }
        });
        jfieldForSphere.setBounds(200, 300, 90, 30);
        add(jfieldForSphere);
        add(badd);
        add(pToTakeFrom);
        add(pToPutInside);
        addToJpanel(pToTakeFrom, pToPutInside);
        setVisible(false);
    }

    public void redo (JPanel panel, JPanel panel2,ArrayList<String> list){
        panel.setLayout(new GridLayout(list.size(), 1));
        panel.removeAll();
        for (String s : list) {
            JLabel jLabel = new JLabel(s, SwingConstants.CENTER);
            jLabel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
            jLabel.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(checkIfAdd(listToBin, jLabel.getText())){
                        listToTakeFrom.add(jLabel.getText());
                    }
                    listToPut.remove(jLabel.getText());
                    panel.remove(jLabel);
                    GridLayout gr = new GridLayout(list.size(), 1);
                    panel.setLayout(gr);
                    panel.validate();
                    panel.repaint();
                    System.out.println(listToTakeFrom);
                    System.out.println(listToPut);
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
    }
    public void addToJpanel(JPanel panel, JPanel panel2) {
        panel.setLayout(new GridLayout(listToTakeFrom.size(), 1));
        panel.removeAll();
        for(int i = 0; i < listToTakeFrom.size(); i++) {
            JLabel jLabel = new JLabel(listToTakeFrom.get(i), SwingConstants.CENTER);
            jLabel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
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


    @Override
    public void display() {
        // TODO przenieść wszystkie ustawienia danych do tej metody
        // dane należy pobierać z app.dataManager
    }


}


