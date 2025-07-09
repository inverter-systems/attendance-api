package com.inverter.attendance.dto;

import com.inverter.attendance.enums.AddressType;
import lombok.Data;

@Data
public class AddressDTO {
    private Long id;
    private String street;
    private String number;
    private String city;
    private String state;
    private String zipCode;
    private AddressType type;
}
