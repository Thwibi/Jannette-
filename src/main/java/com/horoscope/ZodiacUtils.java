package com.horoscope;

import java.time.LocalDate;

public class ZodiacUtils {

    /**
     * Get the Zodiac sign based on the birth date.
     *
     * @param date The birth date.
     * @return The Zodiac sign.
     */
    public static String getZodiacSign(LocalDate date) {
        if (isWithinRange(date, 3, 21, 4, 19)) return "Aries";
        if (isWithinRange(date, 4, 20, 5, 20)) return "Taurus";
        if (isWithinRange(date, 5, 21, 6, 20)) return "Gemini";
        if (isWithinRange(date, 6, 21, 7, 22)) return "Cancer";
        if (isWithinRange(date, 7, 23, 8, 22)) return "Leo";
        if (isWithinRange(date, 8, 23, 9, 22)) return "Virgo";
        if (isWithinRange(date, 9, 23, 10, 22)) return "Libra";
        if (isWithinRange(date, 10, 23, 11, 21)) return "Scorpio";
        if (isWithinRange(date, 11, 22, 12, 21)) return "Sagittarius";
        if (isWithinRange(date, 12, 22, 1, 19)) return "Capricorn";
        if (isWithinRange(date, 1, 20, 2, 18)) return "Aquarius";
        if (isWithinRange(date, 2, 19, 3, 20)) return "Pisces";
        return "Unknown";
    }

    /**
     * Helper function to check if a date falls within a range.
     */
    private static boolean isWithinRange(LocalDate date, int startMonth, int startDay, int endMonth, int endDay) {
        int year = date.getYear();
        LocalDate startDate = LocalDate.of(year, startMonth, startDay);
        LocalDate endDate = (endMonth < startMonth) ?
                LocalDate.of(year + 1, endMonth, endDay) : LocalDate.of(year, endMonth, endDay);

        return (date.isEqual(startDate) || date.isAfter(startDate)) && (date.isEqual(endDate) || date.isBefore(endDate));
    }

    /**
     * Get the ruling planet based on the Zodiac sign.
     *
     * @param zodiacSign The Zodiac sign.
     * @return The ruling planet.
     */
    public static String getRulingPlanet(String zodiacSign) {
        switch (zodiacSign) {
            case "Aries":
                return "Mars";
            case "Taurus":
                return "Venus";
            case "Gemini":
                return "Mercury";
            case "Cancer":
                return "Moon";
            case "Leo":
                return "Sun";
            case "Virgo":
                return "Mercury";
            case "Libra":
                return "Venus";
            case "Scorpio":
                return "Pluto (Modern) / Mars (Traditional)";
            case "Sagittarius":
                return "Jupiter";
            case "Capricorn":
                return "Saturn";
            case "Aquarius":
                return "Uranus (Modern) / Saturn (Traditional)";
            case "Pisces":
                return "Neptune (Modern) / Jupiter (Traditional)";
            default:
                return "Unknown";
        }
    }
}
