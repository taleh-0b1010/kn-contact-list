package com.kn.mapper;

import com.kn.entity.ContactEntity;
import com.kn.model.ContactDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContactMapper {

    List<ContactDTO> toContactDTOs(List<ContactEntity> contactEntities);

    ContactDTO toContactDTO(ContactEntity contactEntity);
}
