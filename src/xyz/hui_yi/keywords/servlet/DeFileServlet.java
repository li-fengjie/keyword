package xyz.hui_yi.keywords.servlet;


import xyz.hui_yi.keywords.bean.FileBean;
import xyz.hui_yi.keywords.dao.FileDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet("/delFile")
public class DeFileServlet extends HttpServlet {
    private FileDao fileDao = new FileDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int d_id = Integer.parseInt(request.getParameter("d_id"));
        FileBean fileBean = fileDao.selectFileBean(d_id);
        File file = new File(fileBean.getDir());
        fileDao.deleteFileBean(d_id);
        if(file.delete()){
            response.getWriter().print("true");
        }else {
            response.getWriter().print("del path error");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
