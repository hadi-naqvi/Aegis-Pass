package view;

import interface_adapter.Authentication.AuthenticationState;
import interface_adapter.LogOut.LogOutController;
import interface_adapter.ScanItem.ScanItemController;
import interface_adapter.ScanItem.ScanItemState;
import interface_adapter.ScanItem.ScanItemViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ScanItemView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "scan item";
    private final ScanItemViewModel scanItemViewModel;
    private final ScanItemController scanItemController;
    private final LogOutController logOutController;
    private JPanel leftPanel;
    private JPanel buttonPanel;
    private JButton generatePasswordButton;
    private JButton generateEmailButton;
    private JButton haveibeenpwnedButton;
    private JButton scanFileURLButton;
    private JButton signOutButton;
    private JLabel logo;
    JPanel scanRightPanel;
    private JPanel topBar;
    private JButton createButton;
    private JButton editViewButton;
    private JButton deleteButton;
    private JButton copyUButton;
    private JButton copyPButton;
    private JButton copyUPButton;
    private JButton autotypeUAndPButton;
    private JButton autotypePButton;
    private JRadioButton scanFileRadioButton;
    private JRadioButton scanURLRadioButton;
    private JButton selectFileButton;
    private JTextField typeURLTextField;
    private JPanel main;
    private JButton confirmButton;
    private JPanel scanPanel;

    public ScanItemView(ScanItemViewModel scanItemViewModel, ScanItemController scanItemController, LogOutController logOutController){
        this.scanItemViewModel = scanItemViewModel;
        this.scanItemController = scanItemController;
        this.logOutController = logOutController;
        this.scanItemViewModel.addPropertyChangeListener(this);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(scanFileRadioButton);
        buttonGroup.add(scanURLRadioButton);

        selectFileButton.setEnabled(false);
        typeURLTextField.setEnabled(false);
        confirmButton.setEnabled(false);

        scanFileRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectFileButton.setEnabled(true);
                typeURLTextField.setEnabled(false);
                confirmButton.setEnabled(false);
            }
        });

        scanURLRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectFileButton.setEnabled(false);
                typeURLTextField.setEnabled(true);
                confirmButton.setEnabled(true);
            }
        });

        selectFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(ScanItemView.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    String selectedFile = fileChooser.getSelectedFile().getAbsolutePath();
                    // Use the selected file as needed
                    scanItemController.scanFile(selectedFile);
                }
            }
        });

        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Removes the passwords in the UI/view model when logging out to prevent this sensitive data leaking
                ScanItemState state = scanItemViewModel.getState();
                // state.setAccounts(null); not sure if this is needed will add again if so
                scanItemViewModel.setState(state);
                scanItemViewModel.firePropertyChanged();

                // Logs the user out
                logOutController.execute();
            }
        });

        confirmButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(confirmButton)) {
                            ScanItemState currentState = scanItemViewModel.getState();

                            scanItemController.scanUrl(currentState.getUrl());
                        }
                    }
                }
        );

        typeURLTextField.getDocument().addDocumentListener(new DocumentListener() {
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
                ScanItemState currentState = scanItemViewModel.getState();
                currentState.setUrl(typeURLTextField.getText());
                scanItemViewModel.setState(currentState);
            }
        });

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
        if (state instanceof ScanItemState){
            ScanItemState scanItemState = (ScanItemState) evt.getNewValue();
            if (scanItemState.getError() == null) {
                JOptionPane.showMessageDialog(this, scanItemState.getResults());
            } else {
                JOptionPane.showMessageDialog(this, scanItemState.getError());
            }
        }
    }
}
