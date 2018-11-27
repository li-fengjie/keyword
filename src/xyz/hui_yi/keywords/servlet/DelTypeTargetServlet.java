package xyz.hui_yi.keywords.servlet;

import xyz.hui_yi.keywords.dao.TypeTargetDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delTarget")
public class DelTypeTargetServlet extends HttpServlet {
    private TypeTargetDao typeTargetDao = new TypeTargetDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int t_id = Integer.parseInt(request.getParameter("t_id"));
        typeTargetDao.deleteTypeTargetBean(t_id);
        response.getWriter().print("true");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
