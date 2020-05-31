package layout;

import app.Config;
import model.Area;
import window.ChoosingAreas;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class AreasTable extends JPanel {
    ChoosingAreas window;

    public AreasTable(ArrayList<Area> areas, ChoosingAreas window) {
        this.setLayout(new BorderLayout());
        this.window = window;
        JTable table = new JTable(new CustomTableModel());
        table.setRowHeight(50);
        table.setFont(Config.font);
        table.setTableHeader(null);

        CustomTableModel model = (CustomTableModel) table.getModel();
        model.addColumn("Areas");



        for (Area x : areas) {
            model.addRow(new Object[]{x.name});
        }

        table.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());
                // przerzucenie do czegoś
                this.window.change(table.getValueAt(table.getSelectedRow(), 0).toString());
            }
        });

        JScrollPane tableContainer = new JScrollPane(table);
        this.add(tableContainer, BorderLayout.CENTER);
    }
}
