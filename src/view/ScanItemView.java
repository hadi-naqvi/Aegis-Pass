package view;

import interface_adapter.Dashboard.DashboardViewModel;
import interface_adapter.ScanItem.ScanItemController;
import interface_adapter.ScanItem.ScanItemState;
import interface_adapter.ScanItem.ScanItemViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class ScanItemView extends JPanel implements ActionListener, PropertyChangeListener, ResettableView {
    public final String viewName = "scan item";
    private final ScanItemViewModel scanItemViewModel;
    private final ScanItemController scanItemController;
    private final DashboardViewModel dashboardViewModel;
    JPanel scanRightPanel;
    private JRadioButton scanFileRadioButton;
    private JRadioButton scanURLRadioButton;
    private JButton selectFileButton;
    private JTextField typeURLTextField;
    private JPanel main;
    private JButton confirmButton;
    private JPanel scanPanel;
    private JButton backButton;

    public ScanItemView(ScanItemViewModel scanItemViewModel,
                        ScanItemController scanItemController, DashboardViewModel dashboardViewModel){
        this.scanItemViewModel = scanItemViewModel;
        this.scanItemController = scanItemController;
        this.dashboardViewModel = dashboardViewModel;
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

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetView();
                scanItemController.switchView();

            }
        });

        confirmButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(confirmButton)) {
                            ScanItemState currentState = scanItemViewModel.getState();

                            try {
                                scanItemController.scanUrl(currentState.getUrl());
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            } catch (InterruptedException ex) {
                                throw new RuntimeException(ex);
                            }
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
        if (state instanceof ScanItemState){
            ScanItemState scanItemState = (ScanItemState) evt.getNewValue();
            if (scanItemState.getError() == null) {
                JOptionPane.showMessageDialog(this, scanItemState.getResults());
            } else {
                JOptionPane.showMessageDialog(this, scanItemState.getError());
            }
        }
    }

    /**
     * Method which resets the components and state of the view
     */
    @Override
    public void resetView() {
        this.selectFileButton.setEnabled(true);
        this.typeURLTextField.setText("");
        this.scanFileRadioButton.setSelected(true);
        this.scanURLRadioButton.setSelected(false);
    }
}
