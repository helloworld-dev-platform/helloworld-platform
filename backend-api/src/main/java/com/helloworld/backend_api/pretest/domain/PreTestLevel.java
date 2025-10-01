package com.helloworld.backend_api.pretest.domain;

import com.helloworld.backend_api.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "PRE_TEST_LEVEL")
public class PreTestLevel extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pre_test_level_id_seq_generator")
    @SequenceGenerator(name = "pre_test_level_id_seq_generator", sequenceName = "pre_test_level_id_seq", allocationSize = 50)
    private Long id;

    @Column(name = "PRE_TEST_LEVEL_NAME", nullable = false)
    private String preTestLevelName;

    @Column(name = "PRE_TEST_END_MSG", columnDefinition = "TEXT")
    private String preTestEndMsg;
}
