package fr.lucasbmmn.calculator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {
    @FXML
    private Label calculationLabel; // Label to display the calculation
    @FXML
    private Label resultLabel; // Label to display the result

    private String number1 = "0";
    private String number2 = "0";
    private char operator;

    private String calculationStep = "number1";

    @FXML
    private void onOneButtonClick() {
        this.onDigitButtonClick('1');
    }

    @FXML
    private void onTwoButtonClick() {
        this.onDigitButtonClick('2');
    }

    @FXML
    private void onThreeButtonClick() {
        this.onDigitButtonClick('3');
    }

    @FXML
    private void onFourButtonClick() {
        this.onDigitButtonClick('4');
    }

    @FXML
    private void onFiveButtonClick() {
        this.onDigitButtonClick('5');
    }

    @FXML
    private void onSixButtonClick() {
        this.onDigitButtonClick('6');
    }

    @FXML
    private void onSevenButtonClick() {
        this.onDigitButtonClick('7');
    }

    @FXML
    private void onEightButtonClick() {
        this.onDigitButtonClick('8');
    }

    @FXML
    private void onNineButtonClick() {
        this.onDigitButtonClick('9');
    }

    @FXML
    private void onZeroButtonClick() {
        this.onDigitButtonClick('0');
    }

    private void onDigitButtonClick(char digit) {
        if (this.calculationStep.equals("number1")) {
            if (this.number1.equals("0")) this.number1 = "";
            this.number1 += digit;
            this.calculationStep = "number1";
            this.resultLabel.setText(number1);
        }
        else {
            if (this.number2.equals("0")) this.number2 = "";
            this.number2 += digit;
            this.calculationStep = "number2";
            this.resultLabel.setText(number2);
        }
    }

    @FXML
    private void onAdditionButtonClick() {
        this.onOperatorButtonClick('+');
    }

    @FXML
    private void onSubtractionButtonClick() {
        this.onOperatorButtonClick('-');
    }

    @FXML
    private void onMultiplicationButtonClick() {
        this.onOperatorButtonClick('×');
    }

    @FXML
    private void onDivisionButtonClick() {
        this.onOperatorButtonClick('÷');
    }

    private void onOperatorButtonClick(char operatorClicked) {
        if (this.calculationStep.equals("number1") || (this.calculationStep.equals("number2") && number2.equals("0"))){
            this.operator = operatorClicked;
            this.calculationStep = "number2";
            this.calculationLabel.setText(this.number1 + ' ' + this.operator);
        }
        else {
            calculate();
            this.operator = operatorClicked;
            this.calculationStep = number2;
        }
    }

    @FXML
    private void onSignButtonClick() {
        if (this.calculationStep.equals("number1")) {
            if (this.number1.charAt(0) == '-') this.number1 = this.number1.substring(1);
            else this.number1 = '-' + this.number1;
        }
        else {
            if (this.number2.charAt(0) == '-') this.number2 = this.number2.substring(1);
            else this.number2 = '-' + this.number2;
        }
    }

    @FXML
    private void onBackspaceButtonClick() {
        if (this.calculationStep.equals("number1")) {
            this.number1 = this.number1.substring(0, this.number1.length()-1);
            if (this.number1.isEmpty()) this.number1 = "0";
            this.resultLabel.setText(number1);
        }
        else {
            this.number2 = this.number2.substring(0, this.number2.length()-1);
            if (this.number2.isEmpty()) this.number2 = "0";
            this.resultLabel.setText(number2);
        }
    }

    @FXML
    private void onPercentButtonClick() {

    }

    @FXML
    private void onClearEntryButtonClick() {
        if (this.calculationStep.equals("number1")) {
            this.number1 = "0";
            this.resultLabel.setText(number1);
        }
        else {
            this.number2 = "0";
            this.resultLabel.setText(number2);
        }
    }

    @FXML
    private void onClearButtonClick() {
        this.number1 = "0";
        this.number2 = "0";
        this.calculationStep = "number1";
        this.calculationLabel.setText("");
        this.resultLabel.setText("0");
    }

    @FXML
    private void onInverseButtonClick() {
        // TODO: Blocking possibility to continue writing of number after inverse
        if (this.calculationStep.equals("number1")) {
            this.resultLabel.setText(String.valueOf(1/Double.parseDouble(number1)));
            this.number1 = "1/(" + this.number1 + ")";
            this.calculationLabel.setText(this.number1);
        }
        else {
            this.resultLabel.setText(String.valueOf(1/Double.parseDouble(number2)));
            this.number2 = "1/(" + this.number2 + ")";
            this.calculationLabel.setText(this.number1 + ' ' + this.operator + ' ' + this.number2);
        }
    }

    @FXML
    private void onSquareButtonClick() {
        // TODO: Blocking possibility to continue writing of number after square
        if (this.calculationStep.equals("number1")) {
            this.resultLabel.setText(String.valueOf(Math.pow(Double.parseDouble(number1), 2)));
            this.number1 = "sqr(" + this.number1 + ")";
            this.calculationLabel.setText(this.number1);
        }
        else {
            this.resultLabel.setText(String.valueOf(Math.pow(Double.parseDouble(number2), 2)));
            this.number2 = "sqr(" + this.number2 + ")";
            this.calculationLabel.setText(this.number1 + ' ' + this.operator + ' ' + this.number2);
        }
    }

    @FXML
    private void onSquareRootButtonClick() {
        // TODO: Blocking possibility to continue writing of number after square root
        if (this.calculationStep.equals("number1")) {
            this.resultLabel.setText(String.valueOf(Math.sqrt(Double.parseDouble(number1))));
            this.number1 = "sqrt(" + this.number1 + ")";
            this.calculationLabel.setText(this.number1);
        }
        else {
            this.resultLabel.setText(String.valueOf(Math.sqrt(Double.parseDouble(number2))));
            this.number2 = "sqrt(" + this.number2 + ")";
            this.calculationLabel.setText(this.number1 + ' ' + this.operator + ' ' + this.number2);
        }
    }

    @FXML
    private void onPointButtonClick() {
        // TODO: Implement decimal numbers
        if (this.calculationStep.equals("number1")) {

        }
        else {

        }
    }

    @FXML
    private void calculate() {
        double result = switch (this.operator) {
            case '+' -> Double.parseDouble(this.number1) + Double.parseDouble(this.number2);
            case '-' -> Double.parseDouble(this.number1) - Double.parseDouble(this.number2);
            case '×' -> Double.parseDouble(this.number1) * Double.parseDouble(this.number2);
            case '/' -> Double.parseDouble(this.number1) / Double.parseDouble(this.number2);
            default -> 0;
        };
        this.calculationLabel.setText(number1 + ' ' + operator + ' ' + number1 + ' ' + '=');
        this.resultLabel.setText(String.valueOf(result));
        this.number1 = "0";
        this.number2 = "0";
        this.calculationStep = "number1";
    }
}