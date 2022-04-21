package com.leeg.contactimp.service;

import com.leeg.contactimp.dto.ContactDto;
import com.leeg.contactimp.dto.UserDto;
import com.leeg.contactimp.exceptions.TokenExceptions;
import com.leeg.contactimp.model.ContactModel;
import com.leeg.contactimp.repository.IContactRepo;
import com.leeg.contactimp.util.ContactUtil;
import com.leeg.contactimp.util.TokenUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ContactService {

    private IContactRepo contactRepo;

    public List<ContactDto> getContactsByUser(String token) throws TokenExceptions.InvalidToken,
            TokenExceptions.UserNotFoundInToken {
        TokenUtil.verifyToken(token);
        UserDto userDto = TokenUtil.getUserDtoFromToken(token);
        List<ContactModel> contacts = contactRepo.findByUser(userDto.getUsername());
        return ContactUtil.getContactDtoListFromListModel(contacts);
    }
}
