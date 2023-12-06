package view;

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
    public JSlider lengthSlider;
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

        lengthSlider.setMinimum(1);
        lengthSlider.setMaximum(128);
        lengthSlider.setMajorTickSpacing(10);
        lengthSlider.setMinorTickSpacing(1);
        lengthSlider.setValue(16);
        aZButton.setBackground(new Color(0xA3BE8C));
        a09Button.setBackground(new Color(0xA3BE8C));
        lowerazButton.setBackground(new Color(0xA3BE8C));

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
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()) {
                    GeneratePasswordState state = generatePasswordViewModel.getState();
                    state.setPasswordLength(source.getValue());
                    lengthLabel.setText("Length: " + String.valueOf(state.getPasswordLength()));
                    generatePasswordViewModel.setState(state);
                    generatePasswordController.execute(state.getPasswordQuality(), state.getPasswordLength(),
                            state.isLowerAlpha(), state.isUpperAlpha(), state.isNumericalChars(), state.isExtendedAscii(),
                            state.isPunctuationOne(), state.isPunctuationTwo(), state.isPunctuationThree(),
                            state.isPunctuationFour(), state.isPunctuationFive(), state.getAlsoIncludeFrom(),
                            state.getExcludeFrom());
                }
            }
        });

        this.aZButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GeneratePasswordState state = generatePasswordViewModel.getState();
                state.setUpperAlpha(!state.isUpperAlpha());
                if (state.isUpperAlpha()) {
                    aZButton.setBackground(new Color(0xA3BE8C));
                }
                else {
                    aZButton.setBackground(new Color(0xD8DEE9));
                }
                generatePasswordViewModel.setState(state);
                generatePasswordController.execute(state.getPasswordQuality(), state.getPasswordLength(),
                        state.isLowerAlpha(), state.isUpperAlpha(), state.isNumericalChars(), state.isExtendedAscii(),
                        state.isPunctuationOne(), state.isPunctuationTwo(), state.isPunctuationThree(),
                        state.isPunctuationFour(), state.isPunctuationFive(), state.getAlsoIncludeFrom(),
                        state.getExcludeFrom());
            }
        });

        this.lowerazButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GeneratePasswordState state = generatePasswordViewModel.getState();
                state.setLowerAlpha(!state.isLowerAlpha());
                if (state.isLowerAlpha()) {
                    lowerazButton.setBackground(new Color(0xA3BE8C));
                }
                else {
                    lowerazButton.setBackground(new Color(0xD8DEE9));
                }
                generatePasswordViewModel.setState(state);
                generatePasswordController.execute(state.getPasswordQuality(), state.getPasswordLength(),
                        state.isLowerAlpha(), state.isUpperAlpha(), state.isNumericalChars(), state.isExtendedAscii(),
                        state.isPunctuationOne(), state.isPunctuationTwo(), state.isPunctuationThree(),
                        state.isPunctuationFour(), state.isPunctuationFive(), state.getAlsoIncludeFrom(),
                        state.getExcludeFrom());
            }
        });

        this.a09Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GeneratePasswordState state = generatePasswordViewModel.getState();
                state.setNumericalChars(!state.isNumericalChars());
                if (state.isNumericalChars()) {
                    a09Button.setBackground(new Color(0xA3BE8C));
                }
                else {
                    a09Button.setBackground(new Color(0xD8DEE9));
                }
                generatePasswordViewModel.setState(state);
                generatePasswordController.execute(state.getPasswordQuality(), state.getPasswordLength(),
                        state.isLowerAlpha(), state.isUpperAlpha(), state.isNumericalChars(), state.isExtendedAscii(),
                        state.isPunctuationOne(), state.isPunctuationTwo(), state.isPunctuationThree(),
                        state.isPunctuationFour(), state.isPunctuationFive(), state.getAlsoIncludeFrom(),
                        state.getExcludeFrom());
            }
        });

        this.extendedASCIIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GeneratePasswordState state = generatePasswordViewModel.getState();
                state.setExtendedAscii(!state.isExtendedAscii());
                if (state.isExtendedAscii()) {
                    extendedASCIIButton.setBackground(new Color(0xA3BE8C));
                }
                else {
                    extendedASCIIButton.setBackground(new Color(0xD8DEE9));
                }
                generatePasswordViewModel.setState(state);
                generatePasswordController.execute(state.getPasswordQuality(), state.getPasswordLength(),
                        state.isLowerAlpha(), state.isUpperAlpha(), state.isNumericalChars(), state.isExtendedAscii(),
                        state.isPunctuationOne(), state.isPunctuationTwo(), state.isPunctuationThree(),
                        state.isPunctuationFour(), state.isPunctuationFive(), state.getAlsoIncludeFrom(),
                        state.getExcludeFrom());
            }
        });

        this.punctOneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GeneratePasswordState state = generatePasswordViewModel.getState();
                state.setPunctuationOne(!state.isPunctuationOne());
                if (state.isPunctuationOne()) {
                    punctOneButton.setBackground(new Color(0xA3BE8C));
                }
                else {
                    punctOneButton.setBackground(new Color(0xD8DEE9));
                }
                generatePasswordViewModel.setState(state);
                generatePasswordController.execute(state.getPasswordQuality(), state.getPasswordLength(),
                        state.isLowerAlpha(), state.isUpperAlpha(), state.isNumericalChars(), state.isExtendedAscii(),
                        state.isPunctuationOne(), state.isPunctuationTwo(), state.isPunctuationThree(),
                        state.isPunctuationFour(), state.isPunctuationFive(), state.getAlsoIncludeFrom(),
                        state.getExcludeFrom());
            }
        });

        this.punctTwoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GeneratePasswordState state = generatePasswordViewModel.getState();
                state.setPunctuationTwo(!state.isPunctuationTwo());
                if (state.isPunctuationTwo()) {
                    punctTwoButton.setBackground(new Color(0xA3BE8C));
                }
                else {
                    punctTwoButton.setBackground(new Color(0xD8DEE9));
                }
                generatePasswordViewModel.setState(state);
                generatePasswordController.execute(state.getPasswordQuality(), state.getPasswordLength(),
                        state.isLowerAlpha(), state.isUpperAlpha(), state.isNumericalChars(), state.isExtendedAscii(),
                        state.isPunctuationOne(), state.isPunctuationTwo(), state.isPunctuationThree(),
                        state.isPunctuationFour(), state.isPunctuationFive(), state.getAlsoIncludeFrom(),
                        state.getExcludeFrom());
            }
        });

        this.punctThreeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GeneratePasswordState state = generatePasswordViewModel.getState();
                state.setPunctuationThree(!state.isPunctuationThree());
                if (state.isPunctuationThree()) {
                    punctThreeButton.setBackground(new Color(0xA3BE8C));
                }
                else {
                    punctThreeButton.setBackground(new Color(0xD8DEE9));
                }
                generatePasswordViewModel.setState(state);
                generatePasswordController.execute(state.getPasswordQuality(), state.getPasswordLength(),
                        state.isLowerAlpha(), state.isUpperAlpha(), state.isNumericalChars(), state.isExtendedAscii(),
                        state.isPunctuationOne(), state.isPunctuationTwo(), state.isPunctuationThree(),
                        state.isPunctuationFour(), state.isPunctuationFive(), state.getAlsoIncludeFrom(),
                        state.getExcludeFrom());
            }
        });

        this.punctFourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GeneratePasswordState state = generatePasswordViewModel.getState();
                state.setPunctuationFour(!state.isPunctuationFour());
                if (state.isPunctuationFour()) {
                    punctFourButton.setBackground(new Color(0xA3BE8C));
                }
                else {
                    punctFourButton.setBackground(new Color(0xD8DEE9));
                }
                generatePasswordViewModel.setState(state);
                generatePasswordController.execute(state.getPasswordQuality(), state.getPasswordLength(),
                        state.isLowerAlpha(), state.isUpperAlpha(), state.isNumericalChars(), state.isExtendedAscii(),
                        state.isPunctuationOne(), state.isPunctuationTwo(), state.isPunctuationThree(),
                        state.isPunctuationFour(), state.isPunctuationFive(), state.getAlsoIncludeFrom(),
                        state.getExcludeFrom());
            }
        });

        this.punctFiveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GeneratePasswordState state = generatePasswordViewModel.getState();
                state.setPunctuationFive(!state.isPunctuationFive());
                if (state.isPunctuationFive()) {
                    punctFiveButton.setBackground(new Color(0xA3BE8C));
                }
                else {
                    punctFiveButton.setBackground(new Color(0xD8DEE9));
                }
                generatePasswordViewModel.setState(state);
                generatePasswordController.execute(state.getPasswordQuality(), state.getPasswordLength(),
                        state.isLowerAlpha(), state.isUpperAlpha(), state.isNumericalChars(), state.isExtendedAscii(),
                        state.isPunctuationOne(), state.isPunctuationTwo(), state.isPunctuationThree(),
                        state.isPunctuationFour(), state.isPunctuationFive(), state.getAlsoIncludeFrom(),
                        state.getExcludeFrom());
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
                generatePasswordController.execute(currentState.getPasswordQuality(), currentState.getPasswordLength(),
                        currentState.isLowerAlpha(), currentState.isUpperAlpha(), currentState.isNumericalChars(), currentState.isExtendedAscii(),
                        currentState.isPunctuationOne(), currentState.isPunctuationTwo(), currentState.isPunctuationThree(),
                        currentState.isPunctuationFour(), currentState.isPunctuationFive(), currentState.getAlsoIncludeFrom(),
                        currentState.getExcludeFrom());
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
                generatePasswordController.execute(currentState.getPasswordQuality(), currentState.getPasswordLength(),
                        currentState.isLowerAlpha(), currentState.isUpperAlpha(), currentState.isNumericalChars(), currentState.isExtendedAscii(),
                        currentState.isPunctuationOne(), currentState.isPunctuationTwo(), currentState.isPunctuationThree(),
                        currentState.isPunctuationFour(), currentState.isPunctuationFive(), currentState.getAlsoIncludeFrom(),
                        currentState.getExcludeFrom());
            }
        });

        this.closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GeneratePasswordState state = generatePasswordViewModel.getState();

                // Resetting the UI/state upon exiting
                state.setPasswordField("");
                state.setPasswordLength(16);
                state.setUpperAlpha(true);
                state.setLowerAlpha(true);
                state.setNumericalChars(true);
                state.setPunctuationOne(false);
                state.setPunctuationTwo(false);
                state.setPunctuationThree(false);
                state.setPunctuationFour(false);
                state.setPunctuationFive(false);
                state.setAlsoIncludeFrom("");
                state.setExcludeFrom("");
                passwordField.setText("");
                aZButton.setBackground(new Color(0xA3BE8C));
                lowerazButton.setBackground(new Color(0xA3BE8C));
                a09Button.setBackground(new Color(0xA3BE8C));
                punctOneButton.setBackground(new Color(0xD8DEE9));
                punctTwoButton.setBackground(new Color(0xD8DEE9));
                punctThreeButton.setBackground(new Color(0xD8DEE9));
                punctFourButton.setBackground(new Color(0xD8DEE9));
                punctFiveButton.setBackground(new Color(0xD8DEE9));
                includeFrom.setText("");
                excludeFrom.setText("");

                dashboardViewModel.getState().setRightPanelView("dashboard");
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
        if (state instanceof GeneratePasswordState) {
            GeneratePasswordState generatePasswordState = (GeneratePasswordState) evt.getNewValue();
            if (generatePasswordState.getGeneratePasswordError() != null) {
                JOptionPane.showMessageDialog(this, generatePasswordState.getGeneratePasswordError());
            }
            else {
                passwordField.setText(((GeneratePasswordState) state).getPasswordField());
            }
        }
    }
}
