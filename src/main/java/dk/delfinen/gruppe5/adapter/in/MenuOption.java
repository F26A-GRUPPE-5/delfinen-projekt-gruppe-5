package dk.delfinen.gruppe5.adapter.in;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuOption {

    private final actions;
    private final Scanner scanner;

    public Menu(Map<String, Runnable> actions, Scanner scanner) {
        this.actions = actions;
        this.scanner = scanner;
    }
}