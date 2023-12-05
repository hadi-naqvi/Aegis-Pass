package view;

import entity.AccountInfo;
import interface_adapter.Authentication.AuthenticationState;
import interface_adapter.CreateAccount.CreateAccountController;
import interface_adapter.CreateAccount.CreateAccountState;
import interface_adapter.CreateAccount.CreateAccountViewModel;
import interface_adapter.Dashboard.DashboardState;
import interface_adapter.Dashboard.DashboardViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLOutput;
import java.time.LocalDateTime;

public class CreateAccountView extends JPanel implements ActionListener, PropertyChangeListener{

    public final String viewName = "create account";
    private final CreateAccountController createAccountController;
    private final DashboardViewModel dashboardViewModel;
    private final CreateAccountViewModel createAccountViewModel;
    private JTextField inputTitle;
    private JTextField inputUsername;
    private JTextField inputPassword;
    private JTextField inputKey;
    private JTextField inputURL;
    private JTextField inputIcon;
    private JTextField inputNotes;
    private JPanel main;
    private JButton cancelButton;
    private JButton createButton;

    public CreateAccountView(DashboardViewModel dashboardViewModel, CreateAccountViewModel createAccountViewModel,
                             CreateAccountController createAccountController) {
        this.createAccountController = createAccountController;
        this.createAccountViewModel = createAccountViewModel;
        this.dashboardViewModel = dashboardViewModel;
        this.createAccountViewModel.addPropertyChangeListener(this);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(createButton)) {
                    CreateAccountState state = createAccountViewModel.getState();
                    createAccountController.execute(
                            state.getTitle(), state.getUsername(), state.getPassword(), state.getSecretKey(),
                            state.getURL(), state.getIconURL(), LocalDateTime.now(), state.getNotes());
                    inputTitle.setText("");
                    inputUsername.setText("");
                    inputPassword.setText("");
                    inputKey.setText("");
                    inputURL.setText("");
                    inputIcon.setText("");
                    inputNotes.setText("");

                    DashboardState state2 = dashboardViewModel.getState();
                    state2.setAccounts(null);
                    dashboardViewModel.setState(state2);
                    dashboardViewModel.firePropertyChanged();
                }

            }
        });



        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAccountController.switchView();

            }
        });

        inputTitle.getDocument().addDocumentListener(new DocumentListener() {
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
                CreateAccountState currentState = createAccountViewModel.getState();
                currentState.setTitle(inputTitle.getText());
                createAccountViewModel.setState(currentState);
            }
        });

        inputUsername.getDocument().addDocumentListener(new DocumentListener() {
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
                CreateAccountState currentState = createAccountViewModel.getState();
                currentState.setUsername(inputUsername.getText());
                createAccountViewModel.setState(currentState);
            }
        });

        inputPassword.getDocument().addDocumentListener(new DocumentListener() {
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
                CreateAccountState currentState = createAccountViewModel.getState();
                currentState.setPassword(inputPassword.getText());
                createAccountViewModel.setState(currentState);
            }
        });

        inputKey.getDocument().addDocumentListener(new DocumentListener() {
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
                CreateAccountState currentState = createAccountViewModel.getState();
                currentState.setSecretKey(inputKey.getText());
                createAccountViewModel.setState(currentState);
            }
        });

        inputURL.getDocument().addDocumentListener(new DocumentListener() {
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
                CreateAccountState currentState = createAccountViewModel.getState();
                currentState.setURL(inputURL.getText());
                createAccountViewModel.setState(currentState);
            }
        });

        inputIcon.getDocument().addDocumentListener(new DocumentListener() {
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
                CreateAccountState currentState = createAccountViewModel.getState();
                currentState.setIconURL(inputIcon.getText());
                createAccountViewModel.setState(currentState);
            }
        });

        inputNotes.getDocument().addDocumentListener(new DocumentListener() {
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
                CreateAccountState currentState = createAccountViewModel.getState();
                currentState.setNotes(inputNotes.getText());
                createAccountViewModel.setState(currentState);
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
        if (state instanceof CreateAccountState) {
            CreateAccountState createAccountState = (CreateAccountState) evt.getNewValue();
            if (createAccountState.getUsernameError() != null) {
                JOptionPane.showMessageDialog(this, createAccountState.getUsernameError());
            }
        }
    }
}
