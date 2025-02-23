package fr.lucasbmmn.calculator;

import fr.lucasbmmn.calculator.utils.Operator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {
    @FXML
    private Label resultLabel; // Label to display the result

    private String currentStep = "number1"; // Current calculation step

    private double number1; // First number
    private double number2; // Second number
    private String operator; // Operator

    @FXML
    private void onOneButtonClick() {
        this.onNumberButtonClick(1);
    }
    @FXML
    private void onTwoButtonClick() {
        this.onNumberButtonClick(2);
    }

    @FXML
    private void onThreeButtonClick() {
        this.onNumberButtonClick(3);
    }

    @FXML
    private void onFourButtonClick() {
        this.onNumberButtonClick(4);
    }

    @FXML
    private void onFiveButtonClick() {
        this.onNumberButtonClick(5);
    }

    @FXML
    private void onSixButtonClick() {
        this.onNumberButtonClick(6);
    }

    @FXML
    private void onSevenButtonClick() {
        this.onNumberButtonClick(7);
    }

    @FXML
    private void onEightButtonClick() {
        this.onNumberButtonClick(8);
    }

    @FXML
    private void onNineButtonClick() {
        this.onNumberButtonClick(9);
    }

    @FXML
    private void onZeroButtonClick() {
        this.onNumberButtonClick(0);
    }

    @FXML
    private void onAdditionButtonClick() {
        this.onOperatorButtonClick(Operator.ADDITION.getSymbol());
    }

    @FXML
    private void onSubtractionButtonClick() {
        this.onOperatorButtonClick(Operator.SUBTRACTION.getSymbol());
    }

    @FXML
    private void onMultiplicationButtonClick() {
        this.onOperatorButtonClick(Operator.MULTIPLICATION.getSymbol());
    }

    @FXML
    private void onDivisionButtonClick() {
        this.onOperatorButtonClick(Operator.DIVISION.getSymbol());
    }

    private void onNumberButtonClick(int number) {
        switch (this.currentStep) {
            case "number1":
                this.number1 = this.number1 * 10 + number;
                this.resultLabel.setText(String.valueOf(number1));
                break;
            case "number2":
                this.number2 = this.number2 * 10 + number;
                this.resultLabel.setText(String.valueOf(number2));
                break;
        }
    }

    private void onOperatorButtonClick(String operator) {
        this.operator = operator;
        this.currentStep = "number2";
    }

    @FXML
    private void calculate() {
        double result = switch (this.operator) {
            case "+" -> this.number1 + this.number2;
            case "-" -> this.number1 - this.number2;
            case "*" -> this.number1 * this.number2;
            case "/" -> this.number1 / this.number2;
            default -> 0;
        };
        this.resultLabel.setText(String.valueOf(result));
    }
}