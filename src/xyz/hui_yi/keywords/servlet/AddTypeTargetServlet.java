package xyz.hui_yi.keywords.servlet;

import xyz.hui_yi.keywords.dao.TypeTargetDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author li-fengjie
 */
@WebServlet("/addTarget")
public class AddTypeTargetServlet extends HttpServlet {
    private TypeTargetDao TypeTargetDao=new TypeTargetDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("name");
        TypeTargetDao.insertToTypeTargetBean(null,name);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print("true");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
