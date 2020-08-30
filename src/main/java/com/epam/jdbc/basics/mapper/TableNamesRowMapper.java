package com.epam.jdbc.basics.mapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TableNamesRowMapper implements RowMapper<String> {

    private static final String TABLE_NAME = "TABLE_NAME";

    @Override
    public String mapRow(ResultSet rs, int row) throws SQLException {
        return rs.getString(TABLE_NAME);
    }
}
