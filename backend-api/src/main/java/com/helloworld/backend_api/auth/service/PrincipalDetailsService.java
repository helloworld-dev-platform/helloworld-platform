package com.helloworld.backend_api.auth.service;

import com.helloworld.backend_api.auth.model.PrincipalDetails;
import com.helloworld.backend_api.user.domain.User;
import com.helloworld.backend_api.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PrincipalDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    log.info(">>>>>>>>>>>>>>>>>  username={}", username);
    User userEntity = userRepository.findByUserName(username);

    if (userEntity != null) {
      return new PrincipalDetails(userEntity);
    }

    return null;
  }
}
