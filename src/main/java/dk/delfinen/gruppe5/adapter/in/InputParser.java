package dk.delfinen.gruppe5.adapter.in;
import java.util.Scanner;

public class InputParser {

    private Scanner scanner;

    public InputParser(Scanner scanner) {
        this.scanner = scanner;
    }

    public int parseInt() throws InvalidInputException {

        if (!scanner.hasNextInt()) {
            scanner.nextLine();
            throw new InvalidInputException("Du skrev ikke et heltal. ");
        }
        int returnValue = scanner.nextInt();
        scanner.nextLine();
        return returnValue;
    }

    public int parseIntFromZeroUpToMax(int max) throws InvalidInputException {

        int chosen = parseInt();
        if (chosen > max || chosen < 0) {
            scanner.nextLine();
            throw new InvalidInputException("Du skrev ikke et gyldigt tal. ");
        }
        return chosen;
    }


    public String parseString() throws InvalidInputException {
        String input = scanner.nextLine().trim();
        System.out.println("Input: '" + input + "' længde: " + input.length());

        if (input.isEmpty()) {
            throw new InvalidInputException("Input må ikke være tomt. ");
        }

        for (char c : input.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && c != ' ') {
                throw new InvalidInputException("Input indeholder ugyldige tegn. ");
            }
        }

        if (input.length() > 25) {
            throw new InvalidInputException("Input er for langt. ");
        }

        return input;
    }

    public boolean parseBoolean() throws InvalidInputException {
        String input = scanner.nextLine().trim().toLowerCase();

        if (input.equals("ja") || input.equals("j") || input.equals("y") || input.equals("yes") || input.equals("true")) {
            return true;
        }

        if (input.equals("nej") || input.equals("n") || input.equals("no") || input.equals("false")) {
            return false;
        }

        throw new InvalidInputException("Skriv ja eller nej.");
    }

    public double parseDouble() throws InvalidInputException {
        String input = scanner.nextLine().trim();
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Ugyldigt tal. ");
        }
    }
}

