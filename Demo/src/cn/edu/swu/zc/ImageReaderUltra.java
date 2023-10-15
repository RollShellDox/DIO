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
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            throw new IOException("Failed to download image: " + e.getMessage());
        }

        return (int) file.length();
    }
//    在这个示例中，我们使用了Java的HttpURLConnection库来下载图片。我们首先创建一个HttpURLConnection对象，设置请求方法为"GET"，并将setDoOutput(true)以允许输出。然后，我们检查响应码，如果响应码不是"200 OK"，则抛出一个IOException。
//
//    接下来，我们使用BufferedInputStream和FileOutputStream将图片数据从输入流写入文件。最后，我们返回文件的大小作为下载的文件大小。
//
//    请注意，这个示例仅用于演示目的，实际应用中可能需要进行更多的错误处理和验证。

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

//        在这个示例中，我们首先将图片URL分割成路径部分，然后提取文件名。接下来，我们创建一个File对象，该对象表示要下载的图片文件。最后，我们检查父目录是否存在，如果不存在，则使用mkdirs()方法创建目录。
//
//        请注意，这个示例仅用于演示目的，实际应用中可能需要进行更多的错误处理和验证
    }
}
