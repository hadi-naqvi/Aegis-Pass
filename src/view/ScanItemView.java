package view;

import interface_adapter.Dashboard.DashboardController;
import interface_adapter.Dashboard.DashboardState;
import interface_adapter.Dashboard.DashboardViewModel;
import interface_adapter.LogOut.LogOutController;
import interface_adapter.ScanItem.ScanItemController;
import interface_adapter.ScanItem.ScanItemState;
import interface_adapter.ScanItem.ScanItemViewModel;

import javax.swing.*;
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

        scanFileRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectFileButton.setEnabled(true);
                typeURLTextField.setEnabled(false);
            }
        });

        scanURLRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectFileButton.setEnabled(false);
                typeURLTextField.setEnabled(true);
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
                    JOptionPane.showMessageDialog(ScanItemView.this, "File selected: " + selectedFile);
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
