package com.epam.mjc.nio;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReader {

    public Profile getDataFromFile(File file) {
        StringBuilder fileContent = new StringBuilder();

        try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {
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
            switch (key) {
                case "Name":
                    name = value;
                    break;
                case "Age":
                    age = Integer.parseInt(value);
                    break;
                case "Email":
                    email = value;
                    break;
                case "Phone":
                    phone = Long.parseLong(value);
                    break;
            }
        }

        return new Profile(name, age, email, phone);
    }
}
