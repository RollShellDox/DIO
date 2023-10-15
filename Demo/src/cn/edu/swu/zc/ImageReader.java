package cn.edu.swu.zc;

import java.io.File;

public class ImageReader {
    public int download(String imageUrl,String directory){
        File file = this.createPath(imageUrl, directory);

        //TODO:download and save image to file and return the download file size

        return  0;
    }

    private File createPath(String imageUrl,String directory){
        //TODO:create directories and return file path
        return null;
    }
}
