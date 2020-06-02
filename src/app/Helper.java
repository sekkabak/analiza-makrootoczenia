package app;

import layout.AvgRefresh;
import layout.BindedTextField;
import layout.BindedTextFieldList;

import javax.swing.*;
import java.awt.*;

public class Helper {
    public static JLabel createLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setBackground(Config.color3);
        label.setForeground(Config.color4);
        label.setOpaque(true);
        label.setBorder(Config.border);
        label.setFont(Config.font);
        return label;
    }

    public static JTextField createField(String value) {
        JTextField text = new JTextField(value, SwingConstants.CENTER);
        text.setPreferredSize(new Dimension(150, 50));
        text.setBorder(Config.border);
        text.setOpaque(true);
        text.setFont(Config.font);
        text.setBackground(Config.color3);
        text.setForeground(Config.color1);
        text.setHorizontalAlignment(JTextField.CENTER);

        return text;
    }

    public static JTextField createBindedField(String value, Object model, String fieldName) {
        JTextField text = Helper.createField(value);
        text.getDocument().addDocumentListener(new BindedTextField(model, fieldName));

        return text;
    }

    public static JTextField createBindedFieldList(String value, Object model, String fieldName, int index, AvgRefresh refresh) {
        JTextField text = Helper.createField(value);
        text.getDocument().addDocumentListener(new BindedTextFieldList(model, fieldName, index, refresh));

        return text;
    }

    public static String getValidAverageForm(double avg) {
        String res = String.valueOf(avg);

        // dodanie + jeśli jest dodatnia
        if(res.charAt(0) != '-' && res.charAt(0) != '+' && avg != 0.0) {
            res = "+" + res;
        }

        // wyliczenie miejsca kroki i dodanie 3 znaków
        int i = 0;
        for (i = 0; i < res.length(); i++) {
            if(res.charAt(i) == '.') {
                i += 3;
                break;
            }
        }

        // obcięcie reszty znaków po 2 po kropce
        if(i < res.length())
            res = res.substring(0, i);

        return res;
    }
}
