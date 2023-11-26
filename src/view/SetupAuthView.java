package view;

import interface_adapter.SetupAuth.SetupAuthController;
import interface_adapter.SetupAuth.SetupAuthState;
import interface_adapter.SetupAuth.SetupAuthViewModel;

import javax.swing.*;
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
    private JButton confirmButton;
    private JPanel main;
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
        confirmButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(confirmButton)) {
                            SetupAuthState currentState = setupAuthViewModel.getState();
                            setupAuthController.execute(
                                    currentState.getUsername(),
                                    currentState.getPassword(),
                                    currentState.getRepeatedPassword()
                            );
                        }
                    }
                }
        );


        usernameInputField.addKeyListener(
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
                        SetupAuthState currentState = setupAuthViewModel.getState();
                        currentState.setUsername(usernameInputField.getText());
                        setupAuthViewModel.setState(currentState);
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
                     * Invoked when a key has been pressed. Empty for now.
                     *
                     * @param e the event to be processed
                     */
                    @Override
                    public void keyPressed(KeyEvent e) {
                        SetupAuthState currentState = setupAuthViewModel.getState();
                        currentState.setPassword(passwordInputField.getText());
                        setupAuthViewModel.setState(currentState);
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

        repeatPasswordInputField.addKeyListener(
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
                     * Invoked when a key has been pressed. Empty for now.
                     *
                     * @param e the event to be processed
                     */
                    @Override
                    public void keyPressed(KeyEvent e) {
                        SetupAuthState currentState = setupAuthViewModel.getState();
                        currentState.setRepeatedPassword(repeatPasswordInputField.getText());
                        setupAuthViewModel.setState(currentState);
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
