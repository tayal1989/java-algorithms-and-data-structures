package com.learning.practise;

import java.util.HashSet;
import java.util.Set;

public class InterviewPractise {
    public static void main(String[] args) {
        String str = "hackerearth";
        findDuplicateCharacter(str);
        swapTwoString("Hello World");
    }

    public static void findDuplicateCharacter(String str) {
        String duplicateChar = "";
        String originalChar = "";
        Set<String> set = new HashSet<>();

        for(int i = 0; i < str.length(); i++) {
            String letter = Character.toString(str.charAt(i));
            if(set.contains(letter)) {
                duplicateChar = duplicateChar + letter + " ";
            } else {
                set.add(letter);
                originalChar = originalChar + letter;
            }
        }

        System.out.println("Original Characters : " + originalChar);
        System.out.println("Duplicate Characters : " + duplicateChar);
    }

    public static void swapTwoString(String str) {
        String[] fullWord = str.split(" ");
        String firstWord = fullWord[0];
        String secondWord = fullWord[1];

        firstWord = firstWord + secondWord;
        secondWord = firstWord.substring(0, firstWord.length() - secondWord.length());
        firstWord = firstWord.substring(secondWord.length());

        System.out.println("Second word becomes : " + secondWord);
        System.out.println("First word becomes : " + firstWord);
    }
}
