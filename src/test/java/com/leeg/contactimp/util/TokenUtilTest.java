package com.leeg.contactimp.util;

import com.leeg.contactimp.dto.UserDto;
import com.leeg.contactimp.exceptions.TokenExceptions;
import org.junit.Assert;
import org.junit.Test;

public class TokenUtilTest {

    @Test
    public void codeString() throws TokenExceptions.UserNotFoundInToken {
        UserDto userDto = TestUtil.getUserDto();

        String token = TokenUtil.generateToken(userDto);

        UserDto userDtoResult = TokenUtil.getUserDtoFromToken(token);

        Assert.assertEquals(userDtoResult.getUsername(), userDto.getUsername());
        Assert.assertEquals(userDtoResult.getPassword(), userDto.getPassword());
    }

    @Test
    public void getUserDtoFromTokenThrowsExcp() {
        Assert.assertThrows(TokenExceptions.UserNotFoundInToken.class, () -> {
            TokenUtil.getUserDtoFromToken(TestUtil.getBadToken());
        });
    }

    @Test
    public void getUserDtoFromToken() throws TokenExceptions.UserNotFoundInToken {
        UserDto userDto = TokenUtil.getUserDtoFromToken(TestUtil.getToken());

        Assert.assertNotNull(userDto.getUsername());
        Assert.assertNotNull(userDto.getPassword());
    }

    @Test
    public void verifyToken() {
        Assert.assertThrows(TokenExceptions.InvalidToken.class, () -> {
            TokenUtil.verifyToken(TestUtil.getToken());
        });
    }


}
