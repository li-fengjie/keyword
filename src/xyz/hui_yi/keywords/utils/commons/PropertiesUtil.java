package xyz.hui_yi.keywords.utils.commons;

import java.io.*;
import java.util.Properties;

/**
 * @author li-fengjie
 * 2018.12.14
 */
public class PropertiesUtil {
    //参数为要修改的文件路径  以及要修改的属性名和属性值
	public static Boolean updatePro(String path,String key,String value){
		Properties prop = new Properties();// 属性集合对象
		FileInputStream fis;
		try {
			fis = new FileInputStream(path);
			prop.load(fis);// 将属性文件流装载到Properties对象中
			fis.close();// 关闭流
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		prop.setProperty(key, value);
		// 文件输出流
		try {
			FileOutputStream fos = new FileOutputStream(path);
            OutputStreamWriter oStreamWriter = new OutputStreamWriter(new FileOutputStream(path), "utf-8");
            // 将Properties集合保存到流中
            prop.store(oStreamWriter, "Copyright (c) Boxcode Studio");
			fos.close();// 关闭流
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		System.out.println("获取修改后的属性值：" + key + "=" + prop.getProperty(key));
		return true;
	}

	public static String getProperty(String path,String key){
        Properties prop = new Properties();// 属性集合对象
        FileInputStream fis;
        try {
            fis = new FileInputStream(path);
            prop.load(fis);// 将属性文件流装载到Properties对象中
            fis.close();// 关闭流
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return prop.getProperty(key);
    }
}