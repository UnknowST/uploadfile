package servlet;

import dao.upfile;
import service.Operate;
import service.impl.Operatimpl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: ListFileServlet
 * @Description: 列出Web系统中所有下载文件

 *
 */
public class GetinfoServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //获取uuid
        String uuid=request.getParameter("uuid");
        System.out.println(uuid);
        //数据库查询
        Operate op=new Operatimpl();
        upfile f1=new upfile();
        f1=op.findpath(uuid);
        //用来记录查询结果
        int state=3;
        if(f1==null) state=2;
        request.setAttribute("state",state);
        request.setAttribute("file", f1);
        request.getRequestDispatcher("/message.jsp").forward(request, response);
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}