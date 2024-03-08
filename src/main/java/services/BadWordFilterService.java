package services;

import java.util.Set;
import java.util.Arrays;
import java.util.HashSet;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class BadWordFilterService {

    private Set<String> badWords;
    private Pattern pattern;

    public BadWordFilterService(String[] badWords) {
        this.badWords = new HashSet<>(Arrays.asList(badWords));
        String patternString = "(" + String.join("|", badWords) + ")";
        this.pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
    }

    public boolean hasBadWord(String text) {
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }
}
