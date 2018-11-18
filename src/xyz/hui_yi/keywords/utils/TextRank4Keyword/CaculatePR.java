package xyz.hui_yi.keywords.utils.TextRank4Keyword;

/**
 * @Description:
 * @author Li-Fengjie
 */

public class CaculatePR {

    /**
     * 计算pagerank的函数
     * @param link 页面的链接图
     * @param damp 设置的阻尼系数，设的是0.85
     * @param linkout 每个页面的链接总数
     * @param init 上次迭代的结果
     *
     * */
    public static double[] pagerank(double[][] link, double damp, int[] linkout,
                                    double[] init) {

        double[] pr = new double[init.length];
        double num = 0;
        for (int i = 0; i < init.length; i++) {
            for (int j = 0; j < init.length; j++) {
// 接下来的判断是关键,计算非本页面，和链接数不为0的页面,并且那个页面有连到本页面的值，即是links[j][i] !=0。
                if ((i != j) && (linkout[j] != 0) && (link[j][i] != 0))
                    num = num + init[j] / linkout[j];

            }
// pagerank的计算公式
            pr[i] = damp + (1 - damp) * num;
        }
        return pr;
    }

    public static double[] pagerank2(double[][] link, double damp, int cishu){

        int[] linkOut = new int[link.length];
        for (int i = 0; i < link.length; i++) {
            for (int j = 0; j < link.length; j++) {
                if(link[i][j] != 0){
                    linkOut[i] +=1;
                }
                //linkOut[i] += link[i][j];
            }
        }

        //定义一个初始数组并初始化，设所有的页面初始PR值都为1；
        double[] pr = new double[link.length];

        double[] init = new double[link.length];
        for (int i = 0; i < link.length; i++) {
            init[i] = 1.0;
        }
        pr = pagerank(link, damp, linkOut, init);

        //我们进行10次迭代计算pagerank的值
        for(int i=0;i<10;i++){
            System.arraycopy(pr, 0, init, 0, link.length);
            pr = pagerank(link, damp, linkOut, pr);
        }

        return pr;

    }
}

