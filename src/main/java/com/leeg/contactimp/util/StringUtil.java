package com.leeg.contactimp.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class StringUtil {

    public static String codeString(String string) {
        return new String(Base64.encodeBase64(string.getBytes()));
    }

    public static String decodeString(String string) {
        return new String(Base64.decodeBase64(string.getBytes()));
    }
}
