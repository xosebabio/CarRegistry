package com.xbabio.carregistry.service.impl;

import com.xbabio.carregistry.entity.User;
import com.xbabio.carregistry.repository.UserRepository;
import com.xbabio.carregistry.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }

    public User save(User newUser) {
        return userRepository.save(newUser);
    }

    @Override
    public void uploadImage(Long id, MultipartFile image) throws IOException {
        User user = userRepository.findById(id).orElseThrow();
        log.info("{}", Base64.getEncoder().encodeToString(image.getBytes()).length());
        user.setImage(Base64.getEncoder().encodeToString(image.getBytes()));
        userRepository.save(user);
    }

    @Override
    public byte[] downloadImage(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return user.getImage().getBytes();
    }
}
