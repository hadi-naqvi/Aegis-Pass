package view;

import interface_adapter.SetupAuth.SetupAuthController;
import interface_adapter.SetupAuth.SetupAuthState;
import interface_adapter.SetupAuth.SetupAuthViewModel;

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

public class SetupAuthView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "setup auth";
    private final SetupAuthViewModel setupAuthViewModel;
    private JTextField usernameInputField;
    private JPasswordField passwordInputField;
    private JPasswordField repeatPasswordInputField;
    private JButton btnConfirm;
    private JPanel main;
    private JButton btnGoLogin;
    private final SetupAuthController setupAuthController;

    /**
     * The Constructor for the View. Sets up event listeners and adds JSwing elements to the View.
     * @param viewModel the view model for the view
     * @param controller the controller for the view
     */
    public SetupAuthView(SetupAuthViewModel viewModel, SetupAuthController controller) {
        this.setupAuthViewModel = viewModel;
        this.setupAuthController = controller;
        this.setupAuthViewModel.addPropertyChangeListener(this);

        btnConfirm.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(btnConfirm)) {
                            SetupAuthState currentState = setupAuthViewModel.getState();
                            setupAuthController.execute(
                                    currentState.getUsername(),
                                    currentState.getPassword(),
                                    currentState.getRepeatedPassword()
                            );
                            usernameInputField.setText("");
                            passwordInputField.setText("");
                            repeatPasswordInputField.setText("");
                        }
                    }
                }
        );

        btnGoLogin.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(btnGoLogin)) {
                            setupAuthController.switchViews();
                        }
                    }
                }
        );

        usernameInputField.getDocument().addDocumentListener(new DocumentListener() {
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
                SetupAuthState currentState = setupAuthViewModel.getState();
                currentState.setUsername(usernameInputField.getText());
                setupAuthViewModel.setState(currentState);
            }
        });

        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {
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
                SetupAuthState currentState = setupAuthViewModel.getState();
                currentState.setPassword(passwordInputField.getText());
                setupAuthViewModel.setState(currentState);
            }
        });

        repeatPasswordInputField.getDocument().addDocumentListener(new DocumentListener() {
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
                SetupAuthState currentState = setupAuthViewModel.getState();
                currentState.setRepeatedPassword(repeatPasswordInputField.getText());
                setupAuthViewModel.setState(currentState);
            }
        });

        this.setLayout(new GridLayout());
        this.add(main);
    }

    /**
     * Invoked when an action occurs. Does nothing right now.
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
        if (state instanceof SetupAuthState) {
            SetupAuthState setupAuthState = (SetupAuthState) evt.getNewValue();
            if (setupAuthState.getPasswordError() != null) {
                JOptionPane.showMessageDialog(this, setupAuthState.getPasswordError());
            }
        }
    }
}
