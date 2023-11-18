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

    public final String viewName = "Create Aegis Pass Password";
    private final SetupAuthViewModel setupAuthViewModel;

    public final JPasswordField passwordInputField = new JPasswordField(30);

    public final JPasswordField repeatPasswordInputField = new JPasswordField(30);

    public final JButton create;
    private final SetupAuthController setupAuthController;

    /**
     * The Constructor for the View. Sets up event listeners and adds JSwing elements to the View.
     * @param viewModel the view model for the view
     * @param controller the controller for the view
     */
    public SetupAuthView(SetupAuthViewModel viewModel, SetupAuthController controller) {
        this.setupAuthController = controller;
        this.setupAuthViewModel = viewModel;
        this.setupAuthViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Aegis Pass Setup");

        JPanel passwordInfo = new JPanel();
        passwordInfo.add(new JLabel("Password:"));
        passwordInfo.add(passwordInputField);
        
        JPanel repeatPasswordInfo = new JPanel();
        passwordInfo.add(new JLabel("Confirm Password:"));
        passwordInfo.add(repeatPasswordInputField);

        create = new JButton(setupAuthViewModel.CREATE_BUTTON_LABEL);

        create.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(create)) {
                            SetupAuthState currentState = setupAuthViewModel.getState();

                            setupAuthController.execute(
                                    currentState.getPassword(),
                                    currentState.getRepeatPassword()
                            );
                        }
                    }
                }
        );

        passwordInputField.addKeyListener(
                new KeyListener() {

                    /**
                     * Invoked when a key has been typed. Updates text field.
                     *
                     * @param e the event to be processed
                     */
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SetupAuthState currentState = setupAuthViewModel.getState();
                        currentState.setPassword(passwordInputField.getText() + e.getKeyChar());
                        setupAuthViewModel.getState(currentState);
                    }

                    /**
                     * Invoked when a key has been pressed. Empty for now.
                     *
                     * @param e the event to be processed
                     */
                    @Override
                    public void keyPressed(KeyEvent e) {
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
                     * Invoked when a key has been typed. Updates text field.
                     *
                     * @param e the event to be processed
                     */
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SetupAuthState currentState = setupAuthViewModel.getState();
                        currentState.setRepeatPassword(repeatPasswordInputField.getText() + e.getKeyChar());
                        setupAuthViewModel.getState(currentState);
                    }

                    /**
                     * Invoked when a key has been pressed. Empty for now.
                     *
                     * @param e the event to be processed
                     */
                    @Override
                    public void keyPressed(KeyEvent e) {
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
        this.add(repeatPasswordInfo);
        this.add(create);
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
