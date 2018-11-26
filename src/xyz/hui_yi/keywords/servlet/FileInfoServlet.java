package xyz.hui_yi.keywords.servlet;

import net.sf.json.JSONObject;
import xyz.hui_yi.keywords.bean.FilePageBean;
import xyz.hui_yi.keywords.dao.FileDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/fileInfo")
public class FileInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FileInfoServlet() {
        super();
    }

    private FileDao fileDao=new FileDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FilePageBean fileBeans =fileDao.queryFilePageBean();
//		System.out.println(fileBeans);
		if(fileBeans != null) {
			JSONObject jsonObject= JSONObject.fromObject(fileBeans);
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().print(jsonObject.toString());
//			response.getWriter().print("error");
		}else {

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
