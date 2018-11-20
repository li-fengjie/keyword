package test;

import xyz.hui_yi.keywords.utils.file.ReadFromFile;
import xyz.hui_yi.keywords.utils.findwords.CountWord;

public class Test4 {
    public static void main(String[] args) {
        String  str = "Java中 获取指定字符串在另一个字符串中出现的次数";
        String str1 = ReadFromFile.file2String("D:\\文件\\项目文档\\关键词\\java\\keyword\\src\\test\\三体.txt","gbk");
//        int count = CountWord.CountWord(str,"Java中 ");
        int count = CountWord.CountWord(str1,"地球");
        System.out.println(count);
    }
}
