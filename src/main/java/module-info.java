module fr.lucasbmmn.calculator {
    requires javafx.controls;
    requires javafx.fxml;


    opens fr.lucasbmmn.calculator to javafx.fxml;
    exports fr.lucasbmmn.calculator;
}