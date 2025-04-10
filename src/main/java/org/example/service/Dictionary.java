package org.example.service;

import org.example.exception.FileNotFoundExceptionInZip;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Dictionary {
    private String zipUrlForDictionary;
    private String targetFileName;


    public Dictionary(String configFilePath) throws IOException {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(configFilePath)) {
            if (input == null) {
                throw new IOException("Конфигурационный файл не найден: " + configFilePath);
            }
            properties.load(input);
        }
        this.zipUrlForDictionary = properties.getProperty("zipUrlForDictionary");
        this.targetFileName = properties.getProperty("targetFileName");
    }


    public List<String> getDictionary() throws IOException, FileNotFoundExceptionInZip {
        String line = getLineFromZipStream();
        if (line.isEmpty()) {
            throw new FileNotFoundExceptionInZip("Файл " + targetFileName + " не найден в архиве.");
        }
        return Arrays.asList(line.split("\n"));
    }


    private ZipInputStream downloadZipUrl() throws IOException {
        try {
            URL url = new URL(zipUrlForDictionary);
            InputStream inputStream = url.openStream();
            return new ZipInputStream(inputStream);
        } catch (IOException e) {
            throw new IOException("Не удалось подключиться к URL: " + zipUrlForDictionary, e);
        }
    }


    private String getLineFromZipStream() throws IOException {
        try (ZipInputStream zipInputStream = downloadZipUrl()) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                if (entry.getName().equals(targetFileName)) {
                    try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                        byte[] buffer = new byte[1024];
                        int length;
                        while ((length = zipInputStream.read(buffer)) > 0) {
                            outputStream.write(buffer, 0, length);
                        }
                        return outputStream.toString();
                    }
                }
            }
        }
        return "";
    }

    public String getZipUrlForDictionary() {
        return zipUrlForDictionary;
    }

    public void setZipUrlForDictionary(String zipUrlForDictionary) {
        this.zipUrlForDictionary = zipUrlForDictionary;
    }

    public String getTargetFileName() {
        return targetFileName;
    }

    public void setTargetFileName(String targetFileName) {
        this.targetFileName = targetFileName;
    }

}






