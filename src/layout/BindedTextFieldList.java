package layout;

import model.SIPair;

import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BindedTextFieldList extends BindedTextField {
    int index;
    AvgRefresh refresh;

    public BindedTextFieldList(Object model, String fieldName, int index, AvgRefresh refresh) {
        super(model, fieldName);
        this.index = index;
        this.refresh = refresh;
    }

    @Override
    protected void dataUpdated(DocumentEvent e) {
        try {
            String text = e.getDocument().getText(
                    e.getDocument().getStartPosition().getOffset(),
                    e.getDocument().getEndPosition().getOffset() - 1);

            Method method = model.getClass().getDeclaredMethod("set" + fieldName, SIPair.class);
            method.invoke(model, new SIPair(text, index));
            refresh.refresh();
        } catch (BadLocationException | NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException | IllegalArgumentException e1) {
            e1.printStackTrace();
        }
    }
}
