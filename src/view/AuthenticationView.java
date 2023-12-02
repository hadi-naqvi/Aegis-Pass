package view;

import interface_adapter.Authentication.AuthenticationController;
import interface_adapter.Authentication.AuthenticationState;
import interface_adapter.Authentication.AuthenticationViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AuthenticationView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "authentication";
    private final AuthenticationController authenticationController;
    private final AuthenticationViewModel authenticationViewModel;

    public final JPasswordField passwordInputField = new JPasswordField(30);

    public final JButton confirmButton;

    /**
     * The Constructor for the View. Sets up event listeners and adds JSwing elements to the View.
     * @param viewModel
     * @param controller
     */
    public AuthenticationView(AuthenticationViewModel viewModel, AuthenticationController controller) {
        this.authenticationViewModel = viewModel;
        this.authenticationController = controller;
        this.authenticationViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(AuthenticationViewModel.TITLE_LABEL);

        JPanel passwordInfo = new JPanel();
        passwordInfo.add(new JLabel(AuthenticationViewModel.PASSWORD_LABEL));
        passwordInfo.add(passwordInputField);

        confirmButton = new JButton(AuthenticationViewModel.CONFIRM_BUTTON_LABEL);

        confirmButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(confirmButton)) {
                            AuthenticationState currentState = authenticationViewModel.getState();
                            authenticationController.execute(
                                    currentState.getPassword()
                            );
                        }
                    }
                }
        );

        passwordInputField.addKeyListener(
                new KeyListener() {

                    /**
                     * Invoked when a key has been typed. Empty for now.
                     *
                     * @param e the event to be processed
                     */
                    @Override
                    public void keyTyped(KeyEvent e) {
                    }

                    /**
                     * Invoked when a key has been pressed. Updates text field and authenticationState.
                     *
                     * @param e the event to be processed
                     */
                    @Override
                    public void keyPressed(KeyEvent e) {
                        AuthenticationState currentState = authenticationViewModel.getState();
                        if (e.getKeyCode() == KeyEvent.VK_ESCAPE ||
                                e.getKeyCode() == KeyEvent.VK_ENTER) {
                            String newPassword = currentState.getPassword() + e.getKeyChar();
                            currentState.setPassword(newPassword.substring(0, newPassword.length() - 1));
                        }
                        else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && currentState.getPassword().length() != 0) {
                            String newPassword = currentState.getPassword().substring(0, currentState.getPassword().length() - 1);
                            currentState.setPassword(newPassword);
                        }
                        else if (!(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)) {
                            currentState.setPassword(currentState.getPassword() + e.getKeyChar());
                        }
                        System.out.println(e.getKeyCode());
                        authenticationViewModel.setState(currentState);
                    }

                    /**
                     * Invoked when a key has been released. Empty for now.
                     *
                     * @param e the event to be processed
                     */
                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(passwordInfo);
        this.add(confirmButton);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object state = evt.getNewValue();
        if (state instanceof AuthenticationState) {
            AuthenticationState authenticationState = (AuthenticationState) evt.getNewValue();
            if (authenticationState.getPasswordError() != null) {
                JOptionPane.showMessageDialog(this, authenticationState.getPasswordError());
            }
        }
    }

}
