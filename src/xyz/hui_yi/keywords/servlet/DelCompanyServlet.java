package xyz.hui_yi.keywords.servlet;

import xyz.hui_yi.keywords.dao.CompanyDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delCompany")
public class DelCompanyServlet extends HttpServlet {
    private CompanyDao companyDao = new CompanyDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int c_id = Integer.parseInt(request.getParameter("c_id"));
        companyDao.deleteCompanyBean(c_id);
        response.getWriter().print("true");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
