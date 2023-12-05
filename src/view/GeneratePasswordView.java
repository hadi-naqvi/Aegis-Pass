package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeneratePasswordView extends JPanel {
    private JPanel generatePasswordPanel;
    private JPanel topPanel;
    private JTextField passwordField;
    private JProgressBar passwordQuality;
    private JPanel middlePanel;
    private JSlider slider1;
    private JButton regenerateButton;
    private JButton copyButton;
    private JPanel charsetSelection;
    private JButton aZButton;
    private JButton a09Button;
    private JButton aZButton1;
    private JButton extendedASCIIButton;
    private JButton button5;
    private JButton $Button;
    private JButton _Button;
    private JButton button8;
    private JButton button9;
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

    public GeneratePasswordView() {
        this.setLayout(new GridLayout());
        this.add(generatePasswordPanel);
    }

    public static class JGradientButton extends JButton {
        private JGradientButton(String text) {
            super(text);
            setContentAreaFilled(false);
            setFocusPainted(false); // used for demonstration
        }

        @Override
        protected void paintComponent(Graphics g) {
            final Graphics2D g2 = (Graphics2D) g.create();
            g2.setPaint(new GradientPaint(
                    new Point(0, 0),
                    Color.GREEN,
                    new Point(0, getHeight()),
                    Color.RED.darker()));
            g2.fillRect(0, 0, getWidth(), getHeight());
            g2.dispose();

            super.paintComponent(g);
        }
    }
}
