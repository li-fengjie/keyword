package xyz.hui_yi.keywords.servlet;

import xyz.hui_yi.keywords.bean.*;
import xyz.hui_yi.keywords.dao.*;
import xyz.hui_yi.keywords.utils.commons.CommonsUtils;
import xyz.hui_yi.keywords.utils.findwords.CountWord;
import xyz.hui_yi.keywords.utils.mail.EmailUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Timestamp;
import java.util.List;

/**
 * 添加分析
 * @author li-fengjie
 */
@WebServlet("/addAnalysis")
public class AddAnalysisServlet extends HttpServlet {
    private AnalysisDao analysisDao=new AnalysisDao();
    private FileDao fileDao = new FileDao();
    private AnalysisDataDao analysisDataDao = new AnalysisDataDao();
    private TypeTargetDao typeTargetDao= new TypeTargetDao();
    private KeywordDao keywordDao = new KeywordDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
//        java.sql.Timestamp timestamp = CommonsUtils.getTimestamp();
//        System.out.println(timestamp.toString());
        String starttime = CommonsUtils.getFormatTimestamp();
        analysisDao.insertToAnalysisBean(starttime,1);
        //获取插入新数据的r_id
        int r_id = analysisDao.selectAnalysisBean(starttime);
        System.out.println(r_id);

        TypeTargetPageBean typeTargetPageBean = typeTargetDao.queryTypeTargetPageBean();
        List<TypeTargetBean> typeTargetBeans = typeTargetPageBean.getTypeTargetBeans();

        FilePageBean filePageBean  = fileDao.queryFilePageBean(1,50);
        List<FileBean> fileBeans = filePageBean.getFileBeans();
        for (FileBean f: fileBeans
             ) {
            int c_id = f.getC_id();
            int d_id = f.getD_id();
            String content = f.getContent();
            for (TypeTargetBean t :
                    typeTargetBeans) {
                int t_id = t.getT_id();
                KeywordPageBean keywordPageBean = keywordDao.queryKeywordPageBean(t_id);
                List<KeywordBean> keywordBeans = keywordPageBean.getKeywordBeans();
                for (KeywordBean k: keywordBeans
                     ) {
                    int k_id = k.getK_id();
                    int count = CountWord.CountWord(content,k.getName());
                    analysisDataDao.insertToAnalysisDataBean(r_id,d_id,k_id,c_id,count);
                }
            }
        }

        long sum = filePageBean.getPageSum();

        for(int i = 2;i  <= sum; i++){
            filePageBean  = fileDao.queryFilePageBean(i,50);
            sum = filePageBean.getPageSum();
            fileBeans = filePageBean.getFileBeans();
            for (FileBean f: fileBeans
                    ) {
                int c_id = f.getC_id();
                int d_id = f.getD_id();
                String content = f.getContent();
                for (TypeTargetBean t :
                        typeTargetBeans) {
                    int t_id = t.getT_id();
                    KeywordPageBean keywordPageBean = keywordDao.queryKeywordPageBean(t_id);
                    List<KeywordBean> keywordBeans = keywordPageBean.getKeywordBeans();
                    for (KeywordBean k: keywordBeans
                            ) {
                        int k_id = k.getK_id();
                        if(k.getName() != null && content != null){
                            int count = CountWord.CountWord(content,k.getName());
                            analysisDataDao.insertToAnalysisDataBean(r_id,d_id,k_id,c_id,count);
                        }
                    }
                }
            }
        }
        String endtime = CommonsUtils.getFormatTimestamp();
        analysisDao.updateAnalysisBean(r_id,endtime,0);

        response.setCharacterEncoding("UTF-8");
        response.getWriter().print("true");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
