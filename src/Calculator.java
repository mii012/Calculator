import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.math.*;
import java.util.Stack;

//Code by Kenny Yip Coding
public class Calculator {
    int boardWidth = 360;
    int boardHeight = 540;

    Color customLightGray = new Color(212, 212, 210);
    Color customDarkGrey = new Color(80, 80, 80);
    Color customBlack = new Color(28, 28, 28);
    Color customPink = new Color(255, 186, 236);

    String[] buttonValues = {
            "AC", "+/-", "%", "÷", "◄",
            "7", "8", "9", "×", "",
            "4", "5", "6", "-", "",
            "1", "2", "3", "+", "",
            "0", ".", "√", "=", "",
    };
    String[] rightSymbols = { "÷", "×", "-", "+", "=" };
    String[] topSymbols = { "AC", "+/-", "%", "◄" };

    JFrame frame = new JFrame("Calculator");
    JLabel displayLabel = new JLabel();
    JPanel displayPanel = new JPanel();
    JPanel buttonsPanel = new JPanel();

    // A+B, A-B, A*B, A/B
    String A = "0";
    String operator = null;
    String B = null;

    // array for buttonvalues
    Stack<String> allButtonValues = new Stack<String>();

    // constructor
    Calculator() {
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

        buttonsPanel.setLayout(new GridLayout(5, 5));
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
                    String resultCalc = "";

                    if (Arrays.asList(rightSymbols).contains(buttonValue)) {
                        if (buttonValue == "=") {
                            allButtonValues.clear();
                            System.out.println("Put numbers in: " + allButtonValues);
                            if (A != null) {
                                B = displayLabel.getText();
                                double numA = Double.parseDouble(A);
                                double numB = Double.parseDouble(B);

                                if (operator == "+") {
                                    resultCalc = removeZeroDecimal(numA + numB);
                                    allButtonValues.push(resultCalc);
                                    displayLabel.setText(resultCalc);
                                    System.out.println("Put numbers in: " + allButtonValues);

                                } else if (operator == "-") {
                                    resultCalc = removeZeroDecimal(numA - numB);
                                    allButtonValues.push(resultCalc);
                                    displayLabel.setText(resultCalc);
                                    System.out.println("Put numbers in: " + allButtonValues);

                                } else if (operator == "×") {
                                    resultCalc = removeZeroDecimal(numA * numB);
                                    allButtonValues.push(resultCalc);
                                    displayLabel.setText(resultCalc);
                                    System.out.println("Put numbers in: " + allButtonValues);

                                } else if (operator == "÷") {
                                    resultCalc = removeZeroDecimal(numA / numB);
                                    allButtonValues.push(resultCalc);
                                    displayLabel.setText(resultCalc);
                                    System.out.println("Put numbers in: " + allButtonValues);
                                }
                                // resets A, b and operator to default values
                                clearAll();
                            }

                        } else if ("+-×÷".contains(buttonValue)) {
                            allButtonValues.clear();
                            // ensures that operator is not clicked twice
                            if (operator == null) { // only one operator clicked
                                // number is saved
                                A = displayLabel.getText();
                                displayLabel.setText("0");
                                B = "0";
                            }
                            // if another operator is clicked, it changes to newest operator
                            // A is still saved value and not 0
                            operator = buttonValue;
                        }

                    } else if (Arrays.asList(topSymbols).contains(buttonValue)) {
                        if (buttonValue == "AC") {
                            allButtonValues.clear();
                            // resets A, b and operator to default values
                            clearAll();
                            displayLabel.setText("0");
                        } else if (buttonValue == "+/-") {
                            double numDisplay = Double.parseDouble(displayLabel.getText());
                            numDisplay *= -1;
                            displayLabel.setText(removeZeroDecimal(numDisplay));

                        } else if (buttonValue == "%") {
                            double numDisplay = Double.parseDouble(displayLabel.getText());
                            numDisplay /= 100;
                            displayLabel.setText(removeZeroDecimal(numDisplay));

                            // undo button by Mia Haworth
                        } else if (buttonValue == "◄") {
                            if (displayLabel.getText() == "0") {
                                displayLabel.setText("0");
                            } else if (displayLabel.getText() != "0") {
                                allButtonValues.pop();
                                System.out.println("Delete numbers: " + allButtonValues);
                                Object[] allValues = allButtonValues.toArray();
                                String withBrackets = Arrays.toString(allValues);
                                // bei leerem Array 0 anzeigen
                                if (allButtonValues.size() == 0) {
                                    withBrackets = "0";
                                }
                                String result = removeBrackets(withBrackets);
                                System.out.println("w/o brackets: " + result);
                                displayLabel.setText(result);
                            }
                            // zahl speichern, ergbnis von rechnung und andere löschen
                        }

                    } else { // digits or decimal
                        allButtonValues.push(buttonValue);
                        System.out.println("Put numbers in: " + allButtonValues);
                        if (buttonValue == ".") {
                            // if displaylabel does not have decimal place (buttonValue = .)
                            if (!displayLabel.getText().contains(buttonValue)) {
                                displayLabel.setText(displayLabel.getText() + buttonValue);
                            }
                        } else if ("0123456789".contains(buttonValue)) {
                            // to prevent 0005, marks beginning, only replaces 0
                            if (displayLabel.getText() == "0") {
                                displayLabel.setText(buttonValue);
                            } else {
                                // no relacement anymore,adds numbers
                                displayLabel.setText(displayLabel.getText() + buttonValue);
                            }
                        } else { // square root by Mia Haworth
                            double currentValue = Double.parseDouble(displayLabel.getText());
                            currentValue = Math.sqrt(currentValue);
                            displayLabel.setText(Double.toString(currentValue));
                        }
                    }
                }
            });
            // works best at the end when everything is put on the frame (like the label
            // etc)
            frame.setVisible(true);
        }
    }

    void clearAll() {
        A = "0";
        operator = null;
        B = null;
    }

    String removeZeroDecimal(double numDisplay) {
        if (numDisplay % 1 == 0) {
            // double to int and int to string
            return Integer.toString((int) numDisplay);
        }
        return Double.toString(numDisplay);
    }

    String removeBrackets(String withBrackets) {
        String withoutBrackets = withBrackets.replace("[", "");
        withoutBrackets = withoutBrackets.replace("]", "");
        withoutBrackets = withoutBrackets.replace(",", "");
        withoutBrackets = withoutBrackets.replace(" ", "");
        return withoutBrackets;
    }
}

// noch effizienter machen mit switch?