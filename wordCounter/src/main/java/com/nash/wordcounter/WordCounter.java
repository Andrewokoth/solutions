/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.nash.wordcounter;
import java.util.Scanner;
/**
 *
 * @author nashvybzes
 */
public class WordCounter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a book description:");
        String description = scanner.nextLine();
        
        String wordToCount = "Uganda";
        String[] words = description.split("\\s+");
        int count = 0;

        for (String word : words) {
            if (word.equalsIgnoreCase(wordToCount)) {
                count++;
            }
        }

        System.out.println("The word 'Uganda' appears " + count + " times.");
        scanner.close();
    }
}
