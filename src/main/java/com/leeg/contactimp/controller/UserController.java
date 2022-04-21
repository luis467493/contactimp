package com.leeg.contactimp.controller;

import com.leeg.contactimp.dto.UserDto;
import com.leeg.contactimp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping(value = "/user",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> register(@RequestBody UserDto userDto) {
        try {
            return new ResponseEntity<>(userService.register(userDto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/user",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> login(@RequestParam(name = "username") String username,
                                        @RequestParam(name = "password") String password) {
        try {
            return new ResponseEntity<>(userService.login(username, password), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/uploadCsv", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> report(@RequestParam("file") MultipartFile file,
                                        @RequestParam("dbColumns") String dbColumns,
                                        @RequestHeader("token") String token) {
        try {
            return new ResponseEntity<>(userService.uploadCsv(file, dbColumns, token), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
