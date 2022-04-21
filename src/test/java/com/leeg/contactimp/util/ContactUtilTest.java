package com.leeg.contactimp.util;

import com.leeg.contactimp.dto.ContactDto;
import com.leeg.contactimp.model.ContactModel;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ContactUtilTest {

    @Test
    public void getContactDtoFromModel() {
        ContactModel contactModel = getContactModel();

        ContactDto contactDto = ContactUtil.getContactDtoFromModel(contactModel);

        checkDtoVsModel(contactModel, contactDto);
    }

    @Test
    public void getContactDtoListFromListModel() {
        List<ContactModel> contactModels = List.of(getContactModel());

        List<ContactDto> contactDtos = ContactUtil.getContactDtoListFromListModel(contactModels);

        Assert.assertEquals(contactModels.size(), contactDtos.size());
        checkDtoVsModel(contactModels.get(0), contactDtos.get(0));
    }

    private void checkDtoVsModel(ContactModel contactModel, ContactDto contactDto) {
        Assert.assertEquals(contactModel.getAddress(), contactDto.getAddress());
        Assert.assertEquals(contactModel.getDob(), contactDto.getDob());
        Assert.assertEquals(contactModel.getCreditCard(), contactDto.getCreditCard());
        Assert.assertEquals(contactModel.getEmail(), contactDto.getEmail());
        Assert.assertEquals(contactModel.getFranchise(), contactDto.getFranchise());
        Assert.assertEquals(contactModel.getName(), contactDto.getName());
        Assert.assertEquals(contactModel.getPhone(), contactDto.getPhone());
    }

    private ContactModel getContactModel() {
        return ContactModel.builder().address("a").dob("b").creditCard("c")
                .email("d").franchise("e").name("f").phone("g").build();
    }

}
