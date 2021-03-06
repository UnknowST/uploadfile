package servlet;

import dao.upfile;
import service.Operate;
import service.impl.Operatimpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownLoadServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//得到要下载的文件名
        String fileName = request.getParameter("filename");

        fileName = new String(fileName.getBytes("iso8859-1"),"UTF-8");
//上传的文件都是保存在/WEB-INF/upload目录下的子目录当中
        String fileSaveRootPath=this.getServletContext().getRealPath("/WEB-INF/upload");

//通过文件名找出文件的所在目录
        upfile f1=new upfile();
        f1=findFileSavePathByFileName(fileName);
        String path = f1.getPath();
        System.out.println("real2"+path);
        //得到要下载的文件
        File file = new File(path + "\\" + fileName);

//如果文件不存在
        if(!file.exists()){
            request.setAttribute("message", "您要下载的资源已被删除！！");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
            return;
        }
//处理文件名

//设置响应头，控制浏览器下载该文件 并将文件还原为原始的名称
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(f1.getName(), "UTF-8"));
//读取要下载的文件，保存到文件输入流
        FileInputStream in = new FileInputStream(path + "\\" + fileName);
//创建输出流
        OutputStream out = response.getOutputStream();
//创建缓冲区
        byte buffer[] = new byte[1024];
        int len = 0;
//循环将输入流中的内容读取到缓冲区当中
        while((len=in.read(buffer))>0){
//输出缓冲区的内容到浏览器，实现文件下载
            out.write(buffer, 0, len);
        }
//关闭文件输入流
        in.close();
//关闭输出流
        out.close();
    }

    /**
     * @Method: findFileSavePathByFileName
     * @Description: 通过文件的uuid找到文件在服务器的物质
     * @param filename 要下载的文件名
     * @return 文件原始信息
     */
    public upfile findFileSavePathByFileName(String filename){
       //根据文件名的uuid查找文件真是路径

       String uuid=filename.substring(0,filename.lastIndexOf("."));
        System.out.println("uuid:  "+uuid);
        Operate op=new Operatimpl();
        return op.findpath(uuid);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
