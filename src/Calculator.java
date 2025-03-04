import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.LineBorder;

//Code by Kenny Yip Coding
public class Calculator {
    int boardWidth = 360;
    int boardHeight = 540;

    Color customLightGray = new Color(212, 212, 210);
    Color customDarkGrey = new Color(80, 80, 80);
    Color customBlack = new Color(28, 28, 28);
    Color customPink = new Color(255, 186, 236);

    String[] buttonValues = {
            "AC", "+/-", "%", "÷",
            "7", "8", "9", "×",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", ".", "√", "="
    };
    String[] rightSymbols = { "÷", "×", "-", "+", "=" };
    String[] topSymbols = { "AC", "+/-", "%" };

    JFrame frame = new JFrame("Calculator");
    JLabel displayLabel = new JLabel();
    JPanel displayPanel = new JPanel();
    JPanel buttonsPanel = new JPanel();

    // constructor
    Calculator() {
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null); // center frame
        frame.setResizable(false); // user cannot resize window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // terminates program when pressing x Button
        frame.setLayout(new BorderLayout()); // place components n o s w in frame

        displayLabel.setBackground(customBlack);
        displayLabel.setForeground(Color.white);
        displayLabel.setFont(new Font("Arial", Font.PLAIN, 80));
        displayLabel.setHorizontalAlignment(JLabel.RIGHT);
        displayLabel.setText("0");
        displayLabel.setOpaque(true);

        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(displayLabel);
        frame.add(displayPanel, BorderLayout.NORTH); // entire displayPanel is placed north on the screen

        buttonsPanel.setLayout(new GridLayout(5, 4));
        buttonsPanel.setBackground(customBlack);
        frame.add(buttonsPanel);

        for (int i = 0; i < buttonValues.length; i++) {
            JButton button = new JButton();
            String buttonValue = buttonValues[i];
            button.setFont(new Font("Arial", Font.PLAIN, 30));
            button.setText(buttonValue);
            button.setFocusable(false); // get rid of lines around symbols
            button.setBorder(new LineBorder(customBlack));
            // customize buttons
            if (Arrays.asList(topSymbols).contains(buttonValue)) { // if currentsymbol is part of topsymbols
                button.setBackground(customLightGray);
                button.setForeground(customBlack); // fontcolor
            } else if (Arrays.asList(rightSymbols).contains(buttonValue)) {
                button.setBackground(customPink);
                button.setForeground(Color.BLACK);
            } else {
                button.setBackground(customDarkGrey);
                button.setForeground(Color.WHITE);
            }
            buttonsPanel.add(button);

            button.addActionListener(new ActionListener() {
                // when mouseclick, action is performed and e refers to the action
                public void actionPerformed(ActionEvent e) {
                    // where does action come from, source of the click is a jbutton
                    JButton button = (JButton) e.getSource();
                    // get symbol of math operation
                    String buttonValue = button.getText();

                    if (Arrays.asList(rightSymbols).contains(buttonValue)) {

                    } else if (Arrays.asList(topSymbols).contains(buttonValue)) {

                    } else { // digits or decimal
                        if (buttonValue == ".") {

                        } else if ("123456789".contains(buttonValue)) {
                            if (displayLabel.getText() == "0") {

                            }
                        }
                    }
                }
            });
        }
    }
}
