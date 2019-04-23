package chap7;

/**
 * 第一个动态处理程序
 * @author gsd
 */
import com.google.common.base.Joiner;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Ch1Servlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("haha");
        PrintWriter out = response.getWriter();
        java.util.Date today = new java.util.Date();
        out.println("<html> " + "<body>" + "<h1 align=center>HF's Chapter1 Servlet</h1>" + "<br>" + today + "</body>" + "</html>");
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        Joiner joiner  = Joiner.on(",");
        java.util.Date today = new java.util.Date();
        request.getParameterMap().forEach((x, y)->{
            System.out.println(x+"-->" + joiner.join(y));
        });

        out.println("<html> " + "<body>" + "This is Post Method" + "<br>" + today + "</body>" + "</html>");
    }
}
