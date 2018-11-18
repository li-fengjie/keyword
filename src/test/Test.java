package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.apache.pdfbox.io.RandomAccessBuffer;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;


import org.apache.pdfbox.text.PDFTextStripper;

/* 2017.11.24---》 2017.11.27更新内容
* 解析pdf文件中的编号
* */
public class Test {
    private static String result = null;// 用来保存pdf文件中的信息
    private static FileInputStream is = null;// 输入流
    private static PDDocument document = null;

    //获取pdf文件文件中全部信息
    public static String getAllInfoFromPDF(String pdfFilePath) {
        String result = null;
        FileInputStream is = null;
        PDDocument document = null;
        try {
            is = new FileInputStream(pdfFilePath);
            RandomAccessRead read = new RandomAccessBuffer(is);
            PDFParser parser = new PDFParser(read);
            parser.parse();
            document = parser.getPDDocument();
            PDFTextStripper stripper = new PDFTextStripper();
            result = stripper.getText(document);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (document != null) {
                try {
                    document.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
 /*
* 通过递归得到某一路径下所有的目录及其PDF文件
*/
// 通过传入文件路径获取文件
        String code = null;
        File file = new File("D:\\pdf文件解析");
// 把获取到的文件名保存在数组中
        File[] files = file.listFiles();
        for (File f : files) {
            //--------------------------1.获取文件路径
            f.getAbsolutePath();
            System.out.println("文件路径:" + f.getAbsolutePath());
            //获取文件类型，即文件后缀名
            int start = f.getAbsolutePath().length() - 3;
            int end = f.getAbsolutePath().length();
            //得到文件的后缀名
            String pdf = f.getAbsolutePath().substring(start, end);
            //判断是否是pdf格式的文件
            if (pdf.equals("pdf") || pdf.equals("PDF")) {
                // 是pdf格式的文件
                //得到全部pdf文件中的信息
                String str = getAllInfoFromPDF(f.getAbsolutePath());
//                String str = getInfoFromPDF3.getAllInfoFromPDF(f.getAbsolutePath());
                //----------------------2.获取pdf文件中的编号
//  String code = str.substring(str.indexOf("：")+1,str.indexOf("：")+27);
                //截取pdf文件中的编号
//                code = str.substring(str.indexOf("编号：") + 3, str.indexOf("：") + 27);
 /*
 * 2017.11.27更新内容
 * 取出文本中的若干个编号，并去除重复的
 *
 * */
///////////////////2017.11.27更新内容/////////////////////////////////////////
                //获取到全部的编号
                Set set = new HashSet();
                Iterator<String> it = null;
                String regex = "\\d{26}";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(str);
                while (matcher.find()) {
                    set.add(matcher.group());
                    it = set.iterator();
                    //System.out.println(matcher.group());
                }
                while (it.hasNext()) {
                    String getCode = it.next();
                    System.out.println("编号为：" + getCode);
                }
/////////////////////////////////////////////////////////////
                //----------------------3.获取pdf 文件中的套数(一份申请书和一份授权书为一套)
                int count = 0, StringStart = 0;
                while (str.indexOf("金融信用信息基础数据库个人信用信息采集授权书", StringStart) >= 0 && StringStart < str.length()) {
                    //当字符串出现子串时，返回子字符串索引
                    count++;
                    StringStart = str.indexOf("金融信用信息基础数据库个人信用信息采集授权书", StringStart) + "金融信用信息基础数据库个人信用信息采集授权书".length();//得到新的start值。
                }
                System.out.println("共有--" + count + "--套");
                System.out.println("---------------------------------------------------------------------");
            }
        }
    }
}