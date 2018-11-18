package xyz.hui_yi.keywords.utils.TextRank4Keyword;

import java.util.*;

/**
 * @Description:
 * @author Li-Fengjie
 *
 */
public class TextRank4Sentence {
    // 日志
//    private Logger logger = Logger.getLogger(TextRank4Keyword.class);

    private int window = 2;

    private String[][] words_no_filter;    // 对sentences中每个句子分词而得到的两级列表
    private String[][] words_no_stopwords; // 去掉words_no_filter中的停止词而得到的两级列表
    private String[][] words_all_filters;  // 保留words_no_stop_words中指定词性的单词而得到的两级列表

    private String stop_words_file; // 指定停止词文件路径（一行一个停止词），若为其他类型，则使用默认停止词文件
    private String[] delimiters; // 符号 用来把文本拆成句子

    // 关键句子
    private Map<String, String> keysentences;

    // 分词器 重复了！
    private Segmentation seg  = new Segmentation();

    public TextRank4Sentence(){
        // 估计应该在这里初始化上面定义那些个参数 暂时还没用到
    }

    /**
     * 分析 核心是分析出句子按权重排序
     * @param text
     * @param window
     */
    public void analyse(String text,int window) {
        //System.out.println(text+ window);
//        logger.info("analyse");

        seg.segment(text=text);
        List<List<String>> resultWords = seg.getResultText();
        String[] resultSentences = seg.getResultSentences();

        keysentences = Util.sort_sentences(resultSentences,resultWords);
    };

    /**
     *
     * @param num
     * @param word_min_len
     * @return 返回句子摘要列表 map<句子，权重>
     */
    public Map<String,String> get_keysentences(int num, int word_min_len) {
        Map<String, String> result = new HashMap();

        int count = 0;

        Set<String> keySet = keysentences.keySet();

        Iterator<String> iter = keySet.iterator();
        while (iter.hasNext()) {
            if(count >= num){
                break;
            }
            String key = iter.next();
            //System.out.println(key + ":" + keysentences.get(key));
            String word = keysentences.get(key);
            if(word.length()>word_min_len){
                result.put(word,key);
                count++;
            }
            //System.out.println(keywords.get(keywords.get(key)));
        }
        return result;
    }


}
