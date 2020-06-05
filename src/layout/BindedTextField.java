package layout;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BindedTextField implements DocumentListener {
    protected final Object model;
    protected final String fieldName;

    public BindedTextField(Object model, String fieldName) {
        this.model = model;

        // powiększa literę do setera
        String firstChar = String.valueOf(fieldName.charAt(0));
        if (firstChar.equals(firstChar.toLowerCase())) {
            fieldName = firstChar.toUpperCase()
                    + fieldName.substring(1);
        }

        this.fieldName = fieldName;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        dataUpdated(e);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        dataUpdated(e);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        dataUpdated(e);
    }

    protected void dataUpdated(DocumentEvent e) {
        try {
            String text = e.getDocument().getText(
                    e.getDocument().getStartPosition().getOffset(),
                    e.getDocument().getEndPosition().getOffset() - 1);

            Method method = model.getClass().getDeclaredMethod("set" + fieldName, String.class);
            method.invoke(model, text);
        } catch (BadLocationException | NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException | IllegalArgumentException e1) {
            e1.printStackTrace();
        }
    }
}
