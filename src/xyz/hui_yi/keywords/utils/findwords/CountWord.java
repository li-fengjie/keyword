package xyz.hui_yi.keywords.utils.findwords;

/**
 * @author li-fengjie
 * 统计文本个数
 */
public class CountWord {
    /**
     * @param text 查找的文本内容
     * @param word 需要统计的单词
     * @return 单词数量
     */
    public static int CountWord(String text,String word){
        int count = 0;
        int index = 0;
        while ((index = text.indexOf(word, index)) != -1) {
            index = index + word.length();
            count++;
        }
        return count;
    }

    /**
     * 判断文本中是否存在目标字符
     * @param text
     * @param word
     * @return
     */
    public static boolean isContainWord(String text,String word){
        if(text.contains(word)){
            return true;
        }else {
            return false;
        }
    }
}
