package org.educa.airline.services;


import org.educa.airline.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class SecurityService {
    private final SecurityUtil securityUtil;

    @Autowired
    public SecurityService(SecurityUtil securityUtil) {
        this.securityUtil = securityUtil;
    }

    public String crypt(String message) throws IllegalBlockSizeException, NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        return securityUtil.crypt(message);
    }

    public String decrypt(String message) throws IllegalBlockSizeException, NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        return securityUtil.decrypt(message);
    }

    public String hash(String message) throws NoSuchAlgorithmException {
        return securityUtil.createHash(message);
    }



}
