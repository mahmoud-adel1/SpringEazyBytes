package com.eazybytes.eazyschool.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "contact_msg")
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "SqlResultSetMapping.count", columns = @ColumnResult(name = "cnt"))
})
@NamedQueries({
        @NamedQuery(name = "Contact.findOpenMsgs",
                query = "SELECT c FROM Contact c WHERE c.status = :status"),
        @NamedQuery(name = "Contact.updateMsgsStatus",
                query = "UPDATE Contact c SET c.status = ?1 WHERE c.id = ?2")
})

@NamedNativeQueries({
        @NamedNativeQuery(name = "Contact.findOpenMsgsNative",
                          query = "SELECT * FROM eazyschool.contact_msg WHERE status = :status",
                          resultClass = Contact.class),
        @NamedNativeQuery(name = "Contact.findOpenMsgsNative.count",
                          query = "SELECT count(*) as cnt FROM eazyschool.contact_msg WHERE status = :status",
                          resultSetMapping = "SqlResultSetMapping.count"),
        @NamedNativeQuery(name = "Contact.updateMsgStatusNative",
                          query = "UPDATE eazyschool.contact_msg c SET c.status = :status WHERE c.contact_id = :id")

})
public class Contact extends BaseEntity{

    @Id
    private int contactId;

    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Mobile number must not be blank")
    @Pattern(regexp = "^$|[0-9]{10}", message = "Mobile number must be 10 digits")
    @Column(name = "mobile_num")
    private String mobileNum;

    @NotBlank(message = "email must not be blank")
    @Email(message = "Please provide a valid email address")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "subject must not be blank")
    @Size(min = 5, message = "Subject must be at least 3 characters long")
    @Column(name = "subject")
    private String subject;

    @NotBlank(message = "message must not be blank")
    @Size(min = 10, message = "Message must be at least 3 characters long")
    @Column(name = "message")
    private String message;

    @Column(name = "status")
    private String status;

}
