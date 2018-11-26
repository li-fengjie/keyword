package xyz.hui_yi.keywords.servlet;

import net.sf.json.JSONObject;
import xyz.hui_yi.keywords.bean.CompanyBean;
import xyz.hui_yi.keywords.bean.FileBean;
import xyz.hui_yi.keywords.bean.FilePageBean;
import xyz.hui_yi.keywords.dao.CompanyDao;
import xyz.hui_yi.keywords.dao.FileDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/fileInfo")
public class FileInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FileInfoServlet() {
        super();
    }

    private FileDao fileDao=new FileDao();
    private CompanyDao companyDao = new CompanyDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FilePageBean fileBeans =fileDao.queryFilePageBean();
		List<FileBean> list = fileBeans.getFileBeans();
		for (int i = 0; i < list.size(); i++) {
		    if(list.get(i).getC_id() != null){
                CompanyBean companyBean = companyDao.selectCompanyBean(list.get(i).getC_id());
                System.out.println(companyBean.getStockname());
                list.get(i).setStockname(companyBean.getStockname());
            }
		}
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
