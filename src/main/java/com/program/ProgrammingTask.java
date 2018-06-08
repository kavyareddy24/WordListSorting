package com.program;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 1) Alphabetical list (with counts in parentheses)
 * 2) Frequency list (with counts in parentheses)
 */
public class ProgrammingTask {

    // Regular expression for alpha numeric characters
    private static final String ALPHA_NUMERIC_REGEX = "[^a-zA-Z0-9]+";
    private static final String SPACE = " ";

    public static void main(String[] args) {
        String input = "This is a test input for a program:22 $$ history, one two#two or, three*three*three four!four!four!four!";
        ProgrammingTask task = new ProgrammingTask();
        task.processData(input);
    }

    /**
     * Core logic of the program that takes input string and displays 
     * 1) Alphabetical list (with counts in parentheses)
     * 2) Frequency list (with counts in parentheses)
     * 
     * @param input
     */
    public void processData(String input) {

        // Remove Special characters from input string
        input = removeSpecialChars(input);

        // Convert string to individual words
        String[] words = input.split(" ");

        System.out.println("=====Alphabetical list (with counts in parentheses)=====");

        // Sort by words
        Map<String, Integer> wordMap = sortByWords(words);
        for (String word : wordMap.keySet()) {
            System.out.println(word + " (" + wordMap.get(word) + ")");
        }

        System.out.println("\n=====Frequency list (with counts in parentheses)=====\n");

        // Sort by frequency
        List<Map.Entry<String, Integer>> list = sortByFrequency(wordMap);

        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(entry.getKey() + " (" + entry.getValue() + ")");
        }
    }

    /**
     * Logic to perform - Alphabetical list (with counts in parentheses)
     * 
     * @param words
     * @return
     */
    private Map<String, Integer> sortByWords(String[] words) {
        // TreeMap with key as the input word and value as the number of times
        // the word occurred
        Map<String, Integer> wordMap = new TreeMap<String, Integer>();

        for (String word : words) {
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }
        return wordMap;
    }

    /**
     * Logic to perform - Frequency list (with counts in parentheses)
     * 
     * @param wordMap
     * @return
     */
    private List<Map.Entry<String, Integer>> sortByFrequency(Map<String, Integer> wordMap) {
        // Convert the map to an array list
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(wordMap.entrySet());
        // Sort the list based on the frequency of each word using ValueThenKeyComparator
        Collections.sort(list, new ValueThenKeyComparator<String, Integer>());
        return list;
    }

    /**
     * Remove special characters from input data
     * 
     * @param input
     * @return
     */
    private String removeSpecialChars(String input) {
        if (input != null) {
            input = input.replaceAll(ALPHA_NUMERIC_REGEX, SPACE);
        }
        return input;
    }
}