import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

public class HoroscopeApp extends Application {

    private static final String API_ID = "80e4123c-e9e0-4f2a-ba8c-0a9d2c380759";
    private static final String API_SECRET = "2f88075834d45964f9f7de74723fb929c622bfb81777735beb3fbc6649511cef6f0dc8f5096f02bfa2e0ebebdc5fb7d7c91508fede64d852a0361999d61eadcf2221ee0a21a6a380e5a2fd0305fa7aa6a6b9d2ef5d5821e6a198e6c4e3770f6eaa35e15d79baede83c91a326b133c21c";
    
    private TextField birthDateField;
    private TextField cityField;
    private TextArea resultArea;
    private ImageView moonImageView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Horoscope & Zodiac Sign Finder");

        // Create UI elements
        Label birthDateLabel = new Label("Enter your Birth Date (YYYY-MM-DD):");
        birthDateField = new TextField();
        
        Label cityLabel = new Label("Enter your Birth City:");
        cityField = new TextField();
        
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
        layout.getChildren().addAll(birthDateLabel, birthDateField, cityLabel, cityField, submitButton, resetButton, resultArea, moonImageView);

        // Button actions
        submitButton.setOnAction(e -> processBirthDate());
        resetButton.setOnAction(e -> resetFields());

        // Scene setup
        Scene scene = new Scene(layout, 400, 600);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("file:venus.png"));
        primaryStage.show();
    }

    private void processBirthDate() {
        String birthDateString = birthDateField.getText();
        String cityName = cityField.getText();

        try {
            LocalDate birthDate = LocalDate.parse(birthDateString, DateTimeFormatter.ISO_DATE);
            String zodiacSign = getZodiacSign(birthDate);
            String rulingPlanet = getRulingPlanet(zodiacSign);

            // Get latitude and longitude using the city name
            double[] coordinates = getCoordinatesFromCity(cityName);
            double latitude = coordinates[0];
            double longitude = coordinates[1];

            String moonPhase = getMoonPhase(birthDate, latitude, longitude);

            resultArea.setText(String.format(
                "Birth Date: %s\n" +
                "Horoscope Sign: %s\n" +
                "Ruling Planet: %s\n" +
                "Moon Phase: %s\n" +
                "Location: %s (Lat: %.2f, Lon: %.2f)",
                birthDateString, zodiacSign, rulingPlanet, moonPhase, cityName, latitude, longitude
            ));

            moonImageView.setImage(new Image(getMoonPhaseImageUrl(moonPhase)));
        } catch (DateTimeParseException e) {
            showError("Invalid date format. Please enter the date in YYYY-MM-DD format.");
        } catch (Exception e) {
            showError("An error occurred: " + e.getMessage());
        }
    }

    private void resetFields() {
        birthDateField.clear();
        cityField.clear();
        resultArea.clear();
        moonImageView.setImage(null);
    }

    private double[] getCoordinatesFromCity(String city) throws Exception {
        String apiUrl = "https://api.opencagedata.com/geocode/v1/json?q=" + city + "&key=5a35326fa5a144959b7a985de8427faa"; // Use your API key
        String response = HttpRequestUtil.sendGet(apiUrl, API_ID, API_SECRET);

        // Parse response to extract latitude and longitude (simplified for demonstration)
        // Assume response contains a field for latitude and longitude

        // For simplicity, let's assume the response is something like:
        // {"results":[{"geometry":{"lat":40.7128,"lng":-74.0060}}]}
        
        // Example response parsing (replace with actual logic)
        double latitude = 40.7128;  // Example latitude (New York)
        double longitude = -74.0060;  // Example longitude (New York)

        return new double[]{latitude, longitude};
    }

    private String getMoonPhase(LocalDate date, double latitude, double longitude) throws Exception {
        String apiUrl = "https://api.astronomyapi.com/api/v2/bodies/positions/moon?latitude=" + latitude + "&longitude=" + longitude + "&from="
                + date.toString() + "&to=" + date.toString();
        String response = HttpRequestUtil.sendGet(apiUrl, API_ID, API_SECRET);

        // Parse the response to extract moon phase (simplified logic for demonstration)
        if (response.contains("Full Moon")) {
            return "Full Moon";
        } else if (response.contains("New Moon")) {
            return "New Moon";
        } else if (response.contains("First Quarter")) {
            return "First Quarter";
        } else if (response.contains("Last Quarter")) {
            return "Last Quarter";
        } else {
            return "Unknown";
        }
    }

    private String getMoonPhaseImageUrl(String moonPhase) {
        switch (moonPhase) {
            case "Full Moon":
                return "https://example.com/full_moon.jpg";
            case "New Moon":
                return "https://example.com/new_moon.jpg";
            case "First Quarter":
                return "https://example.com/first_quarter.jpg";
            case "Last Quarter":
                return "https://example.com/last_quarter.jpg";
            default:
                return "https://example.com/default_moon.jpg";
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

class HttpRequestUtil {
    public static String sendGet(String url, String appId, String appSecret) throws Exception {
        // Implement HTTP GET request with headers for API_ID and API_SECRET
        // Placeholder for demonstration purposes
        return "{\"data\": {\"moon_phase\": \"Full Moon\"}}";
    }
}
