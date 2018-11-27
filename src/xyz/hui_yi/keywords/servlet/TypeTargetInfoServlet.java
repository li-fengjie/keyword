package xyz.hui_yi.keywords.servlet;

import net.sf.json.JSONObject;
import xyz.hui_yi.keywords.bean.*;
import xyz.hui_yi.keywords.dao.TypeTargetDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/typeTargetInfo")
public class TypeTargetInfoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public TypeTargetInfoServlet() {
        super();
    }

    private TypeTargetDao typeTargetDao = new TypeTargetDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TypeTargetPageBean TypeTargetBeans = typeTargetDao.queryTypeTargetPageBean();
        JSONObject jsonObject = JSONObject.fromObject(TypeTargetBeans);
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().print(jsonObject.toString());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
