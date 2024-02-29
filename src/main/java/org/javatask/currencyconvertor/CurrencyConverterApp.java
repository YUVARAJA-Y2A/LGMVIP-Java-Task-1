package org.javatask.currencyconvertor;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CurrencyConverterApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        TextField amountField = new TextField();
        TextField resultField = new TextField();
        resultField.setEditable(false);
        Button convertButton = new Button("Convert");

        // Create a ComboBox for selecting "from" currency
        ObservableList<String> currencyOptions = FXCollections.observableArrayList("USD", "EUR", "GBP", "INR", "JPY");
        ComboBox<String> fromCurrencyComboBox = new ComboBox<>(currencyOptions);
        fromCurrencyComboBox.setValue("USD"); // Set default value

        // Create a ComboBox for selecting "to" currency
        ComboBox<String> toCurrencyComboBox = new ComboBox<>(currencyOptions);
        toCurrencyComboBox.setValue("INR"); // Set default value

        // Add a listener to disable selecting the same currency in both ComboBoxes
        fromCurrencyComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && newValue.equals(toCurrencyComboBox.getValue())) {
                toCurrencyComboBox.setValue(null);
            }

            // Hide "To Currency" ComboBox when "INR" is selected
            toCurrencyComboBox.setVisible(!"INR".equals(newValue));
        });

        toCurrencyComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && newValue.equals(fromCurrencyComboBox.getValue())) {
                fromCurrencyComboBox.setValue(null);
            }
        });

        convertButton.setOnAction(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                String fromCurrency = fromCurrencyComboBox.getValue();
                String toCurrency = toCurrencyComboBox.getValue();

                if (fromCurrency == null || toCurrency == null) {
                    resultField.setText("Select both 'From' and 'To' currencies");
                    return;
                }

                double conversionRate = ConversionRates.getConversionRate(fromCurrency, toCurrency);
                double result = amount * conversionRate;

                resultField.setText(String.valueOf(result));
            } catch (NumberFormatException ex) {
                resultField.setText("Invalid amount");
            } catch (IllegalArgumentException ex) {
                resultField.setText("Conversion rate not available");
            }
        });

        VBox root = new VBox(10);
        root.getChildren().addAll(
                new Label("Amount:"), amountField,
                new Label("From Currency:"), fromCurrencyComboBox,
                new Label("To Currency:"), toCurrencyComboBox,
                new Label("Result:"), resultField,
                convertButton
        );

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Currency Converter");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
