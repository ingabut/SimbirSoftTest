package com.simbirsoft.utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CustomTools {

    public static final String FILE_PATH = "src/test/resources/transactions.csv";

    public static void systemSleep(double sec) {
        try {
            Thread.sleep((long) sec * 1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFile(String text) {
        try {
            FileOutputStream fos = new FileOutputStream(FILE_PATH);
            OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            BufferedWriter bf = new BufferedWriter(osw);
            bf.write(text);
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] toByteArray(File file) throws IOException {
        return Files.readAllBytes(Paths.get(file.getPath()));
    }
}
