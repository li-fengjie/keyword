package xyz.hui_yi.keywords.servlet;

import net.sf.json.JSONObject;
import xyz.hui_yi.keywords.bean.AnalysisPageBean;
import xyz.hui_yi.keywords.dao.AnalysisDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class InfoServlet
 */
@WebServlet("/analysisInfo")
public class AnalysisInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnalysisInfoServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    private AnalysisDao AnalysisDao=new AnalysisDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AnalysisPageBean AnalysisBeans =AnalysisDao.queryAnalysisPageBean();
		System.out.println(AnalysisBeans);
		if(AnalysisBeans != null) {
			JSONObject jsonObject= JSONObject.fromObject(AnalysisBeans);
//			response.setContentType("text/html;charset=utf-8");
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().print(jsonObject.toString());
		}else {
			response.getWriter().print("null");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
