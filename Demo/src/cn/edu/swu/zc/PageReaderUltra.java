package cn.edu.swu.zc;

import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PageReaderUltra {
    public List<String> getUrl(String pageUrl) {
        List<String> urlList = new ArrayList<>();
        try {
            URL url = new URL(pageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                urlList.add(line);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return urlList;
    }
}

//在这个示例中，我们首先创建了一个PageReader类，并在其中定义了一个getUrl方法。这个方法接受一个网页URL作为参数，并返回一个包含该网页上所有URL的列表。
//
//        在getUrl方法中，我们使用java.net.URL类创建一个URL对象，然后使用HttpURLConnection类打开一个连接。接下来，我们设置请求方法为"GET"，连接到URL并获取输入流。最后，我们使用BufferedReader从输入流中读取每一行，并将其添加到URL列表中。最后，我们关闭BufferedReader并返回URL列表。
//
//        请注意，这个示例仅适用于简单的网页，因为它只读取输入流中的每一行并将其添加到URL列表中。对于更复杂的情况，可能需要使用其他库（如Jsoup或Selenium）来处理HTML和XML内容。