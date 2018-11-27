package xyz.hui_yi.keywords.servlet;

import net.sf.json.JSONObject;
import xyz.hui_yi.keywords.bean.KeywordPageBean;
import xyz.hui_yi.keywords.dao.KeywordDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/keywordInfo")
public class KeywordInfoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public KeywordInfoServlet() {
        super();
    }

    private KeywordDao KeywordDao = new KeywordDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int t_id = Integer.parseInt(request.getParameter("t_id"));
        KeywordPageBean KeywordBeans = KeywordDao.queryKeywordPageBean(t_id);
        JSONObject jsonObject = JSONObject.fromObject(KeywordBeans);
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().print(jsonObject.toString());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
