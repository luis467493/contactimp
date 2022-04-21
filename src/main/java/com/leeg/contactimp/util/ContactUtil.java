package com.leeg.contactimp.util;

import com.leeg.contactimp.dto.ContactDto;
import com.leeg.contactimp.model.ContactModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class ContactUtil {

    public static ContactDto getContactDtoFromModel(ContactModel contactModel) {
        return ContactDto.builder().
                address(contactModel.getAddress()).
                dob(contactModel.getDob()).
                creditCard(contactModel.getCreditCard()).
                email(contactModel.getEmail()).
                phone(contactModel.getPhone()).
                name(contactModel.getName()).
                franchise(contactModel.getFranchise()).build();

    }

    public static List<ContactDto> getContactDtoListFromListModel(List<ContactModel> contactModels) {
        return contactModels.stream().map(ContactUtil::getContactDtoFromModel).collect(Collectors.toList());
    }
}
