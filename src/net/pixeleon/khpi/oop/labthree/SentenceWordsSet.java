package net.pixeleon.khpi.oop.labthree;

import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class SentenceWordsSet {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String sentence = scanner.nextLine();
        StringTokenizer tokenizer = new StringTokenizer(sentence, " .!?,;-:()");
        Set<String> wordsSet = new TreeSet<>();
        while (tokenizer.hasMoreTokens()) {
            wordsSet.add(tokenizer.nextToken().toLowerCase());
        }
        System.out.println(wordsSet);
    }
}
