package com.helloworld.backend_api.achievement.domain;

import com.helloworld.backend_api.common.domain.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 업적 엔티티
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "achievement")
public class Achievement extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "ACHIEVE_NAME", length = 50)
  private String achieveName;

  @Column(name = "ACHIEVE_DESC", length = 256)
  private String achieveDesc;

  @Column(name = "ACHIEVE_MAX_LEVEL", nullable = false)
  private Integer achieveMaxLevel;

  @Column(name = "ACHIEVE_IMG_URL", columnDefinition = "TEXT")
  private String achieveImgUrl;
}
