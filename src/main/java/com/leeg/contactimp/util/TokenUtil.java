package com.leeg.contactimp.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leeg.contactimp.dto.UserDto;
import com.leeg.contactimp.exceptions.TokenExceptions;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.leeg.contactimp.constants.Constants.INVALID_TOKEN;
import static com.leeg.contactimp.constants.Constants.SECRET;
import static com.leeg.contactimp.constants.Constants.USER;
import static com.leeg.contactimp.constants.Constants.USER_NOT_FOUND_IN_TOKEN;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class TokenUtil {

    public static String codeString(UserDto userDto) {
        ObjectMapper oMapper = new ObjectMapper();
        Map<String, Object> map = oMapper.convertValue(userDto, Map.class);
        Algorithm algorithm = Algorithm.HMAC256(SECRET);

        Map<String, Object> payloadClaims = new HashMap<>();
        payloadClaims.put(USER, map);

        return JWT.create().withExpiresAt(
                Date.from(ZonedDateTime.now().
                        plusMinutes(10).toInstant())).withPayload(payloadClaims).sign(algorithm);
    }

    public static UserDto getUserDtoFromToken(String token) throws TokenExceptions.UserNotFoundInToken {
        ObjectMapper oMapper = new ObjectMapper();
        DecodedJWT jwt = JWT.decode(token);
        String user = jwt.getClaim(USER).toString();
        UserDto userDto;
        try {
            userDto = oMapper.readValue(user, UserDto.class);
        } catch (JsonProcessingException e) {
            throw new TokenExceptions.UserNotFoundInToken(USER_NOT_FOUND_IN_TOKEN);
        }
        return userDto;
    }

    public static void verifyToken(String token) throws TokenExceptions.InvalidToken {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            verifier.verify(token);
        }catch (Exception e){
            throw new TokenExceptions.InvalidToken(INVALID_TOKEN);
        }
    }
}
