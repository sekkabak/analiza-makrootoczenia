import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class choosingSpheres extends JPanel {

    JPanel pToTakeFrom = new JPanel();
    JPanel pToPutInside = new JPanel();
    ArrayList<String> listToTakeFrom = new ArrayList<>();
    ArrayList<String> listToPut = new ArrayList<>();

    public choosingSpheres() {
        setPreferredSize(new Dimension(400, 394));
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
                    listToTakeFrom.add(jLabel.getText());
                    listToPut.remove(jLabel.getText());
                    panel.remove(jLabel);
                    GridLayout gr = new GridLayout(list.size(), 1);
                    panel.setLayout(gr);
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



}
