@FXML
private TextField dateField; // Input for birthdate (yyyy-mm-dd format)

@FXML
private Label zodiacSignLabel; // To display the zodiac sign

@FXML
private Label zodiacElementLabel; // To display the zodiac element

@FXML
private Label zodiacSymbolLabel; // To display the zodiac symbol

@FXML
public void calculateZodiacSign() {
    try {
        String[] dateParts = dateField.getText().split("-");
        int year = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int day = Integer.parseInt(dateParts[2]);

        String zodiacSign = ZodiacUtils.getZodiacSign(day, month);
        zodiacSignLabel.setText("Zodiac Sign: " + zodiacSign);
        zodiacElementLabel.setText("Element: " + ZodiacUtils.getZodiacElement(zodiacSign));
        zodiacSymbolLabel.setText("Symbol: " + ZodiacUtils.getZodiacSymbol(zodiacSign));

    } catch (Exception e) {
        zodiacSignLabel.setText("Error: Invalid date format. Use yyyy-mm-dd.");
    }
}
