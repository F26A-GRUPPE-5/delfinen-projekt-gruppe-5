package dk.delfinen.gruppe5.adapter.in;

import java.util.Map;
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
                System.out.println(e.getMessage() + "Prøv igen.");
            }
        }
    }

    public String getString(String prompt) {
        System.out.println(prompt);
        while (true) {
            try {
                return parser.parseString();
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage() + "Prøv igen.");
            }
        }
    }

    public Boolean getBoolean(String prompt) {
        System.out.println(prompt);
        while (true) {
            try {
                return parser.parseBoolean();
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage() + "Prøv igen.");
            }
        }
    }

    public boolean choose(String prompt, MenuOption[] choices) {
        System.out.println(prompt);
        System.out.println("--------------------------------------");

        for (int i = 0; i < choices.length; i++) {
            System.out.println("[" + i + "] - " + choices[i].getLabel());
        }

        System.out.println("--------------------------------------");

        while (true) {
            try {
                int index = parser.parseIntFromZeroUpToMax(choices.length - 1);
                choices[index].run();
                return choices[index].isExit();
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage() + "Prøv igen.");
            }
        }
    }

    public void chooseLooping(String prompt, MenuOption[] choices) {

        MenuOption exitOption = new MenuOption("Exit", () -> {}, true);

        MenuOption[] allChoices = new MenuOption[choices.length + 1];
        System.arraycopy(choices, 0, allChoices, 0, choices.length);
        allChoices[choices.length] = exitOption;

        while (true) {
            boolean shouldExit = choose(prompt, allChoices);
            if (shouldExit) break;
        }
    }
}
