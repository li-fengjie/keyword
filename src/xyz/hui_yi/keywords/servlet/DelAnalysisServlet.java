package xyz.hui_yi.keywords.servlet;

import xyz.hui_yi.keywords.dao.AnalysisDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delAnalysis")
public class DelAnalysisServlet extends HttpServlet {
    private AnalysisDao analysisDao = new AnalysisDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int r_id = Integer.parseInt(request.getParameter("r_id"));
        int state = analysisDao.selectAnalysisState(r_id);
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        if(state == 1){
            response.getWriter().print("正在分析中，不可删除");
        }else {
            analysisDao.deleteAnalysisBean(r_id);
            response.getWriter().print("true");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}