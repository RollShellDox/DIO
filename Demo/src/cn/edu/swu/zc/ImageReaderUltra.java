package cn.edu.swu.zc;

import java.io.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
public class ImageReaderUltra {
    public int download(String imageUrl,String directory) throws IOException {
        URL url = new URL(imageUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);

        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new IOException("Failed to download image: response code " + responseCode);
        }

        File file = createPath(imageUrl, directory);
        try (InputStream inputStream = connection.getInputStream();
             BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
             FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            throw new IOException("Failed to download image: " + e.getMessage());
        }

        return (int) file.length();
    }


    private File createPath(String imageUrl, String directory) throws IOException {
        //TODO:create directories and return file path
        String[] pathParts = imageUrl.split("/");
        String fileName = pathParts[pathParts.length - 1];
        File file = new File(directory, fileName);
        File parent = file.getParentFile();
        if (!parent.exists()) {
            parent.mkdirs();
        }
        return file;


    }
}
