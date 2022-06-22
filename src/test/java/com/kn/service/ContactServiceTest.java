package com.kn.service;

import com.kn.entity.ContactEntity;
import com.kn.mapper.ContactMapper;
import com.kn.model.ContactDTO;
import com.kn.repository.ContactRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ContactServiceTest {

    @Mock
    ContactRepository repository;

    @Mock
    ContactMapper mapper;

    @InjectMocks
    ContactService service;

    @Test
    void getContacts() {
        //given
        ContactEntity entity = ContactEntity.builder()
                .id(1L)
                .firstName("John")
                .lastName("Wick")
                .image("base64-image").build();
        List<ContactEntity> entities = List.of(entity, entity);
        Page<ContactEntity> page = new PageImpl<>(entities);
        given(repository.findAll(any(Pageable.class))).willReturn(page);
        ContactDTO contactDTO = ContactDTO.builder()
                .firstName("John")
                .lastName("Wick").build();
        given(mapper.toContactDTOs(entities)).willReturn(List.of(contactDTO, contactDTO));

        //when
        List<ContactDTO> result = service.getContacts(1, 1);

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getFirstName()).isEqualTo("John");
        then(repository).should(times(1)).findAll(any(Pageable.class));
    }

    @Test
    void getContactsByFirstName() {
        //given
        ContactEntity entity = ContactEntity.builder()
                .id(1L)
                .firstName("John")
                .lastName("Wick")
                .image("base64-image").build();
        List<ContactEntity> entities = List.of(entity);
        given(repository.findAllByFirstName("John")).willReturn(entities);
        ContactDTO contactDTO = ContactDTO.builder()
                .firstName("John")
                .lastName("Wick").build();
        given(mapper.toContactDTOs(entities)).willReturn(List.of(contactDTO));

        //when
        List<ContactDTO> result = service.getContactsByFirstName("John");

        //then
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getFirstName()).isEqualTo("John");
        then(repository).should(times(1)).findAllByFirstName(anyString());
    }
}