package app;

import layout.BindedTextField;

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

    public static boolean validateInfluenceValue(String value) {
        for (String x : Config.influenceValidList) {
            if(x.equals(value))
                return true;
        }

        return false;
    }

    public static boolean validateProbabilityValue(double value) {
        return value <= 1.0 && value >= 0;
    }
}
