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
}