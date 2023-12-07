package view;

import interface_adapter.Dashboard.DashboardViewModel;
import interface_adapter.GenerateEmail.GenerateEmailController;
import interface_adapter.GenerateEmail.GenerateEmailState;
import interface_adapter.GenerateEmail.GenerateEmailViewModel;
import interface_adapter.ScanItem.ScanItemState;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GenerateEmail extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "generate email";
    private final GenerateEmailViewModel generateEmailViewModel;
    private final GenerateEmailController generateEmailController;
    private final DashboardViewModel dashboardViewModel;
    private JPanel genEmailRightPanel;
    private JPanel genEmailPanel;
    private JButton backButton;
    private JButton generateEmailButton;
    private JTextField emailTextfield;
    private JPanel main;
    private JTextField passTextField;
    private JButton copyButton;

    public GenerateEmail(GenerateEmailViewModel generateEmailViewModel, GenerateEmailController generateEmailController,
                         DashboardViewModel dashboardViewModel) {
        this.generateEmailViewModel = generateEmailViewModel;
        this.generateEmailController = generateEmailController;
        this.dashboardViewModel = dashboardViewModel;
        this.generateEmailViewModel.addPropertyChangeListener(this);


        generateEmailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(generateEmailController)) {
                    GenerateEmailState currentState = generateEmailViewModel.getState();
                    generateEmailController.execute(currentState.getAccountName(), currentState.getPassName());
                }
            }
        });

        emailTextfield.getDocument().addDocumentListener(new DocumentListener() {
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
                GenerateEmailState currentState = generateEmailViewModel.getState();
                currentState.setAccountName(emailTextfield.getText());
                generateEmailViewModel.setState(currentState);
            }
        });

        emailTextfield.getDocument().addDocumentListener(new DocumentListener() {
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
                GenerateEmailState currentState = generateEmailViewModel.getState();
                currentState.setAccountName(emailTextfield.getText());
                generateEmailViewModel.setState(currentState);
            }
        });

        passTextField.getDocument().addDocumentListener(new DocumentListener() {
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
                GenerateEmailState currentState = generateEmailViewModel.getState();
                currentState.setPassName(passTextField.getText());
                generateEmailViewModel.setState(currentState);
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

    }
}
