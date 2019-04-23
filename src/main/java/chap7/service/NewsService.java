package chap7.service;

import chap7.vo.NewsVO;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.List;

/**
 * @author gsd
 */
@Slf4j
public class NewsService {

    public static final String url = "jdbc:mysql://xx.xx.xx.xx:3306/news";
    public static final String name = "com.mysql.cj.jdbc.Driver";
    public static final String username = "ai2018";
    public static final String password = "helloworld";

    public Connection conn = null;
    public PreparedStatement statement = null;

    static {
        try {
            Class.forName(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public PreparedStatement getStatement(String sql) {
        try {

            conn = DriverManager.getConnection(url, username, password);
            statement = conn.prepareStatement(sql);
            return statement;
        } catch (Exception e) {
            log.error("PreparedStatement error", e);
            throw new RuntimeException(e);
        }
    }

    public void close() {
        try {
            this.statement.close();
            this.conn.close();
        } catch (Exception e) {
            log.error("Close database connection error", e);
            throw new RuntimeException(e);
        }
    }

    public List<NewsVO> listNews() {
        List<NewsVO> result = Lists.newArrayList();
        String sql = "SELECT title, news_time, news_source, detail_url, create_time, platform, edit_time FROM news.news_info";
        PreparedStatement statement = this.getStatement(sql);
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery();

            while(resultSet.next()) {
                NewsVO newsVO = new NewsVO();
                newsVO.setTitle(resultSet.getString("title"));
                newsVO.setCreateTime(resultSet.getTimestamp("create_time").toLocalDateTime());
                result.add(newsVO);
            }
        } catch (SQLException e) {
            log.error("Query sql error", e);
        } finally {
            close();
        }
        return result;
    }
}
