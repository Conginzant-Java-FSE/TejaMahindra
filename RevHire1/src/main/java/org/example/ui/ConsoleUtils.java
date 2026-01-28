package org.example.ui;

import java.util.Scanner;

public class ConsoleUtils {
    public static final Scanner scanner = new Scanner(System.in);

    public static String readString(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine().trim();
    }

    public static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt + ": ");
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    public static void printHeader(String title) {
        System.out.println("\n=== " + title + " ===");
    }

    public static void printSuccess(String message) {
        System.out.println("SUCCESS: " + message);
    }

    public static void printError(String message) {
        System.err.println("ERROR: " + message);
    }

    public static String readPassword(String prompt) {
        System.out.print(prompt + ": ");
        java.io.Console console = System.console();
        if (console != null) {
            char[] passwordChars = console.readPassword();
            return new String(passwordChars);
        } else {
            return scanner.nextLine().trim();
        }
    }
}
