package view;

import interface_adapter.SetupAuth.SetupAuthController;
import interface_adapter.SetupAuth.SetupAuthViewModel;
import org.junit.Test;
import use_case.SetupAuth.SetupAuthInputBoundary;

import javax.swing.*;
import java.awt.event.KeyEvent;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

public class SetupAuthViewTest {

    /**
     * Method that tests the SetupAuthView's field components match their counterparts in the SetupAuthViewModel
     */
    @Test
    public void testSetupAuthViewFields() {

        // create dummy SetupAuthView
        SetupAuthInputBoundary inputBoundary = null;
        SetupAuthController controller = new SetupAuthController(inputBoundary);
        SetupAuthViewModel viewModel = new SetupAuthViewModel();
        JPanel setupAuthView = new SetupAuthView(viewModel, controller);
        JFrame jf = new JFrame();
        jf.setContentPane(setupAuthView);
        jf.pack();
        jf.setVisible(true);

        // get references to all the fields and buttons in the SetupAuthView (get all components with trial and error, and print them to see what's what)
        JPanel panel = (JPanel) setupAuthView.getComponent(0);
//        JButton confirmButton = (JButton) panel.getComponent(0);
        JPasswordField repeatPasswordField = (JPasswordField) panel.getComponent(1);
        JPasswordField passwordField = (JPasswordField) panel.getComponent(2);
        JTextField usernameField = (JTextField) panel.getComponent(3);
//        JButton loginButton = (JButton) panel.getComponent(9);
        
        // send events typing out sample strings to each field, and test to see if the ViewModel matches
        String testPasswordUsernameString = "we A";
        keyEventFieldDispatch(testPasswordUsernameString, panel, usernameField);
        assertEquals(testPasswordUsernameString, usernameField.getText());
        assertEquals(testPasswordUsernameString, viewModel.getState().getUsername());

        String testPasswordFieldString = " ";
        keyEventFieldDispatch(testPasswordFieldString, panel, passwordField);
        assertEquals(testPasswordFieldString, new String(passwordField.getPassword()));
        assertEquals(testPasswordFieldString, viewModel.getState().getRepeatedPassword());

        String testRepeatPasswordFieldString = "sdfab a_";
        keyEventFieldDispatch(testRepeatPasswordFieldString, panel, repeatPasswordField);
        assertEquals(testRepeatPasswordFieldString, new String(repeatPasswordField.getPassword()));
        assertEquals(testRepeatPasswordFieldString, viewModel.getState().getPassword());

    }

    /**
     * Method to wait a set amount of milliseconds safely
     * @param milliseconds the amount of seconds to wait
     */
    public static void bufferMilliseconds(long milliseconds) {
        try {
            sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method to send key events spelling out a specified string to a specified field in a specified panel
     * @param keysTyped specifying what to simulate typing into the field
     * @param panel the panel that the field is in
     * @param field the field that you want to write to
     */
    public static void keyEventFieldDispatch(String keysTyped, JPanel panel, JTextField field) {

        for (char ch : keysTyped.toCharArray()) {
            bufferMilliseconds(100);

            // Create and dispatch event of current character being written to the field
            KeyEvent charEvent = new KeyEvent(field, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, ch);
            panel.dispatchEvent(charEvent);

            bufferMilliseconds(100);

            // Create and dispatch event of moving right a character, so the next character goes at the end of the field
            KeyEvent rightEvent = new KeyEvent(field, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);
            panel.dispatchEvent(rightEvent);
        }
        bufferMilliseconds(500);

    }

    /**
     * Method to send key events spelling out a specified string to a specified field in a specified panel
     * @param keysTyped specifying what to simulate typing into the field
     * @param panel the panel that the field is in
     * @param field the field that you want to write to
     */
    public static void keyEventFieldDispatch(String keysTyped, JPanel panel, JPasswordField field) {

        for (char ch : keysTyped.toCharArray()) {
            bufferMilliseconds(100);

            // Create and dispatch event of current character being written to the field
            KeyEvent charEvent = new KeyEvent(field, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, ch);
            panel.dispatchEvent(charEvent);

            bufferMilliseconds(100);

            // Create and dispatch event of moving right a character, so the next character goes at the end of the field
            KeyEvent rightEvent = new KeyEvent(field, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);
            panel.dispatchEvent(rightEvent);
        }
        bufferMilliseconds(500);

    }

}
