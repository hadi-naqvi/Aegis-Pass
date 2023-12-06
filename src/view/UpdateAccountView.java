package view;

import interface_adapter.CreateAccount.CreateAccountState;
import interface_adapter.Dashboard.DashboardState;
import interface_adapter.Dashboard.DashboardViewModel;
import interface_adapter.UpdateAccount.UpdateAccountController;
import interface_adapter.UpdateAccount.UpdateAccountPresenter;
import interface_adapter.UpdateAccount.UpdateAccountState;
import interface_adapter.UpdateAccount.UpdateAccountViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;

public class UpdateAccountView extends JPanel implements ActionListener, PropertyChangeListener {


    public final String viewName = "update account";
    private final UpdateAccountController updateAccountController;
    private final DashboardViewModel dashboardViewModel;
    private final UpdateAccountViewModel updateAccountViewModel;
    private JTextField inputTitle;
    private JTextField inputUsername;
    private JTextField inputPassword;
    private JTextField inputKey;
    private JTextField inputURL;
    private JTextField inputIcon;
    private JTextField inputNotes;
    private JButton updateButton;
    private JButton cancelButton;
    private JPanel main;

    public UpdateAccountView(DashboardViewModel dashboardViewModel, UpdateAccountViewModel updateAccountViewModel,
                             UpdateAccountController updateAccountController){
        this.updateAccountController = updateAccountController;
        this.updateAccountViewModel = updateAccountViewModel;
        this.dashboardViewModel = dashboardViewModel;
        this.updateAccountViewModel.addPropertyChangeListener(this);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(updateButton)) {
                    UpdateAccountState state = updateAccountViewModel.getState();

                    updateAccountController.execute( state.getOriginalTitle(), state.getOriginalUser(),
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
                updateAccountController.switchView();

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
                UpdateAccountState currentState = updateAccountViewModel.getState();
                currentState.setTitle(inputTitle.getText());
                updateAccountViewModel.setState(currentState);
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
                UpdateAccountState currentState = updateAccountViewModel.getState();
                currentState.setUsername(inputUsername.getText());
                updateAccountViewModel.setState(currentState);
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
                UpdateAccountState currentState = updateAccountViewModel.getState();
                currentState.setPassword(inputPassword.getText());
                updateAccountViewModel.setState(currentState);
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
                UpdateAccountState currentState = updateAccountViewModel.getState();
                currentState.setSecretKey(inputKey.getText());
                updateAccountViewModel.setState(currentState);
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
                UpdateAccountState currentState = updateAccountViewModel.getState();
                currentState.setURL(inputURL.getText());
                updateAccountViewModel.setState(currentState);
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
                UpdateAccountState currentState = updateAccountViewModel.getState();
                currentState.setIconURL(inputIcon.getText());
                updateAccountViewModel.setState(currentState);
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
                UpdateAccountState currentState = updateAccountViewModel.getState();
                currentState.setNotes(inputNotes.getText());
                updateAccountViewModel.setState(currentState);
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
        if (state instanceof UpdateAccountState) {
            UpdateAccountState updateAccountState = (UpdateAccountState) evt.getNewValue();
            if (updateAccountState.getUsernameError() != null) {
                JOptionPane.showMessageDialog(this, updateAccountState.getUsernameError());
            }
        }
    }
}
