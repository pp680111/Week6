package com.zst.week6.q7;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class DataSourceTest {
    public String userInsertNamedParamSql() {
        return "insert into t_user values(:id, :name)";
    }

    public String userInsertRawJDBCSql() {
        return "insert into t_user values(?, ?)";
    }

    public Map<String, Object> userInsertParam() {
        Map<String, Object> params = new HashMap<>();
        params.put("id", UUID.randomUUID().toString().replaceAll("-", ""));
        params.put("name", "Flynn");
        return params;
    }

    public String userSelectSql() {
        return "select * from t_user";
    }

    public String userGetSql() {
        return "select * from t_user where id = :id";
    }

    public String userUpdateSql() {
        return "update t_user set name = :name where id = :id";
    }

    public String userDeleteSql() {
        return "delete from t_user where id = :id";
    }
}
