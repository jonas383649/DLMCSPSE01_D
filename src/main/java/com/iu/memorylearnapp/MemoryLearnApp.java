package com.iu.memorylearnapp;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MemoryLearnApp {
    public static void main(String[] args) {
        Application.launch(MemoryLearnUI.class, args);
    }
}