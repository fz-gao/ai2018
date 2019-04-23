package chap7;

/**
 * 第一个动态处理程序
 * @author gsd
 */
import chap7.service.NewsService;
import chap7.vo.NewsVO;
import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Slf4j
public class Ch1Servlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        java.util.Date today = new java.util.Date();
        out.println("<html> " + "<body>" + "<h1 align=center>HF's Chapter1 Servlet</h1>" + "<br>" + today + "</body>" + "</html>");
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        Joiner joiner  = Joiner.on(",");
        java.util.Date today = new java.util.Date();
        request.getParameterMap().forEach((x, y)->{
            log.info(x+"-->" + joiner.join(y));
        });

        NewsService newsService = new NewsService();
        List<NewsVO> result =  newsService.listNews();

        if(result == null || result.isEmpty()) {
            out.println("<html> " + "<body>" + "This is Post Method" + "<br>" +
                    "查询结果为空！"+ "</body>" + "</html>");
        }
        out.println("<html> " + "<body>" + "This is Post Method" + "<br>查询结果为：" +
                joiner.join(result)+ "</body>" + "</html>");
    }


}
