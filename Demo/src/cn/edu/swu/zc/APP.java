package cn.edu.swu.zc;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class APP {
    public static void main(String[] args) throws IOException {
        PageReaderUltra pageReaderUltra = new PageReaderUltra();
        List<String> urls = pageReaderUltra.getUrl("http://10.122.7.154/javaweb/data/images-url.txt");
        Map<Integer,String> sortMap = new TreeMap<>();
        for(String url:urls){
            ImageReaderUltra imageReaderUltra = new ImageReaderUltra();
            Integer size = imageReaderUltra.download(url,"c:\\images\\");
            sortMap.put(size,url);
        }

        //TODO:sorting with sortMap;
        List<Map.Entry<Integer, String>> sortedList = new ArrayList<>(sortMap.entrySet());
        sortedList.sort(Map.Entry.comparingByKey());
        //TODO:write sortMap to c:\images\images-sorted.txt
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("c:\\images\\images-sorted.txt"))) {
            for (Map.Entry<Integer, String> entry : sortedList) {
                writer.write(entry.getValue());
                writer.newLine();
            }
        }
    }
}
//首先，我们创建了一个名为APP的类，并在其中定义了一个main方法。
//        在main方法中，我们创建了一个PageReaderUltra对象，并使用其getUrl方法从给定的URL中获取URL列表。
//        接下来，我们创建了一个名为sortMap的TreeMap对象，用于存储图片的大小及其对应的URL。
//        然后，我们使用for-each循环遍历URL列表，对于每个URL，我们创建一个ImageReaderUltra对象，并使用其download方法下载图片。下载的图片文件大小将作为键，URL作为值添加到sortMap中。
//        最后，我们使用TreeMap的键（图片大小）对sortMap进行排序，并将排序后的结果写入到c:\images\images-sorted.txt文件中。

//在这个实现中，我们首先使用TreeMap的entrySet方法创建一个包含所有映射项的列表。
// 然后，我们使用Java 8的流API对列表进行排序，根据键（图片大小）进行排序。
// 最后，我们使用try-with-resources语句创建一个BufferedWriter对象，用于将排序后的映射项写入到images-sorted.txt文件中。