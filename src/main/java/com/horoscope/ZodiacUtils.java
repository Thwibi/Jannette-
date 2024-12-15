package com.horoscope;

import java.time.LocalDate;

public class ZodiacUtils {

    public static String getZodiacSign(int day, int month) {
        switch (month) {
            case 1:  return (day <= 19) ? "Capricorn" : "Aquarius";
            case 2:  return (day <= 18) ? "Aquarius" : "Pisces";
            case 3:  return (day <= 20) ? "Pisces" : "Aries";
            case 4:  return (day <= 19) ? "Aries" : "Taurus";
            case 5:  return (day <= 20) ? "Taurus" : "Gemini";
            case 6:  return (day <= 20) ? "Gemini" : "Cancer";
            case 7:  return (day <= 22) ? "Cancer" : "Leo";
            case 8:  return (day <= 22) ? "Leo" : "Virgo";
            case 9:  return (day <= 22) ? "Virgo" : "Libra";
            case 10: return (day <= 22) ? "Libra" : "Scorpio";
            case 11: return (day <= 21) ? "Scorpio" : "Sagittarius";
            case 12: return (day <= 21) ? "Sagittarius" : "Capricorn";
            default: return "Unknown";
        }
    }

    public static String getZodiacElement(String zodiacSign) {
        switch (zodiacSign) {
            case "Aries":
            case "Leo":
            case "Sagittarius":
                return "Fire";
            case "Taurus":
            case "Virgo":
            case "Capricorn":
                return "Earth";
            case "Gemini":
            case "Libra":
            case "Aquarius":
                return "Air";
            case "Cancer":
            case "Scorpio":
            case "Pisces":
                return "Water";
            default:
                return "Unknown";
        }
    }

    public static String getZodiacSymbol(String zodiacSign) {
        switch (zodiacSign) {
            case "Aries":        return "♈";
            case "Taurus":       return "♉";
            case "Gemini":       return "♊";
            case "Cancer":       return "♋";
            case "Leo":          return "♌";
            case "Virgo":        return "♍";
            case "Libra":        return "♎";
            case "Scorpio":      return "♏";
            case "Sagittarius":  return "♐";
            case "Capricorn":    return "♑";
            case "Aquarius":     return "♒";
            case "Pisces":       return "♓";
            default:             return "?";
        }
    }
}
