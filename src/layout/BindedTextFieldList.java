package layout;

import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BindedTextFieldList extends BindedTextField {
    int index;

    public BindedTextFieldList(Object model, String fieldName, int index) {
        super(model, fieldName);
        this.index = index;
    }

    private void dataUpdated(DocumentEvent e) {
//        try {
//            String text = e.getDocument().getText(
//                    e.getDocument().getStartPosition().getOffset(),
//                    e.getDocument().getEndPosition().getOffset() - 1);
//
//            Method method = model.getClass().getDeclaredMethod(
//                    "set" + fieldName, String.class);
//            method.invoke(model, text, i);
//        } catch (BadLocationException | NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException | IllegalArgumentException e1) {
//            e1.printStackTrace();
//        }
    }
}
