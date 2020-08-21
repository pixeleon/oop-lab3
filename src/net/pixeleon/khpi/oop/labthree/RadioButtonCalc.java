package net.pixeleon.khpi.oop.labthree;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RadioButtonCalc extends Application {
    private ToggleGroup toggleGroupMain = new ToggleGroup();
    private TextField textFieldOne, textFieldTwo, textFieldResult;
    private Button buttonCalculate;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox parentNode = new VBox(10);
        parentNode.setPadding(new Insets(5));

        Label labelEnterData = new Label("enter two operands: ");
        Label labelChooseOperation = new Label("choose one of operations:");

        textFieldOne = new TextField();
        textFieldTwo = new TextField();

        parentNode.getChildren().addAll(labelEnterData, textFieldOne, textFieldTwo, labelChooseOperation);
        parentNode.getChildren().addAll(getRadioButtons());

        buttonCalculate = new Button("calculate");
        buttonCalculate.setOnAction(this::doCalculate);
        buttonCalculate.setPrefWidth(Double.MAX_VALUE);
        textFieldResult = new TextField();
        textFieldResult.setEditable(false);
        parentNode.getChildren().addAll(buttonCalculate, textFieldResult);

        Scene scene = new Scene(parentNode);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private List<RadioButton> getRadioButtons() {
        RadioButton radioButtonPlus = new RadioButton("sum up");
        radioButtonPlus.setSelected(true);
        RadioButton radioButtonMinus = new RadioButton("subtract");
        RadioButton radioButtonMult = new RadioButton("multiply");
        RadioButton radioButtonDiv = new RadioButton("divide");

        List<RadioButton> radioButtons =
                new ArrayList<>(Arrays.asList(radioButtonPlus, radioButtonMinus, radioButtonMult, radioButtonDiv));
        for (RadioButton rb : radioButtons) {
            rb.setToggleGroup(toggleGroupMain);
        }
        return radioButtons;
    }

    private void doCalculate(ActionEvent actionEvent) {
        try {
            double operandOne = Double.parseDouble(textFieldOne.getText());
            double operandTwo = Double.parseDouble(textFieldTwo.getText());
            RadioButton selected = (RadioButton) toggleGroupMain.getSelectedToggle();
            switch (selected.getText()) {
                case "sum up":
                    doSumUp(operandOne, operandTwo);
                    return;
                case "subtract":
                    doSubtract(operandOne, operandTwo);
                    return;
                case "multiply":
                    doMultiply(operandOne, operandTwo);
                    return;
                case "divide":
                    doDivide(operandOne, operandTwo);
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid data!");
            alert.setContentText("Please, check your input.");
            alert.showAndWait();
        }
    }

    private void doDivide(double operandOne, double operandTwo) {
        textFieldResult.setText(operandOne / operandTwo + "");
    }

    private void doMultiply(double operandOne, double operandTwo) {
        textFieldResult.setText(operandOne * operandTwo + "");
    }

    private void doSubtract(double operandOne, double operandTwo) {
        textFieldResult.setText(operandOne - operandTwo + "");
    }

    private void doSumUp(double operandOne, double operandTwo) {
        textFieldResult.setText(operandOne + operandTwo + "");
    }
}
