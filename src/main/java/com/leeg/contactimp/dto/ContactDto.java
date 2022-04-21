package com.leeg.contactimp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactDto {
    private String name;
    private String dob;
    private String phone;
    private String address;
    private String creditCard;
    private String franchise;
    private String email;
}
