package com.eazybytes.eazyschool.repository;

import com.eazybytes.eazyschool.model.Holiday;
import com.eazybytes.eazyschool.rowmappers.HolidayRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.swing.tree.RowMapper;
import java.util.List;

@Repository
public class HolidayRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public HolidayRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Holiday> findAll() {
        String sql = "SELECT * FROM eazyschool.holidays";
        var mapper = BeanPropertyRowMapper.newInstance(Holiday.class);
        return jdbcTemplate.query(sql,mapper);
    }
}
