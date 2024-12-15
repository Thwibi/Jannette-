package com.horoscope;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class HoroscopeApp extends Application {

    private TextField birthDateField;
    private TextField birthCityField;
    private TextArea resultArea;
    private ImageView moonImageView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Horoscope & Zodiac Finder");

        // Create UI elements
        Label birthDateLabel = new Label("Enter your Birth Date (YYYY-MM-DD):");
        birthDateField = new TextField();

        Label birthCityLabel = new Label("Enter your Birth City:");
        birthCityField = new TextField();

        Button submitButton = new Button("Submit");
        Button resetButton = new Button("Reset");
        resultArea = new TextArea();
        resultArea.setEditable(false);

        moonImageView = new ImageView();
        moonImageView.setFitHeight(200);
        moonImageView.setFitWidth(200);

        // Layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(15));
        layout.getChildren().addAll(birthDateLabel, birthDateField, birthCityLabel, birthCityField, submitButton, resetButton, resultArea, moonImageView);

        // Button actions
        submitButton.setOnAction(e -> processUserInput());
        resetButton.setOnAction(e -> resetFields());

        // Scene setup
        Scene scene = new Scene(layout, 400, 600);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("file:venus.png"));
        primaryStage.show();
    }

    private void processUserInput() {
        String birthDateString = birthDateField.getText();
        String birthCity = birthCityField.getText();

        try {
            LocalDate birthDate = LocalDate.parse(birthDateString, DateTimeFormatter.ISO_DATE);
            double[] location = GeoLocationUtil.getCoordinates(birthCity);
            String zodiacSign = ZodiacUtils.getZodiacSign(birthDate);
            String rulingPlanet = ZodiacUtils.getRulingPlanet(zodiacSign);
            String moonPhase = AstronomyAPI.getMoonPhase(birthDate, location[0], location[1]);

            resultArea.setText(String.format(
                "Birth Date: %s\n" +
                "Birth City: %s\n" +
                "Zodiac Sign: %s\n" +
                "Ruling Planet: %s\n" +
                "Moon Phase: %s\n",
                birthDateString, birthCity, zodiacSign, rulingPlanet, moonPhase
            ));

            moonImageView.setImage(new Image(AstronomyAPI.getMoonPhaseImageUrl(moonPhase)));
        } catch (DateTimeParseException e) {
            showError("Invalid date format. Please use YYYY-MM-DD.");
        } catch (Exception e) {
            showError("Error: " + e.getMessage());
        }
    }

    private void resetFields() {
        birthDateField.clear();
        birthCityField.clear();
        resultArea.clear();
        moonImageView.setImage(null);
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
