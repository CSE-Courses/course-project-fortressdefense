package gui.CreateGame;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import code.ServerModel;

/**
 * Listener for Text Fields for Create Game form
 * @author https://stackoverflow.com/questions/12337986/binding-of-jtext-fields-value-to-info-class
 *
 */
public class BindingListener implements DocumentListener {
    private ServerModel model;
    private String fieldName;

    public BindingListener(ServerModel model, String fieldName) {
        this.model = model;

        String firstChar = String.valueOf(fieldName.charAt(0));
        if (firstChar.equals(firstChar.toLowerCase())) {
            fieldName = firstChar.toUpperCase()
                    + fieldName.substring(1, fieldName.length());
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

    private void dataUpdated(DocumentEvent e) {
        try {
            String text = e.getDocument().getText(
                    e.getDocument().getStartPosition().getOffset(),
                    e.getDocument().getEndPosition().getOffset() - 1);
            
            /* TEST 
            System.out.println("Entered text: " + text);
            System.out.println("Field to be updated: " + fieldName);

            System.out.println("Old model values: " + model); */

            Method method = model.getClass().getDeclaredMethod(
                    "Set" + fieldName, String.class);
            method.invoke(model, text);

            //Testing: System.out.println("New model values: " + model);
        } catch (BadLocationException e1) {
            e1.printStackTrace();
        } catch (NoSuchMethodException e1) {
            e1.printStackTrace();
        } catch (SecurityException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        } catch (IllegalArgumentException e1) {
            e1.printStackTrace();
        } catch (InvocationTargetException e1) {
            e1.printStackTrace();
        }
    }
}