package com.kraemer.domain.utils;

import java.security.SecureRandom;
import java.util.Base64;

public class TokenUtil {
    private final static Base64.Encoder base64Encoder = Base64.getUrlEncoder();

    public static String generateToken() {
        byte[] randomBytes = new byte[24];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }
}
