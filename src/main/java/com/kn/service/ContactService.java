package com.kn.service;

import com.kn.entity.ContactEntity;
import com.kn.mapper.ContactMapper;
import com.kn.model.ContactDTO;
import com.kn.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ContactService {

    private final ContactMapper mapper;
    private final ContactRepository repository;

    public ContactService(ContactMapper mapper,
                          ContactRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public List<ContactDTO> getContacts(Integer pageNumber, Integer pageSize) {
        List<ContactEntity> entities = List.of();
        if (pageNumber != null && pageSize != null) {
            Pageable pageable = PageRequest.of(pageNumber, pageSize);
            Page<ContactEntity> page= repository.findAll(pageable);
            if (page.hasContent()) entities = page.getContent();
        } else {
            entities = repository.findAll();
        }
        return mapper.toContactDTOs(entities);
    }

    public List<ContactDTO> getContactsByFirstName(String firstName) {
        List<ContactEntity> contactEntities = repository.findAllByFirstName(firstName);
        return mapper.toContactDTOs(contactEntities);
    }
}
