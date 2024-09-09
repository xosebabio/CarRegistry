package com.xbabio.CarRegistry.service;


import com.xbabio.CarRegistry.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {

  public User save(User newUser);

  void uploadImage(Long id, MultipartFile image) throws IOException;

  byte[] downloadImage(Long id);
}
