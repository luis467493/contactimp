package com.leeg.contactimp.service;

import com.leeg.contactimp.dto.UserDto;
import com.leeg.contactimp.exceptions.TokenExceptions;
import com.leeg.contactimp.exceptions.UserExceptions;
import com.leeg.contactimp.model.UserModel;
import com.leeg.contactimp.repository.IContactRepo;
import com.leeg.contactimp.repository.IUserContactRepo;
import com.leeg.contactimp.repository.IUserRepo;
import com.leeg.contactimp.util.StringUtil;
import com.leeg.contactimp.util.TestUtil;
import com.leeg.contactimp.util.TokenUtil;
import com.leeg.contactimp.util.UserUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.UUID;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    IUserRepo userRepo;

    @Mock
    IContactRepo contactRepo;

    @Mock
    IUserContactRepo userContactRepo;

    @InjectMocks
    UserService userService = new UserService(userRepo, contactRepo, userContactRepo);

    @Test
    public void register() {
        UserDto userDto = TestUtil.getUserDto();

        UserModel userModel = UserUtil.getUserModelFromDto(userDto);
        UserModel userModel1 = UserUtil.getUserModelFromDto(userDto);
        userModel1.setId(UUID.randomUUID());
        when(userRepo.save(userModel)).thenReturn(userModel1);

        userDto = userService.register(userDto);

        Assert.assertEquals(userDto.getUsername(), TestUtil.getUserDto().getUsername());
        Assert.assertEquals(userDto.getPassword(), TestUtil.getUserDto().getPassword());
    }

    @Test
    public void loginThrowsUserNotFoundExcp() {
        UserDto userDto = TestUtil.getUserDto();

        Assert.assertThrows(UserExceptions.UserNotFound.class, () -> {
            userService.login(userDto.getUsername(), userDto.getPassword());
        });
    }

    @Test
    public void login() throws UserExceptions.UserNotFound, TokenExceptions.UserNotFoundInToken {
        UserDto userDto = TestUtil.getUserDto();

        UserModel userModel = UserUtil.getUserModelFromDto(userDto);

        when(userRepo.findByUsernameAndAndPassword(userDto.getUsername(), StringUtil.codeString(userDto.getPassword()))).thenReturn(userModel);

        String token = userService.login(userDto.getUsername(), userDto.getPassword());
        UserDto userDtoResult = TokenUtil.getUserDtoFromToken(token);

        Assert.assertEquals(userDto.getUsername(), userDtoResult.getUsername());
    }

    @Test
    public void uploadCsv() {
    }
}
