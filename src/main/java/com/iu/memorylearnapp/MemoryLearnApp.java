package com.iu.memorylearnapp;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main entry point for the Memory Learn application.
 *
 * <p>This class serves as the entry point to initialize Spring Boot and to launch the JavaFX application.</p>
 */
@SpringBootApplication
public class MemoryLearnApp {
    public static void main(String[] args) {
        Application.launch(MemoryLearnUI.class, args);
    }
}