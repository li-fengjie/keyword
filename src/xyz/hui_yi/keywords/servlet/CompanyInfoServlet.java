package xyz.hui_yi.keywords.servlet;

import net.sf.json.JSONObject;
import xyz.hui_yi.keywords.bean.CompanyBean;
import xyz.hui_yi.keywords.dao.CompanyDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class InfoServlet
 */
@WebServlet("/companyInfo")
public class CompanyInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompanyInfoServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    private CompanyDao companyDao=new CompanyDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String pageNoStr=request.getParameter("pageno");
//		String pageSizeStr=request.getParameter("pagesize");
//		System.out.println(pageNoStr + " " + pageSizeStr);
//		int pageno=Integer.parseInt(pageNoStr);
//		int pagesize=Integer.parseInt(pageSizeStr);

//		CompanyPageBean pageBean=companyDao.queryCompanyPageBean(pageno, pagesize);
		CompanyBean companyBeans =companyDao.queryCompanyPageBean();
		System.out.println(companyBeans);
		if(companyBeans != null) {
			JSONObject jsonObject= JSONObject.fromObject(companyBeans);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(jsonObject.toString());
//			response.getWriter().print("error");
		}else {

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
