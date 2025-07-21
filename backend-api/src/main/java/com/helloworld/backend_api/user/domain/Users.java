package com.helloworld.backend_api.user.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@Schema(description = "회원 정보 Entity")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long id;

    @Schema(description = "회원명")
    private String username;

    @Schema(description = "회원비밀번호")
    private String password;

    @Schema(description = "회원이메일주소")
    private String email;

    @Schema(description = "회원역할", example = "USER", defaultValue = "USER")
    private String role;
    @Schema(description = "OAuth제공자")
    private String provider;
    @Schema(description = "OAuth고유아이디")
    private String providerId;
    @Schema(description = "회원상태코드", example = "ACTIVE")
    private String userStatCd;

    @CreationTimestamp
    @Schema(description = "최초생성일시")
    private Timestamp createdAt;

    @CreationTimestamp
    @Schema(description = "마지막접속일시")
    private Timestamp lastLoginAt;

    @Builder
    public Users(Timestamp lastLoginAt, Timestamp createdAt, String userStatCd, String role,String providerId,String provider,String email, String password, String username){
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.provider = provider;
        this.providerId = providerId;
        this.userStatCd = userStatCd;
        this.createdAt = createdAt;
        this.lastLoginAt = lastLoginAt;
    }

}
