package xyz.hui_yi.keywords.servlet;

import xyz.hui_yi.keywords.dao.KeywordDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delKeyword")
public class DelKeywordServlet extends HttpServlet {
    private KeywordDao keywordDao = new KeywordDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int k_id = Integer.parseInt(request.getParameter("k_id"));
        keywordDao.deleteKeywordBean(k_id);
        response.getWriter().print("true");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
