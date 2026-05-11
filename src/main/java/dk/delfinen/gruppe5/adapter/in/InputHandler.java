package dk.delfinen.gruppe5.adapter.in;

import java.util.Scanner;

public class InputHandler {

    public int getInt(Scanner scanner, String promt) {
        System.out.println(promt);
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("Forkert input ");
        }
        return scanner.nextInt();

    }
}
