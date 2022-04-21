package com.leeg.contactimp.util;

import com.leeg.contactimp.dto.UserDto;
import com.leeg.contactimp.model.UserModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestUtil {
    public static UserModel getUserModel() {
        return UserModel.builder().username("abcd").password(StringUtil.codeString("abcdP")).build();
    }

    public static UserDto getUserDto() {
        return UserDto.builder().username("abcd").password(StringUtil.codeString("abcdP")).build();
    }

    public static String getBadToken() {
        return "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9." +
                "eyJleHAiOjE2NTA1MDQ3NjUsInVzZXIiOnsieHl6IjoiTHVpc1AifX0." +
                "PaFVYOahYmZNNLM9sIvP9mOaeM20q58g7ezEtr5OL1Y";
    }

    public static String getToken() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9." +
                "eyJleHAiOjE2NTA1MDQ3NjUsInVzZXIiOnsidXNlcm5hbWUiOiJMdWlzIiwicGFzc3dvcmQiOiJMdWlzUCJ9fQ." +
                "0YrrM1LuK9_IzU7B2AWbGJsqSm_beVH9_VEHYTljOos";
        return token;
    }
}
