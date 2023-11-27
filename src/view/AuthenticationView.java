package view;

import interface_adapter.Authentication.AuthenticationController;
import interface_adapter.Authentication.AuthenticationState;
import interface_adapter.Authentication.AuthenticationViewModel;
import interface_adapter.SetupAuth.SetupAuthController;
import interface_adapter.SetupAuth.SetupAuthState;

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
    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JButton btnLogin;
    private JPanel mainAuthentication;

    /**
     * The Constructor for the View. Sets up event listeners and adds JSwing elements to the View.
     * @param viewModel
     * @param controller
     */
    public AuthenticationView(AuthenticationViewModel viewModel, AuthenticationController controller) {
        this.authenticationViewModel = viewModel;
        this.authenticationController = controller;
        this.authenticationViewModel.addPropertyChangeListener(this);

        btnLogin.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(btnLogin)) {
                            AuthenticationState currentState = authenticationViewModel.getState();
                            authenticationController.execute(
                                    currentState.getUsername(),
                                    currentState.getPassword()
                            );
                        }
                    }
                }
        );

        tfUsername.addKeyListener(
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
                     * Invoked when a key has been pressed. Updates textfield.
                     *
                     * @param e the event to be processed
                     */
                    @Override
                    public void keyPressed(KeyEvent e) {
                        AuthenticationState currentState = authenticationViewModel.getState();
                        currentState.setUsername(tfUsername.getText());
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

        pfPassword.addKeyListener(
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
                        currentState.setPassword(pfPassword.getText());
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

        this.setLayout(new GridLayout());

        this.add(mainAuthentication);
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
