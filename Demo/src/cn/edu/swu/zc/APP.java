package cn.edu.swu.zc;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;


public class APP {
    public static void main(String[] args) throws IOException {
        PageReaderUltra pageReaderUltra = new PageReaderUltra();
        List<String> urls = pageReaderUltra.getUrl("http://10.122.7.154/javaweb/data/images-url.txt");
        Map<Integer,String> sortMap = new TreeMap<>();
        for(String url:urls){
            ImageReaderUltra imageReaderUltra = new ImageReaderUltra();
//            Stream<String> s = sortMap.values().stream().filter("\\").
            Integer size = imageReaderUltra.download(url,"c:\\images\\"+url.substring(6,(url.length()-6)));
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
