package tech.thatgravyboat.skycubed.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatcher {

    private final Matcher matcher;
    private final boolean found;

    public RegexMatcher(Pattern pattern, String text) {
        this.matcher = pattern.matcher(text);
        this.found = matcher.find();
    }

    public static RegexMatcher of(Pattern pattern, String text) {
        return new RegexMatcher(pattern, text);
    }

    public boolean found() {
        return found;
    }

    public String string(String group) {
        return matcher.group(group);
    }

    public int integer(String group) {
        return Integer.parseInt(string(group).replace(",", ""));
    }

    public double decimal(String group) {
        return Double.parseDouble(string(group).replace(",", ""));
    }

    public boolean bool(String group) {
        return Boolean.parseBoolean(string(group));
    }

    public String string(String group, String fallback) {
        try {
            return string(group);
        } catch (Exception e) {
            return fallback;
        }
    }

    public int integer(String group, int fallback) {
        try {
            return integer(group);
        } catch (Exception e) {
            return fallback;
        }
    }

    public double decimal(String group, double fallback) {
        try {
            return decimal(group);
        } catch (Exception e) {
            return fallback;
        }
    }

    public boolean bool(String group, boolean fallback) {
        try {
            return bool(group);
        } catch (Exception e) {
            return fallback;
        }
    }

    public String replace(String replacement) {
        return matcher.replaceAll(replacement);
    }

}
