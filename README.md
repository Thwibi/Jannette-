Functionality:

    Input:
        Accept a user's birth date in the format YYYY-MM-DD.

    Horoscope and Zodiac Sign:
        Determine the zodiac sign based on the birth date.
        Display the traits of the zodiac sign.
        Include the ruling planet of the zodiac sign.

    Compatibility:
        Provide zodiac compatibility information:
            Signs that are compatible.
            Signs that are less compatible or conflicting.

    Moon Phase:
        Calculate the moon phase for the birth date and display its description.
        Show an image of the moon corresponding to the phase.

    UI Features:
        Use a modern and user-friendly GUI:
            Use JavaFX for the interface.
            Include labels, input fields, buttons, and styled outputs.
        Add a custom program icon with the file venus.png to represent the planet Venus.

    Output Design:
        Display the results in a formatted layout:

        Birth Date: [YYYY-MM-DD]
        Horoscope Sign: [Sign]
        Traits: [Description]
        Ruling Planet: [Planet]
        Compatibility: 
          - Compatible Signs: [Signs]
          - Conflicting Signs: [Signs]
        Moon Phase: [Phase]

        Include the moon image and horoscope sign's visual symbol in the GUI.

    Data Source:
        Use built-in logic or a library for zodiac and horoscope determination.
        Use a reliable API or dataset to retrieve moon phase data.
        Provide a simple fallback for offline use (e.g., a predefined moon phase calculation method).

    Validation and Error Handling:
        Validate the entered birth date.
        Show a message box for invalid inputs.

    Enhancements:
        Use CSS for styling to create a modern look.
        Make the app responsive and compatible with most screen sizes.

Technology:

    Use JavaFX for the GUI.
    Include libraries like java.time.LocalDate for date handling.
    Suggest the use of APIs (e.g., Astro API for moon phases) 
