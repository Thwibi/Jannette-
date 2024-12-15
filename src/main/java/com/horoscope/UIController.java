package com.horoscope;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class UIController {
    @FXML
    private TextField cityField;
    @FXML
    private Button fetchButton;
    @FXML
    private ImageView moonImageView;

    @FXML
    public void fetchMoonPhase() {
        try {
            String cityName = cityField.getText();
            double[] coordinates = CityToCoordinates.getCoordinates(cityName);
            String imageUrl = AstronomyAPI.fetchMoonPhaseImage(coordinates[0], coordinates[1], "1997-04-23");

            moonImageView.setImage(new Image(imageUrl));
        } catch (Exception e) {
            e.printStackTrace();
            moonImageView.setImage(new Image("/images/default_moon.png"));
        }
    }
}
