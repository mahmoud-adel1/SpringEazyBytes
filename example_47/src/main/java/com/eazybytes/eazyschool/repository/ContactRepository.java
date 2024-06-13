package com.eazybytes.eazyschool.repository;

import com.eazybytes.eazyschool.model.Contact;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

    List<Contact> findByStatus(String status);

    @Query("SELECT c FROM Contact c WHERE c.status = :status")
//    @Query(value = "SELECT *,mobile_num AS MobileNum FROM eazyschool.contact_msg WHERE status = :status",nativeQuery = true)
    Page<Contact> findByStatusWithQuery(String status, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Contact c SET c.status = ?1 WHERE c.contactId = ?2")
    int updateMsgStatus(String status, int id);

    Page<Contact> findOpenMsgs(String status, Pageable pageable);

    @Transactional
    @Modifying
    int updateMsgsStatus(String status, int id);


    @Query(nativeQuery = true)
    Page<Contact> findOpenMsgsNative(String status, Pageable pageable);

    @Transactional
    @Modifying
    @Query(nativeQuery = true)
    int updateMsgStatusNative(String status, int id);

}














//
//import com.eazybytes.eazyschool.model.Contact;
//import com.eazybytes.eazyschool.rowmappers.ContactRowMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import java.sql.Timestamp;
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Repository
//public class ContactRepository {
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public ContactRepository(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public int saveContactMsg(Contact contact) {
//        String sql = "INSERT INTO eazyschool.contact_msg (name,mobile_num,email,subject,message," +
//                                               "status,created_at,created_by)" +
//                                               " VALUES (?,?,?,?,?,?,?,?)";
//        return jdbcTemplate.update(sql,contact.getName(),contact.getMobileNum(),contact.getEmail(),
//                                            contact.getSubject(),contact.getMessage(),contact.getStatus(),
//                                            contact.getCreatedAt(),contact.getCreatedBy());
//
//    }
//
//    public List<Contact> findMsgWithStatus(String status) {
//        String sql = "SELECT * FROM eazyschool.contact_msg WHERE status=?";
//        return jdbcTemplate.query(sql, ps -> ps.setString(1,status), new ContactRowMapper());
//    }
//
//    public int updateMsgStatus(int contactId, String close, String updatedBy) {
//        String sql = "UPDATE eazyschool.contact_msg SET status = ? , updated_by = ? , updated_at = ? WHERE contact_id = ?";
//        return jdbcTemplate.update(sql,ps -> {
//            ps.setString(1,close);
//            ps.setString(2,updatedBy);
//            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
//            ps.setInt(4,contactId);
//
//        });
//    }
//}
