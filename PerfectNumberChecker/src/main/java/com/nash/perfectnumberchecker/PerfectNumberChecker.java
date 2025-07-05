/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.nash.perfectnumberchecker;
import java.util.Scanner;
/**
 *
 * @author nashvybzes
 */
public class PerfectNumberChecker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = getValidInput(scanner);
        
        if (isPerfectNumber(number)) {
            System.out.println(number + " is a perfect number.");
        } else {
            System.out.println(number + " is not a perfect number.");
        }
        
        scanner.close();
    }

    public static int getValidInput(Scanner scanner) {
        int number = 0;
        while (true) {
            try {
                System.out.print("Enter a positive integer: ");
                number = Integer.parseInt(scanner.nextLine());
                if (number > 0) break;
                System.out.println("Please enter a positive integer.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
        return number;
    }

    public static boolean isPerfectNumber(int number) {
        if (number <= 1) {
            return false;
        }
        
        int sum = 1; // 1 is a proper divisor for all numbers > 1
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                sum += i;
                int complement = number / i;
                if (complement != i) {
                    sum += complement;
                }
            }
        }
        return sum == number;
    }
}
