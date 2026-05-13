package dk.delfinen.gruppe5.adapter.in;

import java.util.Scanner;

public class InputHandler {

    private final InputParser parser;

    public InputHandler(Scanner scanner) {
        this.parser = new InputParser(scanner);
    }

    public int getInt(String prompt) {
        System.out.println(prompt);
        while (true) {
            try {
                return parser.parseInt();
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage() + " prøv igen.");
            }
        }
    }
}
