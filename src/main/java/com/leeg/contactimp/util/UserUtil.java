package com.leeg.contactimp.util;

import com.leeg.contactimp.dto.UserDto;
import com.leeg.contactimp.model.UserModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserUtil {

    public static UserModel getUserModelFromDto(UserDto userDto) {
        return UserModel.builder().
                username(userDto.getUsername()).
                password(StringUtil.codeString(userDto.getPassword())).build();
    }

    public static UserDto getUserDtoFromModel(UserModel userModel) {
        return UserDto.builder().
                username(userModel.getUsername()).
                password(StringUtil.decodeString(userModel.getPassword())).build();
    }
}
