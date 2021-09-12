package com.zst.week6.q7;

import com.zst.week6.q7.module.user.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * Sqlite不在SpringJDBC支持列表中，使用连接池管理Sqlite连接是需要自己实现Dialect，
 * 所以下面以原始的创建JDBC连接的方式执行SQL
 */
@SpringBootTest
@ActiveProfiles("sqlite")
public class SqliteDataSourceTest extends DataSourceTest {
    private static final Logger logger = LoggerFactory.getLogger(SqliteDataSourceTest.class);

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInsert() {
        Map<String, Object> params = userInsertParam();
        try {
            int insertRowNum = executeUpdate(userInsertRawJDBCSql(), params, (stmt, param) -> {
                try {
                    stmt.setString(1, (String) param.get("id"));
                    stmt.setString(2, (String) param.get("name"));
                } catch (SQLException e) {
                    logger.error(e.getMessage(), e);
                }
            });
            Assertions.assertEquals(insertRowNum, 1);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Test
    public void testQuery() {
        try {
            List<User> userList = executeQuery(userSelectSql(), false);
            Assertions.assertTrue(!userList.isEmpty());
            userList.forEach(user -> logger.info(user.toString()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Test
    public void testUpdate() {
    }

    @Test
    public void testDelete() {

    }

    private List<User> executeQuery(String sql, boolean unique) throws SQLException {
        try(Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            List<User> resultList = new ArrayList<>();
            while (rs.next()) {
                resultList.add(new User(rs.getString("id"), rs.getString("name")));
                if (unique) {
                    break;
                }
            }
            return resultList;
        } catch (SQLException e) {
            throw e;
        }
    }

    private int executeUpdate(String sql, Map<String, Object> params,
                              BiConsumer<PreparedStatement, Map<String, Object>> paramMapper) throws SQLException {
        Connection conn = getConnection();
        int resultRow = 0;
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            paramMapper.accept(stmt, params);
            resultRow = stmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            conn.close();
        }

        return resultRow;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite::resource:sqlite/zst.db");
    }
}
