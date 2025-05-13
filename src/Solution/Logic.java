package Solution;

import java.io.Serializable;

/**
 * Handles the business logic for calculating reading grade level using the Flesch-Kincaid formula.
 * Implements Serializable to support saving and loading analysis data.
 */
public class Logic implements Serializable 
{
    private static final long serialVersionUID = 1L;

    /**
     * Calculates the reading grade level of the input text using Flesch-Kincaid formula.
     * 
     * @param text The input text to analyze
     * @return The calculated grade level description
     * @throws IllegalArgumentException If input text is null or empty
     */
    public String calculateGradeLevel(String text) throws IllegalArgumentException 
    {
        if (text == null || text.trim().isEmpty()) 
        {
            throw new IllegalArgumentException("Input text cannot be null or empty");
        }

        // Initialize counters
        int wordCount = 0;
        int sentenceCount = 0;
        int syllableCount = 0;

        // Split text into sentences using punctuation as delimiters
        String[] sentences = text.split("[.!?]+");
        sentenceCount = sentences.length;

        // Process each sentence
        for (String sentence : sentences) 
        {
            // Split sentence into words and count syllables
            String[] words = sentence.trim().split("\\s+");
            
            // Filter out empty strings from split
            for (String word : words) 
            {
                if (!word.isEmpty()) 
                {
                    wordCount++;
                    syllableCount += countSyllables(word);
                }
            }
        }

        // Handle edge cases to prevent division by zero
        if (wordCount == 0 || sentenceCount == 0) 
        {
            return "Before Grade 1";
        }

        // Calculate Flesch-Kincaid Grade Level
        double gradeLevel = 0.39 * (wordCount / (double) sentenceCount)
                         + 11.8 * (syllableCount / (double) wordCount)
                         - 15.59;

        return getGradeLevelDescription(gradeLevel);
    }

    /**
     * Counts syllables in a word using vowel grouping approach.
     * 
     * @param word The word to analyze (case insensitive)
     * @return Number of syllables (minimum 1)
     */
    int countSyllables(String word) 
    {
        if (word == null || word.isEmpty()) 
        {
            return 1;
        }

        word = word.toLowerCase();
        int syllables = 0;
        boolean lastWasVowel = false;

        // Count vowel groups
        for (int i = 0; i < word.length(); i++) 
        {
            char c = word.charAt(i);
            boolean isVowel = isVowel(c);

            // Count each new vowel group
            if (isVowel && !lastWasVowel) 
            {
                syllables++;
            }
            lastWasVowel = isVowel;
        }

        // Adjust for silent e at end (heuristic)
        if (word.endsWith("e") && syllables > 1) 
        {
            syllables--;
        }

        // Ensure at least one syllable per word
        return Math.max(syllables, 1);
    }

    /**
     * Checks if a character is a vowel (including y).
     * Case-insensitive check.
     * 
     * @param c The character to check
     * @return True if vowel, false otherwise
     */
    boolean isVowel(char c) 
    {
        // Convert to lowercase for case-insensitive check
        char lowerC = Character.toLowerCase(c);
        return lowerC == 'a' || lowerC == 'e' || lowerC == 'i' 
                || lowerC == 'o' || lowerC == 'u' || lowerC == 'y';
    }

    /**
     * Converts numeric grade level to descriptive string.
     * 
     * @param gradeLevel The calculated grade level
     * @return Descriptive grade level string
     */
    String getGradeLevelDescription(double gradeLevel) 
    {
        if (gradeLevel < 1) 
        {
            return "Before Grade 1";
        }
        else if (gradeLevel < 2) 
        {
            return "Grade 1";
        }
        else if (gradeLevel < 3) 
        {
            return "Grade 2";
        }
        else if (gradeLevel < 4) 
        {
            return "Grade 3";
        }
        else if (gradeLevel < 5) 
        {
            return "Grade 4";
        }
        else if (gradeLevel < 6) 
        {
            return "Grade 5";
        }
        else if (gradeLevel < 7) 
        {
            return "Grade 6";
        }
        else if (gradeLevel < 8) 
        {
            return "Grade 7";
        }
        else if (gradeLevel < 9) 
        {
            return "Grade 8";
        }
        else if (gradeLevel < 10) 
        {
            return "Grade 9";
        }
        else if (gradeLevel < 11) 
        {
            return "Grade 10";
        }
        else if (gradeLevel < 12) 
        {
            return "Grade 11";
        }
        else if (gradeLevel < 13) 
        {
            return "Grade 12";
        }
        else if (gradeLevel < 14) 
        {
            return "College Level 1";
        }
        else if (gradeLevel < 15) 
        {
            return "College Level 2";
        }
        else if (gradeLevel < 16) 
        {
            return "College Level 3";
        }
        else 
        {
            return "Graduate Level";
        }
    }
}