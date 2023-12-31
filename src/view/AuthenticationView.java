package view;

import interface_adapter.Authentication.AuthenticationController;
import interface_adapter.Authentication.AuthenticationState;
import interface_adapter.Authentication.AuthenticationViewModel;
import interface_adapter.SetupAuth.SetupAuthState;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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
    private JButton btnGoSignup;

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
                            tfUsername.setText("");
                            pfPassword.setText("");
                        }
                    }
                }
        );

        btnGoSignup.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(btnGoSignup)) {
                            authenticationController.switchViews();
                        }
                    }
                }
        );

        tfUsername.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Plain text components don't fire these events
            }

            private void updateState() {
                AuthenticationState currentState = authenticationViewModel.getState();
                currentState.setUsername(tfUsername.getText());
                authenticationViewModel.setState(currentState);
            }
        });

        pfPassword.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Plain text components don't fire these events
            }

            private void updateState() {
                AuthenticationState currentState = authenticationViewModel.getState();
                currentState.setPassword(pfPassword.getText());
                authenticationViewModel.setState(currentState);
            }
        });

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
            else {
                this.tfUsername.setText(((AuthenticationState) state).getUsername());
                this.pfPassword.setText(((AuthenticationState) state).getPassword());
            }
        }
    }

}
