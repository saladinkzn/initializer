package ru.shadam.initializer.plugin.gradle.config;

import java.util.Map;

/**
 *
 */
// TODO: Actually, extension is a property of Plugin.
public class Extension {
    private final String name;
    private final Map<String, String> options;

    public Extension(String name, Map<String, String> options) {
        this.name = name;
        this.options = options;
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getOptions() {
        return options;
    }

    public static String wrapOptionValue(String value) {
        if(value == null) {
            return "null";
        }
        return "\"" + value + "\"";
    }
}
