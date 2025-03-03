package fr.lucasbmmn.calculator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * MainController is the controller class for a JavaFX-based calculator application.
 * It handles user interactions and updates the UI accordingly.
 */
public class MainController {
    @FXML
    private Label calculationLabel; // Label to display the current calculation
    @FXML
    private Label resultLabel; // Label to display the result of the calculation

    private String number1 = "0"; // First operand in the calculation
    private String number2 = "0"; // Second operand in the calculation
    private char operator = '?'; // Operator used in the calculation

    private String calculationStep = "number1"; // Current step in the calculation process

    /**
     * Handles the click event for the '1' button.
     */
    @FXML
    private void onOneButtonClick() {
        this.onDigitButtonClick('1');
    }

    /**
     * Handles the click event for the '2' button.
     */
    @FXML
    private void onTwoButtonClick() {
        this.onDigitButtonClick('2');
    }

    /**
     * Handles the click event for the '3' button.
     */
    @FXML
    private void onThreeButtonClick() {
        this.onDigitButtonClick('3');
    }

    /**
     * Handles the click event for the '4' button.
     */
    @FXML
    private void onFourButtonClick() {
        this.onDigitButtonClick('4');
    }

    /**
     * Handles the click event for the '5' button.
     */
    @FXML
    private void onFiveButtonClick() {
        this.onDigitButtonClick('5');
    }

    /**
     * Handles the click event for the '6' button.
     */
    @FXML
    private void onSixButtonClick() {
        this.onDigitButtonClick('6');
    }

    /**
     * Handles the click event for the '7' button.
     */
    @FXML
    private void onSevenButtonClick() {
        this.onDigitButtonClick('7');
    }

    /**
     * Handles the click event for the '8' button.
     */
    @FXML
    private void onEightButtonClick() {
        this.onDigitButtonClick('8');
    }

    /**
     * Handles the click event for the '9' button.
     */
    @FXML
    private void onNineButtonClick() {
        this.onDigitButtonClick('9');
    }

    /**
     * Handles the click event for the '0' button.
     */
    @FXML
    private void onZeroButtonClick() {
        this.onDigitButtonClick('0');
    }

    /**
     * Handles the click event for any digit button.
     * Updates the current number being entered based on the calculation step.
     *
     * @param digit The digit clicked by the user.
     */
    private void onDigitButtonClick(char digit) {
        if (this.calculationStep.equals("operator")) {
            this.number1 = "";
            this.calculationStep = "number1";
        }

        if (this.calculationStep.equals("result")) {
            this.calculationStep = "number1";
            this.updateCalculationLabel();
        }

        if ((this.calculationStep.equals("number1")) && this.number1.length() < 9) {
            if (this.number1.equals("0")) this.number1 = "";
            this.number1 += digit;
        }
        else if (this.calculationStep.equals("number2") && this.number2.length() < 9) {
            if (this.number2.equals("0")) this.number2 = "";
            this.number2 += digit;
        }
        this.updateResultLabel();
    }

    /**
     * Handles the click event for the addition button.
     */
    @FXML
    private void onAdditionButtonClick() {
        this.onOperatorButtonClick('+');
    }

    /**
     * Handles the click event for the subtraction button.
     */
    @FXML
    private void onSubtractionButtonClick() {
        this.onOperatorButtonClick('-');
    }

    /**
     * Handles the click event for the multiplication button.
     */
    @FXML
    private void onMultiplicationButtonClick() {
        this.onOperatorButtonClick('×');
    }

    /**
     * Handles the click event for the division button.
     */
    @FXML
    private void onDivisionButtonClick() {
        this.onOperatorButtonClick('÷');
    }

    /**
     * Handles the click event for any operator button.
     * Sets the operator and updates the calculation step.
     *
     * @param operatorClicked The operator clicked by the user.
     */
    private void onOperatorButtonClick(char operatorClicked) {
        if (this.calculationStep.equals("number1") ||
                (this.calculationStep.equals("number2") && number2.equals("0")) ||
                this.calculationStep.equals("operator")){
            if (this.number1.endsWith(".")) {
                this.number1 = this.number1.substring(0, this.number1.length() - 1);
            }
            this.operator = operatorClicked;
            this.calculationStep = "number2";
        }
        else if (this.calculationStep.equals("number2") ||
                this.calculationStep.equals("postPercent")) {
            if (this.number2.endsWith(".")) {
                this.number2 = this.number2.substring(0, this.number2.length() - 1);
            }
            this.calculate();
            this.number1 = this.resultLabel.getText();
            this.operator = operatorClicked;
            this.calculationStep = "number2";
        }
        this.updateCalculationLabel();
    }

    /**
     * Handles the click event for the sign button.
     * Toggles the sign of the current number being entered.
     */
    @FXML
    private void onSignButtonClick() {
        if (this.calculationStep.equals("number1") || this.calculationStep.equals("operator")) {
            if (this.number1.charAt(0) == '-') this.number1 = this.number1.substring(1);
            else this.number1 = '-' + this.number1;
        }
        else if (this.calculationStep.equals("number2") || this.calculationStep.equals("postPercent")) {
            if (this.number2.charAt(0) == '-') this.number2 = this.number2.substring(1);
            else this.number2 = '-' + this.number2;
        }
        this.updateCalculationLabel();
        this.updateResultLabel();
    }

    /**
     * Handles the click event for the backspace button.
     * Removes the last digit of the current number being entered.
     */
    @FXML
    private void onBackspaceButtonClick() {
        if (this.calculationStep.equals("number1")) {
            this.number1 = this.number1.substring(0, this.number1.length()-1);
            if (this.number1.isEmpty()) this.number1 = "0";
        }
        // If the number contains (, then it contain square, square root or inverse
        else if (this.calculationStep.equals("number2") && !this.number2.contains("(")) {
            this.number2 = this.number2.substring(0, this.number2.length()-1);
            if (this.number2.isEmpty()) this.number2 = "0";
        }
        this.updateResultLabel();
    }

    /**
     * Handles the click event for the percent button.
     * Currently not implemented.
     */
    @FXML
    private void onPercentButtonClick() {
        if (this.calculationStep.equals("number2") || this.calculationStep.equals("postPercent")) {
            this.number2 = String.valueOf(this.eval(this.number1) * this.eval(this.number2) / 100);
            this.calculationStep = "postPercent";
            this.updateCalculationLabel();
            this.updateResultLabel();
        }
    }

    /**
     * Handles the click event for the clear entry button.
     * Clears the current number being entered.
     */
    @FXML
    private void onClearEntryButtonClick() {
        switch (this.calculationStep) {
            case "number1" -> this.number1 = "0";
            case "operator" -> {
                this.number1 = "0";
                this.calculationStep = "number1";
            }
            case "number2" -> this.number2 = "0";
            case "postPercent" -> {
                this.number2 = "0";
                this.calculationStep = "number2";
            }
        }
        this.updateResultLabel();
    }

    /**
     * Handles the click event for the clear button.
     * Resets the entire calculation.
     */
    @FXML
    private void onClearButtonClick() {
        this.number1 = "0";
        this.number2 = "0";
        this.operator = '?';
        this.calculationStep = "number1";
        this.updateCalculationLabel();
        this.updateResultLabel();
    }

    /**
     * Handles the click event for the inverse button.
     * Inverts the current number being entered.
     */
    @FXML
    private void onInverseButtonClick() {
        if (this.calculationStep.equals("number1")) {
            if (this.number1.endsWith(".")) {
                this.number1 = this.number1.substring(0, this.number1.length() - 1);
            }
            this.number1 = "1/(" + this.number1 + ")";
            this.calculationStep = "operator";
        }
        else if (this.calculationStep.equals("number2") || this.calculationStep.equals("postPercent")) {
            if (this.number2.endsWith(".")) {
                this.number2 = this.number2.substring(0, this.number2.length() - 1);
            }
            this.number2 = "1/(" + this.number2 + ")";
        }
        this.updateCalculationLabel();
        this.updateResultLabel();
    }

    /**
     * Handles the click event for the square button.
     * Squares the current number being entered.
     */
    @FXML
    private void onSquareButtonClick() {
        if (this.calculationStep.equals("number1")) {
            if (this.number1.endsWith(".")) {
                this.number1 = this.number1.substring(0, this.number1.length() - 1);
            }
            this.number1 = "sqr(" + this.number1 + ")";
            this.calculationStep = "operator";
        }
        else if (this.calculationStep.equals("number2") || this.calculationStep.equals("postPercent")) {
            if (this.number2.endsWith(".")) {
                this.number2 = this.number2.substring(0, this.number2.length() - 1);
            }
            this.number2 = "sqr(" + this.number2 + ")";
        }
        this.updateCalculationLabel();
        this.updateResultLabel();
    }

    /**
     * Handles the click event for the square root button.
     * Takes the square root of the current number being entered.
     */
    @FXML
    private void onSquareRootButtonClick() {
        if (this.calculationStep.equals("number1")) {
            if (this.number1.endsWith(".")) {
                this.number1 = this.number1.substring(0, this.number1.length() - 1);
            }
            this.number1 = "sqrt(" + this.number1 + ")";
            this.calculationStep = "operator";
        }
        else if (this.calculationStep.equals("number2") || this.calculationStep.equals("postPercent")) {
            if (this.number2.endsWith(".")) {
                this.number2 = this.number2.substring(0, this.number2.length() - 1);
            }
            this.number2 = "sqrt(" + this.number2 + ")";
        }
        this.updateCalculationLabel();
        this.updateResultLabel();
    }

    /**
     * Handles the click event for the decimal point button.
     * Currently not implemented.
     */
    @FXML
    private void onPointButtonClick() {
        if (this.calculationStep.equals("number1")) {
            if (!number1.contains(".")) this.number1 += '.';
        }
        else if (this.calculationStep.equals("number2")) {
            if (!number2.contains(".")) this.number2 += '.';
        }
        this.updateResultLabel();
    }

    /**
     * Performs the calculation based on the current numbers and operator.
     * Updates the UI with the result.
     */
    @FXML
    private void calculate() {
        this.calculationStep = "result";
        double result = switch (this.operator) {
            case '+' -> this.eval(this.number1) + this.eval(this.number2);
            case '-' -> this.eval(this.number1) - this.eval(this.number2);
            case '×' -> this.eval(this.number1) * this.eval(this.number2);
            case '÷' -> this.eval(this.number1) / this.eval(this.number2);
            default -> 0;
        };
        this.updateCalculationLabel();
        this.resultLabel.setText(this.format(result));
        this.number1 = "0";
        this.number2 = "0";
        this.operator = '?';
    }

    /**
     * Updates the result label with the current number or result.
     */
    private void updateResultLabel() {
        if (this.calculationStep.equals("number1") || this.calculationStep.equals("operator")) {
            this.resultLabel.setText(this.format(this.eval(this.number1)));
        }
        else if (this.calculationStep.equals("number2") || this.calculationStep.equals("postPercent")) {
            this.resultLabel.setText(this.format(this.eval(this.number2)));
        }
    }

    /**
     * Updates the calculation label with the current calculation expression.
     */
    private void updateCalculationLabel() {
        String text = "";
        if (this.calculationStep.equals("operator")) text = this.number1;
        else if (this.operator != '?') {
            text = this.number1 + ' ' + this.operator;
            if (this.calculationStep.equals("result") || this.calculationStep.equals("postPercent")) text += ' ' + this.number2 + " =";
                // If the number contains (, then it contain square, square root or inverse
            else if (this.number2.contains("(")) text += ' ' + this.number2;
        }
        this.calculationLabel.setText(text);
    }

    /**
     * Evaluates a string representation of a number, which may include
     * expressions like square, square root, or inverse.
     *
     * @param number The string representation of the number.
     * @return The evaluated double value.
     */
    private double eval(String number) {
        double result;
        if (number.startsWith("sqr(")) {
            result = Math.pow(eval(number.substring(4, number.length() - 1)), 2);
        }
        else if (number.startsWith("-sqr(")) {
            result = -(Math.pow(eval(number.substring(5, number.length() - 1)), 2));
        }
        else if (number.startsWith("sqrt(")) {
            result = Math.sqrt(eval(number.substring(5, number.length() - 1)));
        }
        else if (number.startsWith("-sqrt(")) {
            result = -(Math.sqrt(eval(number.substring(6, number.length() - 1))));
        }
        else if (number.startsWith("1/(")) {
            result = 1 / eval(number.substring(3, number.length()-1));
        }
        else if (number.startsWith("-1/(")) {
            result = 1 / -(eval(number.substring(4, number.length()-1)));
        }
        else {
            result = Double.parseDouble(number);
        }
        return result;
    }

    /**
     * Formats a double value into a string with thousands separators.
     *
     * @param number The double value to format.
     * @return The formatted string.
     */
    private String format(double number) {
        String formatedNumber = String.valueOf(number);
        if (number == (int) number) formatedNumber = String.valueOf((int) number);


        int numStartPos = formatedNumber.indexOf('-') + 1;
        int pointPos = formatedNumber.indexOf('.');
        if (pointPos == -1) pointPos = formatedNumber.length();
        int numEndPos = formatedNumber.indexOf('E');
        if (numEndPos == -1) numEndPos = formatedNumber.length();

        int counter = 0;
        for (int i = pointPos+1; i < numEndPos; i++) {
            if (counter == 3) {
                formatedNumber =
                        formatedNumber.substring(0, i) + ' ' + formatedNumber.substring(i);
                counter = 0;
                numEndPos++;
            }
            else counter++;
        }

        counter = 0;
        for (int i = pointPos; i > numStartPos; i--) {
            if (counter == 3) {
                formatedNumber =
                        formatedNumber.substring(0, i) + ' ' + formatedNumber.substring(i);
                counter = 0;
            }
            counter++;
        }

        return formatedNumber;
    }
}
