package com.eazybytes.eazyschool.repository;

import com.eazybytes.eazyschool.model.Contact;
import com.eazybytes.eazyschool.rowmappers.ContactRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ContactRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ContactRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int saveContactMsg(Contact contact) {
        String sql = "INSERT INTO eazyschool.contact_msg (name,mobile_num,email,subject,message," +
                                               "status,created_at,created_by)" +
                                               " VALUES (?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,contact.getName(),contact.getMobileNum(),contact.getEmail(),
                                            contact.getSubject(),contact.getMessage(),contact.getStatus(),
                                            contact.getCreatedAt(),contact.getCreatedBy());

    }

    public List<Contact> findMsgWithStatus(String status) {
        String sql = "SELECT * FROM eazyschool.contact_msg WHERE status=?";
        return jdbcTemplate.query(sql, ps -> ps.setString(1,status), new ContactRowMapper());
    }

    public int updateMsgStatus(int contactId, String close, String updatedBy) {
        String sql = "UPDATE eazyschool.contact_msg SET status = ? , updated_by = ? , updated_at = ? WHERE contact_id = ?";
        return jdbcTemplate.update(sql,ps -> {
            ps.setString(1,close);
            ps.setString(2,updatedBy);
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            ps.setInt(4,contactId);

        });
    }
}
