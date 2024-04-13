package tech.thatgravyboat.skycubed.utils;

import java.util.Locale;

public class NumberUtils {

    public static int toInt(String s) {
        if (s == null) return 0;
        s = s.trim();
        s = s.replace(",", "");
        s = s.replace("$", "");
        if (s.isEmpty()) return 0;
        s = s.toLowerCase(Locale.ROOT);
        char last = s.charAt(s.length() - 1);
        try {
            return switch (last) {
                case 'k' -> (int) (Float.parseFloat(s.substring(0, s.length() - 1)) * 1000);
                case 'm' -> (int) (Float.parseFloat(s.substring(0, s.length() - 1)) * 1000000);
                case 'b' -> (int) (Float.parseFloat(s.substring(0, s.length() - 1)) * 1000000000);
                default -> (int) Float.parseFloat(s);
            };
        }catch (NumberFormatException e){
            return 0;
        }
    }
}
