package com.kn.controller;

import com.kn.model.ContactDTO;
import com.kn.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final ContactService service;

    public ContactController(ContactService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ContactDTO>> getContacts(@RequestParam(value = "pageNo", required = false) Integer pageNumber,
                                                        @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        List<ContactDTO> contactDTOS = service.getContacts(pageNumber, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(contactDTOS);
    }

    @GetMapping("/search-by-name")
    public ResponseEntity<List<ContactDTO>> getContactsByFirstName(@RequestParam("firstName") String firstName) {
        List<ContactDTO> contactDTOS = service.getContactsByFirstName(firstName);
        return ResponseEntity.status(HttpStatus.OK).body(contactDTOS);
    }
}
