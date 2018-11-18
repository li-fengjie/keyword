package xyz.hui_yi.keywords.utils.file;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FileType {

    private static Properties properties = null;

    static {
        try {
            InputStream inputStream = FileType.class.getClassLoader().getResourceAsStream("arrow_upload_filetype.properties");
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 根据扩展名验证上传文件是否属于pdf文件
     * @param fileName     filename.pdf
     * @return
     */
    public static boolean validateFileTypeByFileName(String fileName){
        if(fileName != null){
            String ext = fileName.substring(fileName.lastIndexOf('.')+1).toLowerCase();
            List<String> arrowType = new ArrayList<String>();
            for(Object key : properties.keySet()){
                String value = (String)properties.get(key);
                String[] values = value.split(",");
                for(String v : values){
                    arrowType.add(v.trim());
                }
            }
            return arrowType.contains(fileName.toLowerCase()) && properties.keySet().contains(ext);
        }else
            return false;
    }

    /**
     * 验证上传文件是否属于pdf 文件
     * @param contentType     application/pdf
     * @return
     */
    public static boolean validateFileTypeByMimeType(String contentType){
        if(contentType != null){
            List<String> arrowType = new ArrayList<String>();
            for(Object key : properties.keySet()){
                String value = (String)properties.get(key);
                arrowType.add(value);
            }
            return arrowType.contains(contentType);
        }else {
            return false;
        }
    }
}
