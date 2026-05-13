package dk.delfinen.gruppe5.adapter.in;

import java.util.Scanner;

public class InputHandler {

    public int getInt(Scanner scanner, String prompt) {
        System.out.println(prompt);

        while (true) {
            String input = scanner.nextLine();

            if (isInteger(input)) {
                return Integer.parseInt(input);
            }

            System.out.println("Forkert input. Prøv igen:");
        }
    }

    public String getString(Scanner scanner, String prompt) {
        System.out.println(prompt);

        while (true) {
            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println("Du skal indtaste noget:");
        }
    }

    public boolean getBoolean(Scanner scanner, String prompt) {
        System.out.println(prompt + " (ja/nej)");

        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("ja")) {
                return true;
            } else if (input.equals("nej")) {
                return false;
            }


            System.out.println("Forkert input. Prøv igen:");
        }
    }

    private boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
