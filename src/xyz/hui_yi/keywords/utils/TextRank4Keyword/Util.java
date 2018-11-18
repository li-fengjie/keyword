package xyz.hui_yi.keywords.utils.TextRank4Keyword;

import java.util.*;

import static java.lang.Math.abs;

/**
 * @Description:
 * @Author: J.Y.Zhang
 * @Date: 2017/10/15
 */
public class Util {
    /**
     * 计算两个句子的相似性
     * @param word_list1
     * @param word_list2
     */
    public static double get_simliarity(List<String> word_list1, List<String> word_list2){
        List<String> list1 = word_list1;
        List<String> list2 = word_list2;

        List<String> words = new ArrayList<String>();
        words.addAll(list1);
        words.addAll(list2);
        words = new ArrayList<String>(new LinkedHashSet<>(words));
        //System.out.println(words);
        float[] vector1 = new float[words.size()];
        float[] vector2 = new float[words.size()];
        float[] vector3 = new float[words.size()];
        float[] vector4 = new float[words.size()];
        for(int i = 0; i < words.size(); i++){
            for(String word1: word_list1){
                if(words.get(i) == word1){
                    vector1[i] += 1;
                }
            }
        }

        for(int i = 0; i < words.size(); i++){
            for(String word2: word_list2){
                if(words.get(i) == word2){
                    vector2[i] += 1;
                }
            }
        }
        //System.out.println(vector1);

        //vector3 = [vector1[x]*vector2[x]  for x in xrange(len(vector1))]
        for(int x = 0; x < vector1.length; x++){
            vector3[x] = vector1[x]*vector2[x];
        }
        //vector4 = [1 for num in vector3 if num > 0.]
        for(int x = 0; x < vector3.length; x++){
            if(vector3[x] > 0.){
                vector4[x] = 1;
            }
        }
        //co_occur_num = sum(vector4)
        float co_occur_num = 0;
        for (int i = 0; i < vector4.length; i++) {
            co_occur_num = co_occur_num + vector4[i];
        }
        if (abs(co_occur_num) <= 1e-12 ){
            return 0.;
        }

        float size1 = (float) word_list1.size();
        float size2 = (float) word_list2.size();
        double denominator = Math.log( size1 ) + Math.log( size2 );
        if( abs(denominator) <= 1e-12 ){
            return  0.;
        }
        
        return co_occur_num / denominator;
    }

    /**
     * 句子排序
     * @param sentences   列表，元素是句子
     * @param words       二维列表，子列表和sentences中的句子对应，子列表由单词组成
     * @return map <句子，权重>
     */
    public static Map<String, String> sort_sentences(String[] sentences, List<List<String>> words){

        int sentences_num = sentences.length;
        double[][] graph = new double[sentences_num][sentences_num]; // 默认初始值是0
        for(int i = 0; i < sentences_num; i++){
            for(int j = i; j < sentences_num; j++){
                double similarity = get_simliarity( words.get(i), words.get(j) );
                //System.out.println(similarity);
                graph[i][j] = similarity;
                graph[j][i] = similarity;
            }
        }

        double damp = 0.85;
        double[][] link = graph;
        double[] pr = CaculatePR.pagerank2(graph,damp,10);
        //System.out.println(pr[0]);

        Map<String, String> map = new TreeMap<String, String>(
                new Comparator<String>() {
                    public int compare(String obj1, String obj2) {
                        // 降序排序
                        return obj2.compareTo(obj1);
                    }
                });
        for (int i = 0; i < link.length; i++) {
            map.put(""+pr[i], sentences[i]);

        }
        Set<String> keySet = map.keySet();
        Iterator<String> iter = keySet.iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            //System.out.println(key + ":" + map.get(key));
            //System.out.println(index_word.get(Integer.parseInt(map.get(key)) ));
        }
        return map;

    }

    /**
     * 构造在window下的单词组合，用来构造单词之间的边。
     * @param word_list
     * @param window
     * @return
     */

    public static List<String[]> combine(List<String> word_list, int window){

        if(window < 2){
            window = 2;
        }
        List<String[]> wordCombines = new ArrayList<>();
        for(int i = 1; i <  word_list.size(); i++){
            String[] wordCombine = new String[2];
            wordCombine[0] = word_list.get(i-1);
            wordCombine[1] = word_list.get(i);
            wordCombines.add(wordCombine);
        }
        return wordCombines;

    }


    /**
     * 关键词排序
     * @param vertex_source
     * @param edge_source
     * @param i
     * @return
     */
    public static Map<String, String> sort_words(List<List<String>> vertex_source, List<List<String>> edge_source, int i) {
        String[] sort_words = {};
        Map word_index = new HashMap();
        Map index_word = new HashMap();
        int words_number = 0;
        for(List<String> word_list: vertex_source){
            for(String word: word_list){

                boolean contains = word_index.containsKey(word);    //判断是否包含指定的键值
                if(contains == false){
                    //System.out.println("这个单词不在字典里");
                    word_index.put(word,words_number);
                    index_word.put(words_number,word);
                    words_number+=1;
                }

            }
            //System.out.println(word_index);
        }

        //Matrix graph = DenseMatrix.Factory.zeros(words_number, words_number);
        double[][] graph = new double[words_number][words_number];
        for(List<String> word_list: vertex_source){

            List<String[]> combineResult;
            combineResult = combine(word_list,2);

            for(int k = 0; k < combineResult.size();k++){
                //System.out.println(combineResult.get(k));
                String w1 = combineResult.get(k)[0];
                String w2 = combineResult.get(k)[1];
                if(word_index.containsKey(w1) && word_index.containsKey(w2) ){
                    int index1 = (int) word_index.get(w1);
                    int index2 = (int) word_index.get(w2);
                    //System.out.println(index1+w1);
                    graph[index1][index2] = 1;
                    graph[index2][index1] = 1;
                }
            }

            //System.out.println(word_index);
        }
        /**
         * 计算pr值
         */
        // 定义阻尼系数
        double damp = 0.85;
        double[][] link = graph;
        double[] pr = CaculatePR.pagerank2(graph,damp,10);

        Map<String, String> map = new TreeMap<String, String>(
                new Comparator<String>() {
                    public int compare(String obj1, String obj2) {
                        // 降序排序
                        return obj2.compareTo(obj1);
                    }
                });
        for (i = 0; i < link.length; i++) {
            map.put(""+pr[i], (String) index_word.get(i));

        }
        Set<String> keySet = map.keySet();
        Iterator<String> iter = keySet.iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            //System.out.println(key + ":" + map.get(key));
            //System.out.println(index_word.get(Integer.parseInt(map.get(key)) ));
        }
        return map;
    }
}
