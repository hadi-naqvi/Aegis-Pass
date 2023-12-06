package view;

import entity.AccountInfo;
import interface_adapter.Authentication.AuthenticationState;
import interface_adapter.CreateAccount.CreateAccountController;
import interface_adapter.CreateAccount.CreateAccountState;
import interface_adapter.CreateAccount.CreateAccountViewModel;
import interface_adapter.Dashboard.DashboardController;
import interface_adapter.Dashboard.DashboardState;
import interface_adapter.Dashboard.DashboardViewModel;
import interface_adapter.DeleteAccount.DeleteAccountController;
import interface_adapter.GeneratePassword.GeneratePasswordController;
import interface_adapter.GeneratePassword.GeneratePasswordState;
import interface_adapter.GeneratePassword.GeneratePasswordViewModel;
import interface_adapter.LogOut.LogOutController;
import interface_adapter.UpdateAccount.UpdateAccountController;
import interface_adapter.UpdateAccount.UpdateAccountState;
import interface_adapter.UpdateAccount.UpdateAccountViewModel;
import interface_adapter.ScanItem.ScanItemController;
import interface_adapter.ScanItem.ScanItemViewModel;
import view.ScanItemView;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DashboardView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "display dash";
    private final DashboardController dashboardController;
    private final LogOutController logOutController;
    private final DeleteAccountController deleteAccountController;
    private final DashboardViewModel dashboardViewModel;
    private JButton mainView;
    private DefaultTableModel accountsTableModel;
    private JTable accounts;
    private JPanel main;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel buttonPanel;
    private JLabel logo;
    private JButton generatePasswordButton;
    private JButton generateEmailButton;
    private JButton haveibeenpwnedButton;
    private JButton scanFileURLButton;
    private JButton signOutButton;
    private JTable table;
    private JScrollBar scrollbar;
    private JPanel topBar;
    private JPanel cardPanel;
    private JButton createButton;
    private JButton editViewButton;
    private JButton deleteButton;
    private JButton copyUButton;
    private JButton copyPButton;
    private JButton copyUPButton;
    private JButton autotypeUAndPButton;
    private JButton autotypePButton;
    private JLabel Title;
    private JLabel Username;
    private JLabel Password;
    private JLabel URL;
    private JLabel Notes;
    private JLabel Date;
    private JPanel createAccountPanel;
    private GeneratePasswordView generatePasswordPanel;
    private UpdateAccountView updateAccountPanel;
    private JPanel scanItemPanel;
    private JScrollPane tableScrollPane;

    public DashboardView(DashboardViewModel dashboardViewModel,
                         DashboardController dashboardController, LogOutController logOutController,
                         ScanItemController scanItemController, ScanItemViewModel scanItemViewModel,
                         CreateAccountController createAccountController, CreateAccountViewModel createAccountViewModel,
                         UpdateAccountController updateAccountController, UpdateAccountViewModel updateAccountViewModel,
                         DeleteAccountController deleteAccountController, DeleteAccountViewModel deleteAccountViewModel,
                         GeneratePasswordController generatePasswordController, GeneratePasswordViewModel generatePasswordViewModel) {
        this.dashboardViewModel = dashboardViewModel;
        this.dashboardController = dashboardController;
        this.logOutController = logOutController;
        this.deleteAccountController = deleteAccountController;
        this.scanItemPanel = new ScanItemView(scanItemViewModel, scanItemController, dashboardViewModel);
        this.createAccountPanel = new CreateAccountView(dashboardViewModel, createAccountViewModel, createAccountController);
        this.generatePasswordPanel = new GeneratePasswordView(dashboardViewModel, generatePasswordViewModel, generatePasswordController);
        this.updateAccountPanel = new UpdateAccountView(dashboardViewModel, updateAccountViewModel, updateAccountController);
        this.dashboardViewModel.addPropertyChangeListener(this);


        this.accountsTableModel = new DefaultTableModel();
        this.accountsTableModel.setColumnIdentifiers(new Object[]{"Icon", "Title", "Username", "URL", "Notes", "Date"});
        table.setModel(this.accountsTableModel);
        this.tableScrollPane = new JScrollPane(table);

        this.rightPanel.add(this.tableScrollPane, BorderLayout.CENTER);

        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Removes the passwords in the UI/view model when logging out to prevent this sensitive data leaking
                DashboardState state = dashboardViewModel.getState();
                state.setAccounts(null);
                dashboardViewModel.setState(state);
                dashboardViewModel.firePropertyChanged();

                // Logs the user out
                logOutController.execute();
            }
        });

        scanFileURLButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.remove(rightPanel);
                main.add(scanItemPanel, BorderLayout.CENTER);
                updateView();
                setRightPanelName("scan item");
            }
        });

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.remove(rightPanel);
                main.add(createAccountPanel, BorderLayout.CENTER);
                updateView();
                setRightPanelName("create account");
            }
        });

        generatePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.remove(rightPanel);
                main.add(generatePasswordPanel, BorderLayout.CENTER);
                updateView();
                setRightPanelName("generate password");
                generatePasswordPanel.lengthSlider.setValue(16);
                GeneratePasswordState generatePasswordState = generatePasswordViewModel.getState();
                generatePasswordController.execute(generatePasswordState.getPasswordQuality(), generatePasswordState.getPasswordLength(),
                        generatePasswordState.isLowerAlpha(), generatePasswordState.isUpperAlpha(), generatePasswordState.isNumericalChars(), generatePasswordState.isExtendedAscii(),
                        generatePasswordState.isPunctuationOne(), generatePasswordState.isPunctuationTwo(), generatePasswordState.isPunctuationThree(),
                        generatePasswordState.isPunctuationFour(), generatePasswordState.isPunctuationFive(), generatePasswordState.getAlsoIncludeFrom(),
                        generatePasswordState.getExcludeFrom());
            }
        });

        editViewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowIndex = table.getSelectedRow();
                if (rowIndex == -1){
                    updateAccountNoAccount();
                }
                UpdateAccountState state = updateAccountViewModel.getState();
                state.setOriginalTitle(dashboardViewModel.getState().getAccounts().get(rowIndex).getTitle());
                state.setOriginalUser(dashboardViewModel.getState().getAccounts().get(rowIndex).getUsername());
                state.setUsernameError(null);
                updateAccountViewModel.setState(state);
                main.remove(rightPanel);
                main.add(updateAccountPanel, BorderLayout.CENTER);
                updateView();
                setRightPanelName("update account");

                updateAccountPanel.setTitleText(dashboardViewModel.getState().getAccounts().get(rowIndex).getTitle());
                updateAccountPanel.setUsernameText(dashboardViewModel.getState().getAccounts().get(rowIndex).getUsername());
                updateAccountPanel.setPasswordText(dashboardViewModel.getState().getAccounts().get(rowIndex).getPassword());
                updateAccountPanel.set2FAKeyText(dashboardViewModel.getState().getAccounts().get(rowIndex).getSecretKey());
                updateAccountPanel.setURLText(dashboardViewModel.getState().getAccounts().get(rowIndex).getURL());
                updateAccountPanel.setIconURLText(dashboardViewModel.getState().getAccounts().get(rowIndex).getIconURL());
                updateAccountPanel.setNotesText(dashboardViewModel.getState().getAccounts().get(rowIndex).getNotes());
            }
        });

        this.setLayout(new GridLayout());
        this.add(main);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Gets most recent
                int rowIndex = table.getSelectedRow();
                if (rowIndex == -1){
                    deleteAccountNoAccount();
                } else {
                    String titleToDelete = (String) table.getValueAt(rowIndex, 1);
                    String usernameToDelete = (String) table.getValueAt(rowIndex, 2);
                    deleteAccountController.execute(titleToDelete, usernameToDelete);
                }
            }
        });
    }

    private void deleteAccountNoAccount() {
        JOptionPane.showMessageDialog(this, "no account selected");
    }

    private void updateAccountNoAccount(){
        JOptionPane.showMessageDialog(this, "no account selected");
    }

    private void setRightPanelName(String name){
        DashboardState dashboardState = this.dashboardViewModel.getState();
        dashboardState.setRightPanelView(name);
        dashboardViewModel.setState(dashboardState);
    }

    private void updateView(){
        this.validate();
        this.repaint();
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
        if (state instanceof DashboardState) {
            DashboardState dashboardState = (DashboardState) evt.getNewValue();

            if( dashboardState.getRightPanelView()!=null) {
                if (dashboardState.getRightPanelView().equals("dashboard")) {
                    main.remove(main.getComponent(1));
                    main.add(rightPanel, BorderLayout.CENTER);
                    updateView();
                }

            }
            while (accountsTableModel.getRowCount() > 0) {
                accountsTableModel.removeRow(0);
            }

            this.dashboardController.execute();

            for (AccountInfo account : dashboardState.getAccounts()) {
                accountsTableModel.addRow(new Object[]{account.getIconURL(), account.getTitle(), account.getUsername(),
                        account.getURL(), account.getNotes(), account.getDate()});
            }

        }

    }


}
