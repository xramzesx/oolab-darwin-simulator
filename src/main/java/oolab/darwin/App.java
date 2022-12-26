package oolab.darwin;

import javafx.application.Application;
import oolab.darwin.gui.ConfigView;

public class App{
    public static void main(String[] args) {
        try {
            Application.launch(ConfigView.class, args);
        } catch (Exception e) {
            System.out.println("\nAn exception occur: " + e.getMessage() + "\n");
        }
    }
}
