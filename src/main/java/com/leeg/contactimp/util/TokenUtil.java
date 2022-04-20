package com.leeg.contactimp.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leeg.contactimp.dto.UserDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class TokenUtil {

    public static String codeString(UserDto userDto) {
        ObjectMapper oMapper = new ObjectMapper();
        Map<String, Object> map = oMapper.convertValue(userDto, Map.class);

        String secret = "contactimp";
        Algorithm algorithm = Algorithm.HMAC256(StringUtil.codeString(secret));

        Map<String, Object> payloadClaims = new HashMap<>();
        payloadClaims.put("user", map);

        return JWT.create().withExpiresAt(
                Date.from(ZonedDateTime.now().
                        plusMinutes(10).toInstant())).withPayload(payloadClaims).sign(algorithm);
    }
}
