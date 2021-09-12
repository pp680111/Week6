package com.zst.week6.q7;

import com.zst.week6.q7.module.user.entity.User;
import org.assertj.core.util.Maps;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@SpringBootTest
@ActiveProfiles("h2")
@Transactional
@Rollback
public class H2DataSourceTest extends DataSourceTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Test
    public void testInsert() {
        int updatedRow = namedParameterJdbcTemplate.update(userInsertNamedParamSql(), userInsertParam());
        Assertions.assertEquals(updatedRow, 1);
    }

    @Test
    public void testQuery() {
        namedParameterJdbcTemplate.update(userInsertNamedParamSql(), userInsertParam());
        List<User> userList = jdbcTemplate.query(userSelectSql(), (rs, rowNum) -> new User(rs.getString("id"), rs.getString("name")));
        Assertions.assertNotNull(userList);
        Assertions.assertFalse(userList.isEmpty());
        userList.forEach(System.out::println);
    }

    @Test
    public void testUpdate() {
        Map<String, Object> params = userInsertParam();
        namedParameterJdbcTemplate.update(userInsertNamedParamSql(), params);
        String id = (String) params.get("id");
        String oldName = (String) params.get("name");

        params.put("name", "newName");
        int updatedRow = namedParameterJdbcTemplate.update(userUpdateSql(), params);
        Assertions.assertEquals(updatedRow, 1);
    }

    @Test
    public void testDelete() {
        Map<String, Object> params = userInsertParam();
        namedParameterJdbcTemplate.update(userInsertNamedParamSql(), params);

        String id = (String) params.get("id");
        int deletedRow = namedParameterJdbcTemplate.update(userDeleteSql(), Maps.newHashMap("id", id));
        Assertions.assertEquals(deletedRow, 1);

        List list = jdbcTemplate.queryForList(userSelectSql());
        Assertions.assertTrue(list.isEmpty());

    }
}
