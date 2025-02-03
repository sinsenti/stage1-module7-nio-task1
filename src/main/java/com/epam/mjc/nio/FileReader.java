package com.epam.mjc.nio;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;


public class FileReader {

    public Profile getDataFromFile(File file) {
        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        String content = fileContent.toString();
        String[] lines = content.split("\n");
        String name = "";
        int age = 0;
        String email = "";
        long phone = 0;
        for (String line : lines) {
            String[] parts = line.split(":", 2);
            String key = parts[0].trim();
            String value = parts[1].trim();
            if (key.equals("Name")) {
                name = value;
            } else if (key.equals("Age")) {
                age = Integer.parseInt(value);
            } else if (key.equals("Email")) {
                email = value;
            } else if (key.equals("Phone")) {
                phone = Long.parseLong(value);
            }
        }
        return new Profile(name, age, email, phone);

    }
}
