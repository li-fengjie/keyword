package xyz.hui_yi.keywords.servlet;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.pdfbox.io.IOUtils;
import xyz.hui_yi.keywords.bean.*;
import xyz.hui_yi.keywords.dao.*;
import xyz.hui_yi.keywords.utils.commons.CommonsUtils;
import xyz.hui_yi.keywords.utils.poi.JSON2ExcelUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * 分析结果数据文件下载
 * 两个头一个流
 */
@WebServlet("/downloadFile")
public class DownloadFile extends HttpServlet {
    private AnalysisDao analysisDao = new AnalysisDao();
    private AimsResultDao aimsResultDao = new AimsResultDao();

//    private AnalysisDataDao analysisDataDao = new AnalysisDataDao();
//    private CompanyDao companyDao = new CompanyDao();
//    private KeywordDao keywordDao = new KeywordDao();
//    private TypeTargetDao typeTargetDao = new TypeTargetDao();
    private List<ResultBean> resultBeans = new ArrayList<>();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String root = this.getServletContext().getRealPath("/WEB-INF/excelFiles");
        String r_id = request.getParameter("r_id");
        if(r_id == null || r_id.equals("")){
            response.setHeader("Content-type", "text/html;charset=utf-8");
            response.getWriter().println("参数不得为空!");
            return;
        }
        AnalysisBean analysisBean = analysisDao.selectAnalysisBean(Integer.parseInt(r_id));
//        List<AnalysisDataBean> analysisDataBeans = analysisDataDao.selectAnalysisDataBean(Integer.parseInt(r_id));
//        for(int i = 0; i < analysisDataBeans.size(); i++){
//            AnalysisDataBean analysisDataBean = analysisDataBeans.get(i);
//            int c_id = analysisDataBean.getC_id();
//            int k_id = analysisDataBean.getK_id();
//            int count = analysisDataBean.getCount();
//            int d_id = analysisDataBean.getD_id();
//            CompanyBean companyBean = companyDao.selectCompanyBean(c_id);
//            KeywordBean keywordBean = keywordDao.selectKeywordBean(k_id);
//            int t_id = keywordBean.getT_id();
//            TypeTargetBean typeTargetBean = typeTargetDao.selectTypeTargetBean(t_id);
//            String typename = typeTargetBean.getName();
//
//            //初始化ResultBean
//            ResultBean resultBean = new ResultBean();
//            companyDao.selectCompanyBean(analysisDataBean.getC_id());
//            resultBean.setIndustry(companyBean.getIndustry());
//            resultBean.setStockname(companyBean.getStockname());
//            resultBean.setStockcode(companyBean.getStockcode());
//            resultBean.setTypename(typename);
//            resultBean.setCount(count);
//            //添加数据
//            resultBeans.add(resultBean);
//        }


        resultBeans = aimsResultDao.selectAimDataBean(Integer.parseInt(r_id));
        JSONArray jsonArray = JSONArray.fromObject(resultBeans);
        System.out.println(jsonArray);
        //是否在分析中
        int state = analysisDao.selectAnalysisState(Integer.parseInt(r_id));

        String starttime = analysisBean.getStarttime();
        starttime = starttime.replaceAll(":",".");
        System.out.println(starttime);
        File dirFile = new File(root);
        //创建目录链
        if(!dirFile.exists()){
            dirFile.mkdirs();
        }
        String fileDir = root + "/" + starttime + ".xls";
        System.out.println(fileDir);
        String filename = "关键词分析结果"+starttime+".xls";
        File file = new File(fileDir);

        //是否分析过 || 是否分析完成，
        if(!file.exists() || state != 0){
            JSON2ExcelUtils.JSON2Excel(jsonArray,fileDir);
        }
        /*
        防止文件名乱码
         */
        String frameName = new String(filename.getBytes("GBK"),"iso-8859-1");

        String contentType = this.getServletContext().getMimeType(fileDir);
        String contentDisposition = "attachment;filename="+frameName;

        FileInputStream inputStream = new FileInputStream(fileDir);

        response.setHeader("ContentType",contentType);
        response.setHeader("Content-Disposition",contentDisposition);

        //绑定响应端的流
        ServletOutputStream outputStream = response.getOutputStream();
        IOUtils.copy(inputStream,outputStream);
        inputStream.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
