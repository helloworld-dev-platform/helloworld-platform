package com.helloworld.backend_api.user.domain;

import com.helloworld.backend_api.common.domain.BaseTimeEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@NoArgsConstructor
@Schema(description = "회원 정보 Entity")
@Table(name = "Users")
@Getter
public class User extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @JdbcTypeCode(SqlTypes.BIGINT)
  @SequenceGenerator(
      name = "users_seq_generator",
      sequenceName = "users_seq", // 데이터베이스에 생성될 시퀀스 이름
      initialValue = 1,      // 시작 값
      allocationSize = 50     // 한 번에 할당할 시퀀스 수
  )
  private Long id;

  @Column(name = "USER_EMAIL", nullable = false, unique = true, length = 255)
  private String userEmail;

  @Column(name = "USER_NAME", length = 50)
  private String userName;

  @Column(name = "USER_ROLE", length = 50)
  private String userRole;

  @Enumerated(EnumType.STRING)
  @Column(name = "USER_STATUS")
  private UserStatus status;

  @Column(name = "TOTAL_POINT", nullable = false)
  private Integer totalPoint = 0;

  @Column(name = "TOTAL_STUDY_SECOND", nullable = false)
  private Long totalStudySecond;

  @Column(name = "LAST_LOGIN_AT", nullable = false)
  private LocalDateTime lastLoginAt;

  @Builder
  public User(Long id, String userEmail, String userName, String userRole,
      UserStatus status, Integer totalPoint, Long totalStudySecond) {
    this.id = id;
    this.userEmail = userEmail;
    this.userName = userName;
    this.userRole = userRole != null ? userRole : "USER"; // 기본값 설정
    this.status = status;
    this.totalPoint = totalPoint; // 초기 포인트는 0으로 설정
    this.lastLoginAt = LocalDateTime.now(); // 생성 시점의 로그인 시간 기록
    this.totalStudySecond = totalStudySecond;
  }

}
