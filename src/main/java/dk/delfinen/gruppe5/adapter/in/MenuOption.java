package dk.delfinen.gruppe5.adapter.in;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuOption {
    private final String label;
    private final Runnable action;
    private final boolean isExit;

    public MenuOption(String label, Runnable action) {
        this(label, action, false);
    }

    public MenuOption(String label, Runnable action, boolean isExit) {
        this.label = label;
        this.action = action;
        this.isExit = isExit;
    }

    public String getLabel() { return label; }
    public void run() { action.run(); }
    public boolean isExit() { return isExit; }
}