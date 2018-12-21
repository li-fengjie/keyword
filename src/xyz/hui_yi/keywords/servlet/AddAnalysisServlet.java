package xyz.hui_yi.keywords.servlet;

import xyz.hui_yi.keywords.dao.AnalysisDao;
import xyz.hui_yi.keywords.utils.commons.CommonsUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Timestamp;

/**
 * 添加分析
 * @author li-fengjie
 */
@WebServlet("/addAnalysis")
public class AddAnalysisServlet extends HttpServlet {
    private AnalysisDao analysisDao=new AnalysisDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
//        java.sql.Timestamp timestamp = CommonsUtils.getTimestamp();
//        System.out.println(timestamp.toString());
        String timestamp = CommonsUtils.getFormatTimestamp();
        analysisDao.insertToAnalysisBean(timestamp,1);

        String r_id = analysisDao.selectAnalysisBean(toString());
        System.out.println(r_id);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print("true");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
