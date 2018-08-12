package io.autor.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Stephan Grundner
 */
public class StringUtils {

    public static String findFirst(String text, String regex, int group) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        if (matcher.matches()) {
            return matcher.group(group);
        }

        return null;
    }

    public static String findFirst(String text, String regex) {
        return findFirst(text, regex, 1);
    }
}
