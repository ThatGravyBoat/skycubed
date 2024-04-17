package tech.thatgravyboat.skycubed.utils;

import com.google.gson.JsonObject;
import com.teamresourceful.resourcefullib.common.utils.WebUtils;
import org.intellij.lang.annotations.Language;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class RepoPattern {

    private static final String URL = "https://raw.githubusercontent.com/ThatGravyBoat/SkyCubed/main/src/main/java/tech/thatgravyboat/skycubed/api/events/ActionBarCallback.java";
    private static final Map<String, String> PATTERNS = new HashMap<>();
    private static final Map<String, Pattern> COMPILED_PATTERNS = new HashMap<>();

    public static void init() {
        JsonObject json = WebUtils.getJson(URL, true);
        if (json == null) return;
        for (var entry : json.entrySet()) {
            PATTERNS.put(entry.getKey(), entry.getValue().getAsString());
        }
    }

    private static String getRegex(String key, @Language("RegExp") String fallback) {
        return PATTERNS.getOrDefault(key, fallback);
    }

    public static Pattern get(String key, @Language("RegExp") String fallback) {
        return COMPILED_PATTERNS.computeIfAbsent(key, k -> Pattern.compile(getRegex(k, fallback)));
    }

    public static Pattern get(String key, @Language("RegExp") String fallback, int flags) {
        return COMPILED_PATTERNS.computeIfAbsent(key, k -> Pattern.compile(getRegex(k, fallback), flags));
    }
}
