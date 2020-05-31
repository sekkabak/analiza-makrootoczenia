package layout;

import app.Config;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AreasTable {
    public AreasTable() {
        JFrame frame = new JFrame("JTable Test Display");

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JTable table = new JTable(new CustomTableModel());
        table.setRowHeight(50);
        table.setFont(Config.font);
        table.setTableHeader(null);

        CustomTableModel model = (CustomTableModel) table.getModel();
        model.addColumn("Areas");

        for(int i = 0; i< 100; i++)
            model.addRow(new Object[]{"coś"+i});

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                // do some actions here, for example
                // print first column value from selected row
                System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());
            }
        });

        JScrollPane tableContainer = new JScrollPane(table);

        panel.add(tableContainer, BorderLayout.CENTER);
        frame.getContentPane().add(panel);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new AreasTable();
    }
}
