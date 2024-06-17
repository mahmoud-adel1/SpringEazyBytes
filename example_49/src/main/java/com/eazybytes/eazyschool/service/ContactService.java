package com.eazybytes.eazyschool.service;

import com.eazybytes.eazyschool.config.EazySchoolProps;
import com.eazybytes.eazyschool.constants.EazySchoolConstants;
import com.eazybytes.eazyschool.model.Contact;
import com.eazybytes.eazyschool.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ContactService {

    private ContactRepository contactRepository;

    @Autowired
    private EazySchoolProps eazySchoolProps;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Optional<Contact> findContactById(int contactId) {
        return contactRepository.findById(contactId);
    }

    public boolean saveMessageDetails(Contact contact) {
        boolean isSaved = false;
        contact.setStatus(EazySchoolConstants.OPEN);
        Contact savedContact = contactRepository.save(contact);
        log.info(contact.toString());
        if(savedContact != null && savedContact.getContactId()>0) {
            isSaved = true;
        }
        return isSaved;
    }

    public List<Contact> findMsgsByStatus(String status) {
        List<Contact> contactMsgs = contactRepository.findByStatus(status);
        return contactMsgs;
    }

    public Page<Contact> findMsgsWithOpenStatus(int pageNum,String sortField, String sortDir) {
        int pageSize = eazySchoolProps.getPageSize();
        if(eazySchoolProps.getContact() != null && eazySchoolProps.getContact().get("pageSize") != null) {
            pageSize = Integer.parseInt(eazySchoolProps.getContact().get("pageSize").trim());
        }


        Pageable pageable = PageRequest.of(pageNum,pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending());
        Page<Contact> contactMsgs = contactRepository.findOpenMsgsNative(EazySchoolConstants.OPEN,pageable);
        return contactMsgs;
    }

    public void deleteMsgById(int contactId) {
        contactRepository.deleteById(contactId);
    }

    public boolean updateMsgStatus(int contactId) {
        boolean isUpdated = false;
//        Optional<Contact> contact = contactRepository.findById(contactId);
//        contact.ifPresent(contact1 -> {
//            contact1.setStatus(EazySchoolConstants.CLOSE);
//        });

//        Contact updatedContact = contactRepository.save(contact.get());
        int rows = contactRepository.updateMsgStatusNative(EazySchoolConstants.CLOSE,contactId);

        if(rows > 0) {
            isUpdated = true;
        }
        return isUpdated;
    }
}
