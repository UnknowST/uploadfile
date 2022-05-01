package servlet;

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
public class ListFileServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//获取上传文件的目录
        String uploadFilePath = this.getServletContext().getRealPath("/WEB-INF/upload");
//存储要下载的文件名
        Map<String,String> fileNameMap = new HashMap<String,String>();

//递归遍历filepath目录下的所有文件和目录，将文件的文件名存储到map集合中
        listfile(new File(uploadFilePath),fileNameMap);//File既可以代表一个文件也可以代表一个目录
//将Map集合发送到listfile.jsp页面进行显示
        request.setAttribute("fileNameMap", fileNameMap);
        request.getRequestDispatcher("/listfile.jsp").forward(request, response);
    }

    /**
     * @Method: listfile

     * @param file 即代表一个文件，也代表一个文件目录
     * @param map 存储文件名的Map集合
     */
    public void listfile(File file,Map<String,String> map){
//如果file代表的不是一个文件，而是一个目录
        if(!file.isFile()){
//列出该目录下的所有文件和目录
            File files[] = file.listFiles();
//遍历files[]数组
            for(File f : files){
//递归
                listfile(f,map);
            }
        }else{
            map.put(file.getName(), file.getName());
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}