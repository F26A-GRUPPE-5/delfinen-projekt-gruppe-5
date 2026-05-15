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
            throw new InvalidInputException("Du skrev ikke et heltal.");
        }
        int returnValue = scanner.nextInt();
        scanner.nextLine();
        return returnValue;
    }

    public String parseString() throws InvalidInputException {
        String input = scanner.nextLine().trim();
        System.out.println("Input: '" + input + "' længde: " + input.length());

        if (input.isEmpty()) {
            throw new InvalidInputException("Input må ikke være tomt.");
        }

        for (char c : input.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && c != ' ') {
                throw new InvalidInputException("Input indeholder ugyldige tegn.");
            }
        }

        if (input.length() > 25) {
            throw new InvalidInputException("Input er for langt.");
        }

        return input;
    }

    public boolean parseBoolean() throws InvalidInputException {
        String input = scanner.nextLine().trim();

        if (input.isEmpty() || input.equals("måske") || input.equals("1")) {
            scanner.nextLine();
            return true;
        }

        throw new InvalidInputException("Ugyldigt input.");

    }
}
