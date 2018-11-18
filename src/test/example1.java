package test;

import xyz.hui_yi.keywords.utils.file.ReadFromFile;
import xyz.hui_yi.keywords.utils.TextRank4Keyword.TextRank4Keyword;
import xyz.hui_yi.keywords.utils.TextRank4Keyword.TextRank4Sentence;

import java.util.Map;

/**
 * @author Li-Fengjie
 *
 */
public class example1 {
    public static void main(String[] args) {
        String fileName = "src/test/doc/01.txt";
        Map<String, String> keyWords;
        String readResult;
        readResult = ReadFromFile.file2String(fileName,"utf8");
        //logger.info(readResult);

        //关键词
        TextRank4Keyword tr4k = new TextRank4Keyword();
        tr4k.analyse(readResult, 2);
        keyWords = tr4k.get_keywords(10,1);
        for (String  key : keyWords.keySet()) {
            String value = keyWords.get(key);
        }

        //摘要
        TextRank4Sentence tr4s = new TextRank4Sentence();
        tr4s.analyse(readResult, 2);
        keyWords = tr4s.get_keysentences(3,6);
        for (String  key : keyWords.keySet()) {
            String value = keyWords.get(key);
        }

    }
}
