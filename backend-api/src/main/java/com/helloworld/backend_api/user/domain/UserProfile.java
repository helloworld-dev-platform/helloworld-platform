package com.helloworld.backend_api.user.domain;

import com.helloworld.backend_api.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_profile")
public class UserProfile extends BaseTimeEntity {

  @Id
  @Column(name = "users_id")
  private Long id;

  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  @JoinColumn(name = "users_id")
  private User user;

  @Column(name = "NICKNAME", length = 50)
  private String nickname;

  @Column(name = "PROFILE_IMG_URL", columnDefinition = "TEXT")
  private String profileImgUrl;

}
