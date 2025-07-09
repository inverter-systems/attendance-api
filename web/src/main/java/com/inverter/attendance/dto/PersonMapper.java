package com.inverter.attendance.dto;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.inverter.attendance.entity.Address;
import com.inverter.attendance.entity.ContactInfo;
import com.inverter.attendance.entity.LegalPerson;
import com.inverter.attendance.entity.NaturalPerson;
import com.inverter.attendance.entity.Person;

@Component
public class PersonMapper {

    // --- DTO to Entity --- //
    public NaturalPerson toEntity(NaturalPersonDTO dto) {
        if (dto == null) {
            return null;
        }
        NaturalPerson entity = new NaturalPerson();
        entity.setId(dto.getId());
        entity.setFullName(dto.getFullName());
        entity.setSsn(dto.getSsn());
        entity.setBirthDate(dto.getBirthDate());
        entity.setGender(dto.getGender());
        entity.setNationality(dto.getNationality());
        entity.setMaritalStatus(dto.getMaritalStatus());
        entity.setProfessionalOccupation(dto.getProfessionalOccupation());
        entity.setUser(dto.getUser());

        // Set bidirectional relationship
        List<Address> addresses = toAddressEntityList(dto.getAddresses());
        addresses.forEach(address -> address.setPerson(entity));
        entity.setAddresses(addresses);

        List<ContactInfo> contacts = toContactInfoEntityList(dto.getContacts());
        contacts.forEach(contact -> contact.setPerson(entity));
        entity.setContacts(contacts);

        return entity;
    }

    public Address toEntity(AddressDTO dto) {
        if (dto == null) {
            return null;
        }
        Address entity = new Address();
        entity.setId(dto.getId());
        entity.setStreet(dto.getStreet());
        entity.setNumber(dto.getNumber());
        entity.setCity(dto.getCity());
        entity.setState(dto.getState());
        entity.setZipCode(dto.getZipCode());
        entity.setType(dto.getType());
        return entity;
    }

    public ContactInfo toEntity(ContactInfoDTO dto) {
        if (dto == null) {
            return null;
        }
        ContactInfo entity = new ContactInfo();
        entity.setId(dto.getId());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        return entity;
    }

    public List<Address> toAddressEntityList(List<AddressDTO> dtos) {
        if (dtos == null) {
            return Collections.emptyList();
        }
        return dtos.stream().map(this::toEntity).toList();
    }

    public List<ContactInfo> toContactInfoEntityList(List<ContactInfoDTO> dtos) {
        if (dtos == null) {
            return Collections.emptyList();
        }
        return dtos.stream().map(this::toEntity).toList();
    }

    public LegalPerson toEntity(LegalPersonDTO dto) {
        if (dto == null) {
            return null;
        }
        LegalPerson entity = new LegalPerson();
        entity.setId(dto.getId());
        entity.setFullName(dto.getFullName()); // Corporate name
        entity.setTradeName(dto.getTradeName());
        entity.setEin(dto.getEin());
        entity.setStateRegistration(dto.getStateRegistration());
        entity.setMunicipalRegistration(dto.getMunicipalRegistration());
        entity.setFoundationDate(dto.getFoundationDate());
        entity.setCompanySize(dto.getCompanySize());
        entity.setLegalNature(dto.getLegalNature());
        entity.setPrimaryActivity(dto.getPrimaryActivity());
        entity.setSecondaryActivities(dto.getSecondaryActivities());
        entity.setUser(dto.getUser());

        // Set bidirectional relationship
        List<Address> addresses = toAddressEntityList(dto.getAddresses());
        addresses.forEach(address -> address.setPerson(entity));
        entity.setAddresses(addresses);

        List<ContactInfo> contacts = toContactInfoEntityList(dto.getContacts());
        contacts.forEach(contact -> contact.setPerson(entity));
        entity.setContacts(contacts);

        return entity;
    }

    // --- Entity to DTO --- //
    public PersonDTO toDto(Person person) {
        if (person instanceof NaturalPerson naturalPerson) {
            return toDto(naturalPerson);
        } else if (person instanceof LegalPerson legalPerson) {
            return toDto(legalPerson);
        }
        return null; // Or a generic PersonDTO if you have one
    }

    public NaturalPersonDTO toDto(NaturalPerson entity) {
        if (entity == null) {
            return null;
        }
        NaturalPersonDTO dto = new NaturalPersonDTO();
        populatePersonDetails(dto, entity);

        dto.setSsn(entity.getSsn());
        dto.setBirthDate(entity.getBirthDate());
        dto.setGender(entity.getGender());
        dto.setNationality(entity.getNationality());
        dto.setMaritalStatus(entity.getMaritalStatus());
        dto.setProfessionalOccupation(entity.getProfessionalOccupation());

        return dto;
    }

    public LegalPersonDTO toDto(LegalPerson entity) {
        if (entity == null) {
            return null;
        }
        LegalPersonDTO dto = new LegalPersonDTO();
        populatePersonDetails(dto, entity);

        dto.setTradeName(entity.getTradeName());
        dto.setEin(entity.getEin());
        dto.setStateRegistration(entity.getStateRegistration());
        dto.setMunicipalRegistration(entity.getMunicipalRegistration());
        dto.setFoundationDate(entity.getFoundationDate());
        dto.setCompanySize(entity.getCompanySize());
        dto.setLegalNature(entity.getLegalNature());
        dto.setPrimaryActivity(entity.getPrimaryActivity());
        dto.setSecondaryActivities(entity.getSecondaryActivities());

        return dto;
    }

    private void populatePersonDetails(PersonDTO dto, Person person) {
        dto.setId(person.getId());
        dto.setFullName(person.getFullName());
        dto.setAddresses(toAddressDtoList(person.getAddresses()));
        dto.setContacts(toContactInfoDtoList(person.getContacts()));
    }

    public AddressDTO toDto(Address address) {
        if (address == null) {
            return null;
        }
        AddressDTO dto = new AddressDTO();
        dto.setId(address.getId());
        dto.setStreet(address.getStreet());
        dto.setNumber(address.getNumber());
        dto.setCity(address.getCity());
        dto.setState(address.getState());
        dto.setZipCode(address.getZipCode());
        dto.setType(address.getType());
        return dto;
    }

    public ContactInfoDTO toDto(ContactInfo contactInfo) {
        if (contactInfo == null) {
            return null;
        }
        ContactInfoDTO dto = new ContactInfoDTO();
        dto.setId(contactInfo.getId());
        dto.setEmail(contactInfo.getEmail());
        dto.setPhone(contactInfo.getPhone());
        return dto;
    }

    public List<AddressDTO> toAddressDtoList(List<Address> entities) {
        if (entities == null) {
            return Collections.emptyList();
        }
        return entities.stream().map(this::toDto).toList();
    }

    public List<ContactInfoDTO> toContactInfoDtoList(List<ContactInfo> entities) {
        if (entities == null) {
            return Collections.emptyList();
        }
        return entities.stream().map(this::toDto).toList();
    }
}
