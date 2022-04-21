package com.leeg.contactimp.service;

import com.leeg.contactimp.dto.ContactDto;
import com.leeg.contactimp.exceptions.TokenExceptions;
import com.leeg.contactimp.model.ContactModel;
import com.leeg.contactimp.repository.IContactRepo;
import com.leeg.contactimp.util.ContactUtil;
import com.leeg.contactimp.util.TestUtil;
import com.leeg.contactimp.util.TokenUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ContactServiceTest {

    @Mock
    IContactRepo contactRepo;

    @InjectMocks
    ContactService contactService = new ContactService(contactRepo);

    @Test
    public void getContactsByUserThrowsInvalidTokenExcp() {
        Assert.assertThrows(TokenExceptions.InvalidToken.class, () -> {
            contactService.getContactsByUser(TestUtil.getToken());
        });
    }

    @Test
    public void getContactsByUserThrowsUserNotFoundInTokenExcp() throws TokenExceptions.InvalidToken, TokenExceptions.UserNotFoundInToken {
        String token = TokenUtil.generateToken(TestUtil.getUserDto());

        List<ContactModel> contacts = List.of(ContactModel.builder().phone("a1").build());
        List<ContactDto> contactDtosExpected = ContactUtil.getContactDtoListFromListModel(contacts);

        when(contactRepo.findByUser("abcd")).thenReturn(contacts);

        List<ContactDto> contactDtosResult = contactService.getContactsByUser(token);

        Assert.assertEquals(contactDtosExpected.size(), contactDtosResult.size());
        Assert.assertEquals(contactDtosExpected.get(0).getPhone(), contactDtosResult.get(0).getPhone());
    }
}
