package view;

import interface_adapter.CreateAccount.CreateAccountState;
import interface_adapter.Dashboard.DashboardViewModel;
import interface_adapter.GeneratePassword.GeneratePasswordController;
import interface_adapter.GeneratePassword.GeneratePasswordState;
import interface_adapter.GeneratePassword.GeneratePasswordViewModel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GeneratePasswordView extends JPanel implements ActionListener, PropertyChangeListener {
    private JPanel generatePasswordPanel;
    private JPanel topPanel;
    private JTextField passwordField;
    private JProgressBar passwordQuality;
    private JPanel middlePanel;
    private JSlider lengthSlider;
    private JButton regenerateButton;
    private JButton copyButton;
    private JPanel charsetSelection;
    private JButton aZButton;
    private JButton a09Button;
    private JButton lowerazButton;
    private JButton extendedASCIIButton;
    private JButton punctOneButton;
    private JButton punctTwoButton;
    private JButton punctThreeButton;
    private JButton punctFourButton;
    private JButton punctFiveButton;
    private JButton closeButton;
    private JPanel extraParamsPanel;
    private JTextField includeFrom;
    private JTextField excludeFrom;
    private JPanel bottomPanel;
    private JLabel charTypes;
    private JPanel sliderPanel;
    private JLabel lengthLabel;
    private JPanel containerPanel;
    private JPanel buttonPanel;
    private JLabel entropyLabel;
    private JPanel qualityPanel;
    private DashboardViewModel dashboardViewModel;
    private GeneratePasswordViewModel generatePasswordViewModel;
    private GeneratePasswordController generatePasswordController;

    public GeneratePasswordView(DashboardViewModel dashboardViewModel,
                                GeneratePasswordViewModel generatePasswordViewModel,
                                GeneratePasswordController generatePasswordController) {
        this.dashboardViewModel = dashboardViewModel;
        this.generatePasswordViewModel = generatePasswordViewModel;
        this.generatePasswordController = generatePasswordController;
        this.generatePasswordViewModel.addPropertyChangeListener(this);

        this.regenerateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GeneratePasswordState state = generatePasswordViewModel.getState();
                generatePasswordController.execute(state.getPasswordQuality(), state.getPasswordLength(),
                        state.isLowerAlpha(), state.isUpperAlpha(), state.isNumericalChars(), state.isExtendedAscii(),
                        state.isPunctuationOne(), state.isPunctuationTwo(), state.isPunctuationThree(),
                        state.isPunctuationFour(), state.isPunctuationFive(), state.getAlsoIncludeFrom(),
                        state.getExcludeFrom());
            }
        });

        this.copyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                GeneratePasswordState state = generatePasswordViewModel.getState();
                StringSelection stringSelection = new StringSelection(state.getPasswordField());
                clipboard.setContents(stringSelection, null);
            }
        });

        this.lengthSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                GeneratePasswordState state = generatePasswordViewModel.getState();
                state.setPasswordLength(lengthSlider.getValue());
                generatePasswordViewModel.setState(state);
            }
        });

        this.aZButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GeneratePasswordState state = generatePasswordViewModel.getState();
                state.setUpperAlpha(!state.isUpperAlpha());
                generatePasswordViewModel.setState(state);
            }
        });

        this.lowerazButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GeneratePasswordState state = generatePasswordViewModel.getState();
                state.setLowerAlpha(!state.isLowerAlpha());
                generatePasswordViewModel.setState(state);
            }
        });

        this.a09Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GeneratePasswordState state = generatePasswordViewModel.getState();
                state.setNumericalChars(!state.isNumericalChars());
                generatePasswordViewModel.setState(state);
            }
        });

        this.extendedASCIIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GeneratePasswordState state = generatePasswordViewModel.getState();
                state.setUpperAlpha(!state.isUpperAlpha());
                generatePasswordViewModel.setState(state);
            }
        });

        this.punctOneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GeneratePasswordState state = generatePasswordViewModel.getState();
                state.setPunctuationOne(!state.isPunctuationOne());
                generatePasswordViewModel.setState(state);
            }
        });

        this.punctTwoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GeneratePasswordState state = generatePasswordViewModel.getState();
                state.setPunctuationTwo(!state.isPunctuationTwo());
                generatePasswordViewModel.setState(state);
            }
        });

        this.punctThreeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GeneratePasswordState state = generatePasswordViewModel.getState();
                state.setPunctuationThree(!state.isPunctuationThree());
                generatePasswordViewModel.setState(state);
            }
        });

        this.punctFourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GeneratePasswordState state = generatePasswordViewModel.getState();
                state.setPunctuationFour(!state.isPunctuationFour());
                generatePasswordViewModel.setState(state);
            }
        });

        this.punctFiveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GeneratePasswordState state = generatePasswordViewModel.getState();
                state.setPunctuationFive(!state.isPunctuationFive());
                generatePasswordViewModel.setState(state);
            }
        });

        this.includeFrom.getDocument().addDocumentListener(new DocumentListener() {
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

            }
            private void updateState() {
                GeneratePasswordState currentState = generatePasswordViewModel.getState();
                currentState.setAlsoIncludeFrom(includeFrom.getText());
                generatePasswordViewModel.setState(currentState);
            }
        });

        this.excludeFrom.getDocument().addDocumentListener(new DocumentListener() {
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

            }
            private void updateState() {
                GeneratePasswordState currentState = generatePasswordViewModel.getState();
                currentState.setExcludeFrom(excludeFrom.getText());
                generatePasswordViewModel.setState(currentState);
            }
        });

        this.closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dashboardViewModel.firePropertyChanged();
            }
        });

        this.setLayout(new GridLayout());
        this.add(generatePasswordPanel);
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
            GeneratePasswordState generatePasswordState = (GeneratePasswordState) evt.getNewValue();
            if (generatePasswordState.getGeneratePasswordError() != null) {
                JOptionPane.showMessageDialog(this, generatePasswordState.getGeneratePasswordError());
            }
            else {
                passwordField.setText(((CreateAccountState) state).getPassword());
            }
        }
    }
}
