package net.pixeleon.khpi.oop.labthree;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class DictionaryApp extends Application {
    private TextField textFieldWord, textFieldTranslation;
    private Map<String, String> dictionaryMap;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        dictionaryMap = new TreeMap<>();
        initDictionary();

        VBox parentNode = new VBox(10);
        parentNode.setPadding(new Insets(5));
        textFieldWord = new TextField();
        HBox buttonsBox = new HBox(10);
        buttonsBox.setPadding(new Insets(5));
        Button buttonSearch = new Button("search word");
        buttonSearch.setOnAction(this::doWordSearch);
        Button buttonAdd = new Button("add word");
        buttonAdd.setOnAction(this::doWordAdding);
        buttonsBox.getChildren().addAll(buttonSearch, buttonAdd);
        textFieldTranslation = new TextField();

        parentNode.getChildren().addAll(textFieldWord, buttonsBox, textFieldTranslation);
        Scene scene = new Scene(parentNode);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void doWordAdding(ActionEvent actionEvent) {
        String word = textFieldWord.getText().trim();
        if(!word.isEmpty()) {
            addWord(word);
        }
    }

    private void initDictionary() {
        dictionaryMap.put("cat", "кот, кошка");
        dictionaryMap.put("dog", "собака, пёс");
        dictionaryMap.put("bat", "летучая мышь");
        dictionaryMap.put("bag", "мешок; сумка; чемодан, пакет");
        dictionaryMap.put("car", "автомобиль, машина");
    }

    private void doWordSearch(ActionEvent actionEvent) {
        try {
            textFieldTranslation.clear();
            String word = textFieldWord.getText().trim();
            String translation = dictionaryMap.get(word);
            if (translation != null) {
                textFieldTranslation.setText(translation);
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("No word");
                alert.setHeaderText("There is no such word in dictionary");
                alert.setContentText("Do you want to add it?");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.isPresent() && option.get() == ButtonType.OK) {
                    addWord(word);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addWord(String word) {
        try {
            Dialog<String> dialog = new TextInputDialog();
            dialog.setTitle("New word");
            dialog.setHeaderText("Enter translation for word '" + word + "'");
            dialog.setContentText("Translation: ");
            Optional<String> input = dialog.showAndWait();
            input.ifPresent(translation -> dictionaryMap.put(word, translation));
            textFieldWord.clear();
            textFieldTranslation.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearTextFields() {
        textFieldWord.clear();
        textFieldTranslation.clear();
    }
}
