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
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    log.info(">>>>>>>>>>>>>>>>>  username(email)={}", email);
    User userEntity = userRepository.findByUserEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException(email + "을(를) 데이터베이스에서 찾을 수 없습니다."));

    return new PrincipalDetails(userEntity);
  }
}
