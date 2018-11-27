package xyz.hui_yi.keywords.servlet;

import xyz.hui_yi.keywords.dao.KeywordDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author li-fengjie
 */
@WebServlet("/addKeyword")
public class AddKeywordServlet extends HttpServlet {
    private KeywordDao keywordDao=new KeywordDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("name");
        int t_id = Integer.parseInt(request.getParameter("t_id"));
        keywordDao.insertToKeywordBean(null,name,t_id);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print("true");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
