package view;

import interface_adapter.CheckBreach.CheckBreachController;
import interface_adapter.CheckBreach.CheckBreachState;
import interface_adapter.CheckBreach.CheckBreachViewModel;
import interface_adapter.Dashboard.DashboardViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class CheckBreachView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "check breach";
    private final CheckBreachViewModel checkBreachViewModel;
    private final CheckBreachController checkBreachController;
    private final DashboardViewModel dashboardViewModel;
    private JPanel main;
    JPanel breachRightPanel;
    private JPanel breachPanel;
    private JRadioButton checkEmailRadioButton;
    private JRadioButton checkPasswordRadioButton;
    private JTextField typePasswordTextField;
    private JButton confirmPasswordButton;
    private JTextField typeEmailTextField;
    private JButton confirmEmailButton;
    private JButton backButton;

    /**
     * Constructor method for the Check Breach view
     * @param checkBreachViewModel the View Model for the Check Breach view
     * @param checkBreachController the Controller for the Check Breach use case
     * @param dashboardViewModel the View Model for the Dashboard View
     */
    public CheckBreachView(CheckBreachViewModel checkBreachViewModel, CheckBreachController checkBreachController,
                           DashboardViewModel dashboardViewModel) {
        this.checkBreachViewModel = checkBreachViewModel;
        this.checkBreachController = checkBreachController;
        this.dashboardViewModel = dashboardViewModel;
        this.checkBreachViewModel.addPropertyChangeListener(this);

        // setting up the radio buttons
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(checkEmailRadioButton);
        buttonGroup.add(checkPasswordRadioButton);

        // disabling the fields and confirm buttons by default (until a radio button is pressed)
        typeEmailTextField.setEnabled(false);
        confirmEmailButton.setEnabled(false);
        typePasswordTextField.setEnabled(false);
        confirmPasswordButton.setEnabled(false);

        // listener for the check email radio button
        checkEmailRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                typeEmailTextField.setEnabled(true);
                confirmEmailButton.setEnabled(true);
                typePasswordTextField.setEnabled(false);
                confirmPasswordButton.setEnabled(false);
            }
        });

        // listener for the check password radio button
        checkPasswordRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                typeEmailTextField.setEnabled(false);
                confirmEmailButton.setEnabled(false);
                typePasswordTextField.setEnabled(true);
                confirmPasswordButton.setEnabled(true);
            }
        });

        // listener for the email text field
        typeEmailTextField.getDocument().addDocumentListener(new DocumentListener() {
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
                // Plaint text components don't fire these events
            }

            private void updateState() {
                CheckBreachState currentState = checkBreachViewModel.getState();
                currentState.setEmail(typeEmailTextField.getText());
                checkBreachViewModel.setState(currentState);
            }
        });

        // listener for the email confirm button
        confirmEmailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(confirmEmailButton)) {
                    CheckBreachState currentState = checkBreachViewModel.getState();
                    try {
                        checkBreachController.checkEmail(currentState.getEmail());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        // listener for the password text field
        typePasswordTextField.getDocument().addDocumentListener(new DocumentListener() {
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
                // Plaint text components don't fire these events
            }

            private void updateState() {
                CheckBreachState currentState = checkBreachViewModel.getState();
                currentState.setPassword(typePasswordTextField.getText());
                checkBreachViewModel.setState(currentState);
            }
        });

        // listener for the password confirm button
        confirmPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(confirmPasswordButton)) {
                    CheckBreachState currentState = checkBreachViewModel.getState();
                    try {
                        checkBreachController.checkPassword(currentState.getPassword());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        // listener for the back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBreachController.switchView();
            }
        });

        this.setLayout(new GridLayout());
        this.add(main);
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
        if (state instanceof CheckBreachState) {
            CheckBreachState checkBreachState = (CheckBreachState) evt.getNewValue();
            if (checkBreachState.getError() == null) {
                JOptionPane.showMessageDialog(this, checkBreachState.getResults());
            } else {
                JOptionPane.showMessageDialog(this, checkBreachState.getError());
            }
        }
    }

}
