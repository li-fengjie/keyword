package xyz.hui_yi.keywords.servlet;

import net.sf.json.JSONArray;
import xyz.hui_yi.keywords.bean.*;
import xyz.hui_yi.keywords.dao.*;
import xyz.hui_yi.keywords.utils.commons.CommonsUtils;
import xyz.hui_yi.keywords.utils.findwords.CountWord;
import xyz.hui_yi.keywords.utils.mail.Mail;
import xyz.hui_yi.keywords.utils.mail.MailUtils;
import xyz.hui_yi.keywords.utils.poi.JSON2ExcelUtils;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 添加分析
 * 时间会很长
 * 分析统计主要逻辑
 * @author li-fengjie
 */
@WebServlet("/addAnalysis")
public class AddAnalysisServlet extends HttpServlet {
    private AnalysisDao analysisDao = new AnalysisDao();
    private FileDao fileDao = new FileDao();
    private AnalysisDataDao analysisDataDao = new AnalysisDataDao();
    private TypeTargetDao typeTargetDao = new TypeTargetDao();
    private KeywordDao keywordDao = new KeywordDao();
    private EmailDao emailDao = new EmailDao();
    private AimsResultDao aimsResultDao = new AimsResultDao();
    private List<ResultBean> resultBeans = new ArrayList<>();

    private static final int PAGE_SIZE =1000;
    private static final int CORE_POOL_SIZE = 10;
    private static final int MAX_POOL_SIZE = 20;
    private static final int KEEP_ALIVE_TIME = 200;
    private static final int CAPACITY = 500;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("utf-8");
//        java.sql.Timestamp timestamp = CommonsUtils.getTimestamp();
//        System.out.println(timestamp.toString());
            String starttime = CommonsUtils.getFormatTimestamp();
            analysisDao.insertToAnalysisBean(starttime, 1);
            //获取插入新数据的r_id
            int r_id = analysisDao.selectAnalysisBean(starttime);
            System.out.println(r_id);

            TypeTargetPageBean typeTargetPageBean = typeTargetDao.queryTypeTargetPageBean();
            List<TypeTargetBean> typeTargetBeans = typeTargetPageBean.getTypeTargetBeans();

            FilePageBean filePageBean = fileDao.queryFilePageBean(1, PAGE_SIZE);
            List<FileBean> fileBeans = filePageBean.getFileBeans();
            for (FileBean f : fileBeans
                    ) {
                int c_id = f.getC_id();
                int d_id = f.getD_id();
                String content = f.getContent();
                for (TypeTargetBean t :
                        typeTargetBeans) {
                    int t_id = t.getT_id();
                    KeywordPageBean keywordPageBean = keywordDao.queryKeywordPageBean(t_id);
                    List<KeywordBean> keywordBeans = keywordPageBean.getKeywordBeans();
                    for (KeywordBean k : keywordBeans
                            ) {
                        int k_id = k.getK_id();
                        if(content != null && k.getName() != null){
                            int count = CountWord.CountWord(content, k.getName());
                            analysisDataDao.insertToAnalysisDataBean(r_id, d_id, k_id, c_id, count);
                        }
                    }
                }
            }

            long sum = filePageBean.getPageSum();

            response.setCharacterEncoding("UTF-8");
            response.getWriter().print("true");
            //TODO 多线程
            //线程池
            ThreadPoolExecutor executor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.MILLISECONDS,
                    new ArrayBlockingQueue<Runnable>(CAPACITY));
            long step = sum / MAX_POOL_SIZE;
            for(long i = 0; i < MAX_POOL_SIZE; i++){
                executor.execute(new MyTask(i,step,r_id,filePageBean,fileDao,keywordDao,fileBeans,typeTargetBeans,analysisDataDao,analysisDao));
            }
            executor.shutdown();

//            for (int i = 2; i <= sum; i++) {
//                filePageBean = fileDao.queryFilePageBean(i, PAGE_SIZE);
//                sum = filePageBean.getPageSum();
//                fileBeans = filePageBean.getFileBeans();
//                for (FileBean f : fileBeans
//                        ) {
//                    int c_id = f.getC_id();
//                    int d_id = f.getD_id();
//                    String content = f.getContent();
//                    for (TypeTargetBean t :
//                            typeTargetBeans) {
//                        int t_id = t.getT_id();
//                        KeywordPageBean keywordPageBean = keywordDao.queryKeywordPageBean(t_id);
//                        List<KeywordBean> keywordBeans = keywordPageBean.getKeywordBeans();
//                        for (KeywordBean k : keywordBeans
//                                ) {
//                            int k_id = k.getK_id();
//                            if (k.getName() != null && content != null) {
//                                int count = CountWord.CountWord(content, k.getName());
//                                analysisDataDao.insertToAnalysisDataBean(r_id, d_id, k_id, c_id, count);
//                            }
//                        }
//                    }
//                }
//            }
//            //统计结束 添加结束时间 改变状态
//            String endtime = CommonsUtils.getFormatTimestamp();
//            analysisDao.updateAnalysisBean(r_id, endtime, 0);
//
//            //生成报表
//            resultBeans = aimsResultDao.selectAimDataBean(r_id);
//            JSONArray jsonArray = JSONArray.fromObject(resultBeans);
//            String root = this.getServletContext().getRealPath("/WEB-INF/excelFiles");
//            starttime = starttime.replaceAll(":", ".");
//            System.out.println(starttime);
//            File dirFile = new File(root);
//            if (!dirFile.exists()) {
//                dirFile.mkdirs();
//            }
//            String fileDir = root + "/" + starttime + ".xls";
//            System.out.println(fileDir);
//            File file = new File(fileDir);
//
//            JSON2ExcelUtils.JSON2Excel(jsonArray, fileDir);
//
//            // 发送邮箱
//            EmailBean emailBean = emailDao.selectEmailBean();
//            String host = emailBean.getHost();
//            String to = emailBean.getToo();
//            String from = emailBean.getFromm();
//            String password = emailBean.getPassword();
//
//            if (password != null && to != null && from != null && host != null) {
//                Session session = MailUtils.createSession(host, from, password);
//                Mail mail = new Mail(from, to, "来自关键词分析统计系统的分析结果邮件", "" +
//                        //文件名乱码解决
//                        "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"></head><body>欢迎使用关键词分析统计系统，结果请在附件中查看 您的激活码为：1234", file, "关键词分析统计结果" + ".xls");
//
//                try {
//                    MailUtils.send(session, mail);
//                } catch (MessagingException e) {
//                    e.printStackTrace();
//                }
//            }
        } catch (Exception e) {
            EmailBean emailBean = emailDao.selectEmailBean();
            String host = emailBean.getHost();
            String to = emailBean.getToo();
            String from = emailBean.getFromm();
            String password = emailBean.getPassword();
            if (password != null && to != null && from != null && host != null) {
                Session session = MailUtils.createSession(host, from, password);
                Mail mail = new Mail(from, to, "来自关键词分析统计系统的分析结果邮件", "" +
                        //文件名乱码解决
                        "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"></head><body>欢迎使用关键词分析统计系统，分析过程出错" + e.toString());

                try {
                    MailUtils.send(session, mail);
                } catch (MessagingException ee) {
                    ee.printStackTrace();
                }
            }
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

class MyTask implements Runnable {
    private static final int PAGE_SIZE = 500;
    private long i;
    private long step;
    private FilePageBean filePageBean;
//    private FileDao fileDao;
    private KeywordDao keywordDao = new KeywordDao();
    private List<FileBean> fileBeans;
    private List<TypeTargetBean> typeTargetBeans;
    private int r_id;
//    private AnalysisDataDao analysisDataDao;
    private AnalysisDao analysisDao = new AnalysisDao();
    private FileDao fileDao = new FileDao();
    private AnalysisDataDao analysisDataDao = new AnalysisDataDao();
    public MyTask(long i, long step, int r_id, FilePageBean filePageBean, FileDao fileDao, KeywordDao keywordDao,
                  List<FileBean> fileBeans, List<TypeTargetBean> typeTargetBeans, AnalysisDataDao analysisDataDao,
                  AnalysisDao analysisDao) {
        this.i = i;
        this.step = step;
        this.r_id = r_id;
        this.filePageBean = filePageBean;
//        this.fileDao = fileDao;
//        this.keywordDao = keywordDao;
        this.fileBeans = fileBeans;
        this.typeTargetBeans = typeTargetBeans;
//        this.analysisDao = analysisDao;
//        this.analysisDataDao = analysisDataDao;

    }

    @Override
    public void run() {
        System.out.println("正在执行task " + i);
        System.out.println(step + "");
        for (long j = step*i + 1; j <= step*(i + 1); j++) {
            System.out.println("jjjjjjjjjjj " + j+"");
                filePageBean = fileDao.queryFilePageBean1((int)j, PAGE_SIZE);
            System.out.println(filePageBean.toString());
                fileBeans = filePageBean.getFileBeans();
                for (FileBean f : fileBeans
                        ) {
                    int c_id = f.getC_id();
                    int d_id = f.getD_id();
                    String content = f.getContent();
                    for (TypeTargetBean t :
                            typeTargetBeans) {
                        int t_id = t.getT_id();
                        KeywordPageBean keywordPageBean = keywordDao.queryKeywordPageBean(t_id);
                        List<KeywordBean> keywordBeans = keywordPageBean.getKeywordBeans();
                        for (KeywordBean k : keywordBeans
                                ) {
                            int k_id = k.getK_id();
                            if (k.getName() != null && content != null) {
                                int count = CountWord.CountWord(content, k.getName());
                                analysisDataDao.insertToAnalysisDataBean(r_id, d_id, k_id, c_id, count);
                            }
                        }
                    }
                }
        }
        String endtime = CommonsUtils.getFormatTimestamp();
        int state = analysisDao.selectAnalysisState(r_id);
        long num = state + i;
        analysisDao.updateAnalysisBean(r_id,endtime, (int) num);
        System.out.println("task " + i + "执行完毕");
    }
}
