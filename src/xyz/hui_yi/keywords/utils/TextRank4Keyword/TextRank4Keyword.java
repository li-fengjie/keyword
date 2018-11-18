package xyz.hui_yi.keywords.utils.TextRank4Keyword;

import java.util.*;


/**
 * @author Li-Fengjie
 */
public class TextRank4Keyword {
    // 日志
//    private  Logger logger = Logger.getLogger(TextRank4Keyword.class);

    //
    private int window = 2;

    private String[][] words_no_filter;    // 对sentences中每个句子分词而得到的两级列表
    private String[][] words_no_stopwords; // 去掉words_no_filter中的停止词而得到的两级列表
    private String[][] words_all_filters;  // 保留words_no_stop_words中指定词性的单词而得到的两级列表

    private String stop_words_file; // 指定停止词文件路径（一行一个停止词），若为其他类型，则使用默认停止词文件
    private String[] delimiters; // 符号 用来把文本拆成句子

    // 关键词 map <关键词， 权重>
    private Map<String, String> keywords;

    // 分词器 学长代码里有单例模式 应该学一下
    private Segmentation seg  = new Segmentation();

    /**
     * 无参数的构造函数
     */
    public TextRank4Keyword(){
        // 估计应该在这里初始化上面定义那些个参数 暂时还没用到
    }

    /**
     * 分析 核心是最后找出keywords
     * @param text
     * @param window
     */
    public void analyse(String text,int window) {
        //System.out.println(text+ window);
        //logger.info("analyse");

        seg.segment(text=text);
        List<List<String>> resultWords = seg.getResultText();
        String[] resultSentences = seg.getResultSentences();
        /*
        for(int i = 0; i < resultWords.size(); i++){
            logger.info(resultWords.get(i));
        }
        */
        List<List<String>> vertex_source = resultWords;
        List<List<String>> edge_source   = resultWords;

        keywords = Util.sort_words(vertex_source, edge_source, window);
    };

    /**
     *
     * @param num 返回关键词列表的长度
     * @param word_min_len 单词的长度大于 word_min_len
     * @return 关键词列表
     */
    public Map<String, String> get_keywords(int num, int word_min_len){
        Map<String, String> result = new HashMap();

        int count = 0;

        Set<String> keySet = keywords.keySet();

        Iterator<String> iter = keySet.iterator();
        while (iter.hasNext()) {
            if(count >= num){
                break;
            }
            String key = iter.next();
            /*System.out.println(key + ":" + keywords.get(key));*/
            int a = 0;//wuyong
            String word = keywords.get(key);
            if(word.length()>word_min_len){
                result.put(word,key);
                count++;
            }
            //System.out.println(keywords.get(keywords.get(key)));
        }
        return result;
    }
}
