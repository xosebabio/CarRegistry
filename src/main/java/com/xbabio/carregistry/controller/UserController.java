package com.xbabio.carregistry.controller;

import com.xbabio.carregistry.controller.dtos.LoginRequest;
import com.xbabio.carregistry.controller.dtos.SignUpRequest;
import com.xbabio.carregistry.service.UserService;
import com.xbabio.carregistry.service.impl.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final AuthenticationService authenticationService;

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> singup(@RequestBody SignUpRequest request) {
       try {
           return ResponseEntity.ok(authenticationService.signup(request));
       }
       catch (Exception e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
       }
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @PostMapping("/userimage/{id}")
    public ResponseEntity<?> uploadImage(@PathVariable Long id, @RequestParam("image") MultipartFile image) {
        try {
            if(!image.getContentType().equals("image/jpeg") && !image.getContentType().equals("image/png")){
                log.info("Invalid image format");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            userService.uploadImage(id, image);
            return ResponseEntity.ok().build();
        }
        catch (Exception e) {
            log.info("Error uploading image");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/userimage/{id}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable Long id) {
        try {
            byte[] imageBytes = userService.downloadImage(id);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
