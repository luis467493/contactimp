package com.leeg.contactimp.util;

import com.leeg.contactimp.dto.UserDto;
import com.leeg.contactimp.model.UserModel;
import org.junit.Assert;
import org.junit.Test;

public class UserUtilTest {

    @Test
    public void getUserModelFromDto() {
        UserDto userDto = TestUtil.getUserDto();

        UserModel userModel = UserUtil.getUserModelFromDto(userDto);

        checkDtoVsModel(userModel, userDto);
    }

    @Test
    public void getUserDtoFromModel() {
        UserModel userModel = TestUtil.getUserModel();

        UserDto userDto = UserUtil.getUserDtoFromModel(userModel);

        checkDtoVsModel(userModel, userDto);
    }

    private void checkDtoVsModel(UserModel userModel, UserDto userDto) {
        Assert.assertEquals(userModel.getUsername(), userDto.getUsername());
        Assert.assertEquals(StringUtil.decodeString(userModel.getPassword()), userDto.getPassword());
    }


}
