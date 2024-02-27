package org.educa.airline.controllers;

import jakarta.validation.Valid;

import org.educa.airline.dto.CryptDTO;
import org.educa.airline.entity.CryptEntity;
import org.educa.airline.mappers.CryptMapper;
import org.educa.airline.services.CryptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping(path = "/crypt")
public class CryptController {
    private final CryptService cryptService;
    private final CryptMapper cryptMapper;

    @Autowired
    public CryptController(CryptService cryptService,
                           CryptMapper cryptMapper) {
        this.cryptService = cryptService;
        this.cryptMapper = cryptMapper;
    }


    @PostMapping(path = "/crypt")
    public ResponseEntity<CryptDTO> crypt(@RequestBody @Valid CryptDTO cryptDTO) {

        CryptEntity entity = cryptMapper.toEntity(cryptDTO);
        String mesage;
        try {
            mesage = cryptService.crypt(entity.getMessage());
        } catch (IllegalBlockSizeException | NoSuchPaddingException | BadPaddingException | NoSuchAlgorithmException |
                 InvalidKeyException e) {
            return ResponseEntity.status(500).build();
        }
        CryptDTO response = new CryptDTO(mesage);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping(path = "/decrypt")
    public ResponseEntity<CryptDTO> decrypt(@RequestBody @Valid CryptDTO cryptDTO) {
        CryptEntity entity = cryptMapper.toEntity(cryptDTO);
        String mesage;
        try {
            mesage = cryptService.decrypt(entity.getMessage());
        } catch (IllegalBlockSizeException | NoSuchPaddingException | BadPaddingException | NoSuchAlgorithmException |
                 InvalidKeyException e) {
            return ResponseEntity.status(500).build();
        }
        CryptDTO response = new CryptDTO(mesage);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping(path = "/hash")
    public ResponseEntity<CryptDTO> hash(@RequestBody @Valid CryptDTO cryptDTO) {

        Authentication auth = SecurityContextHolder.getContext() .getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();

        CryptEntity entity = cryptMapper.toEntity(cryptDTO);
        String mesage;
        try {
            mesage = cryptService.hash(entity.getMessage());
        } catch (NoSuchAlgorithmException e) {
            return ResponseEntity.status(500).build();
        }
        CryptDTO response = new CryptDTO(mesage);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping(path = "/test")
    public ResponseEntity<Void> testPost() {
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/test")
    public ResponseEntity<Void> testGet() {
        return ResponseEntity.ok().build();
    }



}
