package xyz.hui_yi.keywords.servlet;

import xyz.hui_yi.keywords.dao.CompanyDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author li-fengjie
 */
@WebServlet("/addCompany")
public class AddCompanyServlet extends HttpServlet {
    private CompanyDao companyDao=new CompanyDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String industry = request.getParameter("industry");
        String stockname = request.getParameter("stockname");
        String stockcode = request.getParameter("stockcode");
        String cname = request.getParameter("cname");
        System.out.println(industry + " " + stockname +" "+ stockcode + " " +cname);
        companyDao.insertToCompanyBean(null,cname,industry,stockcode,stockname,0);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print("true");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
