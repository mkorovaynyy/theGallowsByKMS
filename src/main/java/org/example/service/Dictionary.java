package org.example.service;

import org.example.exception.FileNotFoundExceptionInZip;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

//Создаем словарь - не очень уверен (или очень не уверен скорее даже), что класс верно спроектировал.
//В классе эксперементирую, интересно было прочитать файлы из сети, т.к. ранее не делал этого. Развивать идею
// и давать возможность сразу указать свой путь url не стал, хотя через сеттер и это возможно
// Методы постарался разбить, учитывая логику: 1 метод = 1 действие

public class Dictionary {
    private String zipUrlForDictionary = "http://blog.kislenko.net/archives/8381.zip";
    private String targetFileName = "singular.txt";


    // парсим строку в словарь в виде List<String>
    public List<String> getDictionary() throws IOException, FileNotFoundExceptionInZip {
        String line = getLineFromZipStream();
        if (line.isEmpty()) {
            throw new FileNotFoundExceptionInZip("Файл " + targetFileName + " не найден в архиве.");
        }
        return Arrays.asList(line.split("\n"));
    }

    // скачиваем zip и упаковываем в стрим зипов
    private ZipInputStream downloadZipUrl() throws IOException {
        try {
            URL url = new URL(zipUrlForDictionary);
            InputStream inputStream = url.openStream();
            return new ZipInputStream(inputStream);
        } catch (IOException e) {
            throw new IOException("Не удалось подключиться к URL: " + zipUrlForDictionary, e);
        }
    }

    // превращаем стрим зипов в строку
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
        return ""; // Если файл не найден, возвращаем пустую строку
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Dictionary that = (Dictionary) o;
        return Objects.equals(zipUrlForDictionary, that.zipUrlForDictionary) && Objects.equals(targetFileName, that.targetFileName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zipUrlForDictionary, targetFileName);
    }

    @Override
    public String toString() {
        return "Dictionary{" +
                "zipUrlForDictionary='" + zipUrlForDictionary + '\'' +
                ", targetFileName='" + targetFileName + '\'' +
                '}';
    }
}






