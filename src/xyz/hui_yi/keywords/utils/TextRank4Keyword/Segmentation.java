package xyz.hui_yi.keywords.utils.TextRank4Keyword;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.recognition.impl.StopRecognition;
import org.ansj.splitWord.analysis.ToAnalysis;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Description: 分词 基于 ansj
 * @author Li-Fengjie
 */
public class Segmentation {

    // 加载日志组件
//    private static Logger logger = Logger.getLogger(Segmentation.class);

    // 分词二级列表 第一级元素以句子为单位划分 第二级是句子分词结果
    private List<List<String>> resultText;

    // 分句结果列表 元素是句子字符串
    private String[] resultSentences;

    // 单词小写 是否用停止词 是否过滤词性（暂时都没用）
    private boolean lower = true, use_stop_words = true, use_speech_tags_filter = true;

    /**
     * 返回分词结果
     * @return
     */
    public List<List<String>> getResultText() {
        return resultText;
    }

    /**
     * 返回分句结果
     * @return
     */
    public String[] getResultSentences() {
        return resultSentences;
    }

    /**
     * 分词函数入口
     * @param text 读取文件得到的字符串流
     * @return
     */
    public List<List<String>> segment(String text){

        String[] sentences = SentenceSegmentation(text);
        List<List<String>> words = WordSegmentation(sentences);
        this.resultText = words;
        return words;
    }

    /**
     * 分句
     * @param text 读取文件得到的字符串流
     * @return
     */
    public String[] SentenceSegmentation(String text){
        //logger.info("SentenceSeg");
        // 去除换行符 空格 制表符
        text =text.replaceAll("\\s*", "");

        // 正则表达式：句子结束符 分句

        String regEx="[; ；。？！?.!]";
        Pattern p =Pattern.compile(regEx);

        // 按照句子结束符分割句子
        String[] sentences = p.split(text);

        // 去除其余标点
        for( int i = 0; i < sentences.length; i++){
            sentences[i] = sentences[i].replaceAll("\\p{P}" , "");
            //logger.info(sentences[i]);
        }
        this.resultSentences = sentences;
        return sentences;
    }

    /**
     * 把句子进一步分词
     * @param sentences 分好的句子列表
     * @return
     */
    public List<List<String>> WordSegmentation(String[] sentences){
        //logger.info("WordSeg");
        //List<List<Term>> listTest = new ArrayList<List<Term>>();

        List<List<String>> sentenceWordsList = new ArrayList<List<String>>();
        for(int i = 0; i < sentences.length; i++){
            List<String> wordsList = new ArrayList<String>();
            StopRecognition filter = new StopRecognition();

            //filter.insertStopWords("我"); //过滤单词
            //调用过滤
            //Result modifResult = ToAnalysis.parse(sentences[i]).recognition(filter); //过滤分词结果

            Result result = ToAnalysis.parse(sentences[i]);
            List<Term> terms = result.getTerms(); //拿到terms

            for(int j = 0; j < terms.size(); j++){
                String word = terms.get(j).getName(); //拿到词
                wordsList.add(word);
            }
            sentenceWordsList.add(i, wordsList);

        }
        //logger.info(listTest);
        return sentenceWordsList;
    }
}
