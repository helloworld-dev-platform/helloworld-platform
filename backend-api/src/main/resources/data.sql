-- #######################################################
-- 1. LEARNING_LANGUAGE (학습 언어) - ID 1:Python, ID 2:Java, ID 3:JavaScript
-- #######################################################
INSERT INTO learning_language (LANGUAGE_NAME, CREATED_AT, UPDATED_AT)
VALUES ('Python', NOW(), NOW()), -- ID 1
       ('Java', NOW(), NOW()),   -- ID 2
       ('JavaScript', NOW(), NOW());
-- ID 3

-- #######################################################
-- 2. STEPUP_COURSE (스텝업 코스) - ID 1~4
-- #######################################################
INSERT INTO stepup_course (learning_language_id, COURSE_NAME, CREATED_AT, UPDATED_AT)
VALUES (1, 'Python 기초 문법 완벽 마스터', NOW(), NOW()), -- ID 1
       (1, 'Python 데이터 구조와 알고리즘', NOW(), NOW()), -- ID 2
       (2, 'Java 프로그래밍 핵심 입문', NOW(), NOW()),    -- ID 3
       (3, 'JavaScript와 웹 프론트엔드 기초', NOW(), NOW());
-- ID 4

-- #######################################################
-- 3. STEPUP_SECTION (스텝업 섹션) - ID 1~8
-- #######################################################
INSERT INTO stepup_section (STEPUP_COURSE_ID, SECTION_NAME, SECTION_ORDER, CREATED_AT,
                            UPDATED_AT)
VALUES (1, '1장. 변수와 자료형', 1, NOW(), NOW()),      -- ID 1
       (1, '2장. 조건문과 반복문', 2, NOW(), NOW()),     -- ID 2
       (2, '1장. 리스트와 튜플 활용', 1, NOW(), NOW()),   -- ID 3
       (2, '2장. 딕셔너리와 집합', 2, NOW(), NOW()),     -- ID 4
       (3, '1장. 클래스와 객체 지향', 1, NOW(), NOW()),   -- ID 5
       (3, '2장. 상속과 다형성', 2, NOW(), NOW()),      -- ID 6
       (4, '1장. 자바스크립트 기본 문법', 1, NOW(), NOW()), -- ID 7
       (4, '2장. DOM 조작 입문', 2, NOW(), NOW());
-- ID 8

-- #######################################################
-- 4. STEPUP_STEP (스텝업 스텝) - ID 1~16
-- #######################################################
-- 섹션 1 (ID 1, Python 변수)
INSERT INTO stepup_step (STEPUP_SECTION_ID, STEP_TITLE, STEP_ORDER, CREATED_AT, UPDATED_AT)
VALUES (1, '변수 선언과 초기화', 1, NOW(), NOW()), -- ID 1
       (1, '문자열 타입 다루기', 2, NOW(), NOW());
-- ID 2

-- 섹션 2 (ID 2, Python 조건/반복)
INSERT INTO stepup_step (STEPUP_SECTION_ID, STEP_TITLE, STEP_ORDER, CREATED_AT, UPDATED_AT)
VALUES (2, 'IF/ELIF/ELSE 구조 이해', 1, NOW(), NOW()), -- ID 3
       (2, 'FOR 반복문 기본', 2, NOW(), NOW());
-- ID 4

-- 섹션 3 (ID 3, Python 리스트)
INSERT INTO stepup_step (STEPUP_SECTION_ID, STEP_TITLE, STEP_ORDER, CREATED_AT, UPDATED_AT)
VALUES (3, '리스트 생성 및 접근', 1, NOW(), NOW()), -- ID 5
       (3, '튜플의 불변성 이해', 2, NOW(), NOW());
-- ID 6

-- 섹션 4 (ID 4, Python 딕셔너리)
INSERT INTO stepup_step (STEPUP_SECTION_ID, STEP_TITLE, STEP_ORDER, CREATED_AT, UPDATED_AT)
VALUES (4, '딕셔너리 키와 값', 1, NOW(), NOW()), -- ID 7
       (4, '집합 연산자 활용', 2, NOW(), NOW());
-- ID 8

-- 섹션 5 (ID 5, Java 클래스)
INSERT INTO stepup_step (STEPUP_SECTION_ID, STEP_TITLE, STEP_ORDER, CREATED_AT, UPDATED_AT)
VALUES (5, '객체 생성과 메서드', 1, NOW(), NOW()), -- ID 9
       (5, '생성자 이해하기', 2, NOW(), NOW());
-- ID 10

-- 섹션 6 (ID 6, Java 상속)
INSERT INTO stepup_step (STEPUP_SECTION_ID, STEP_TITLE, STEP_ORDER, CREATED_AT, UPDATED_AT)
VALUES (6, 'extends 키워드 사용', 1, NOW(), NOW()), -- ID 11
       (6, '메서드 오버라이딩', 2, NOW(), NOW());
-- ID 12

-- 섹션 7 (ID 7, JS 기본)
INSERT INTO stepup_step (STEPUP_SECTION_ID, STEP_TITLE, STEP_ORDER, CREATED_AT, UPDATED_AT)
VALUES (7, 'var, let, const 차이', 1, NOW(), NOW()), -- ID 13
       (7, '함수와 스코프', 2, NOW(), NOW());
-- ID 14

-- 섹션 8 (ID 8, JS DOM)
INSERT INTO stepup_step (STEPUP_SECTION_ID, STEP_TITLE, STEP_ORDER, CREATED_AT, UPDATED_AT)
VALUES (8, 'getElementById 사용', 1, NOW(), NOW()), -- ID 15
       (8, '이벤트 리스너 추가', 2, NOW(), NOW());
-- ID 16


-- #######################################################
-- 5. PROBLEMS (문제) - 총 16개 (각 스텝당 1~2개)
-- #######################################################
-- 스텝 1 (ID 1): Python 변수 선언
INSERT INTO PROBLEM (LEARNING_LANGUAGE_ID, STEPUP_STEP_ID, PROBLEM_TYPE, CONTENT, DOMAIN_TYPE,
                     DIFFICULTY, CREATED_AT, UPDATED_AT)
VALUES (1, 1, 'MULTIPLE_CHOICE', 'Python에서 정수 10을 변수 "count"에 할당하는 올바른 코드는?', 'STEPUP', 'EASY',
        NOW(), NOW()), -- P_ID 1
       (1, 1, 'SUBJECTIVE', 'Python에서 두 변수 `a=5`와 `b=3`의 값을 서로 바꾸는 코드를 작성하세요.', 'STEPUP', 'MEDIUM',
        NOW(), NOW());
-- P_ID 2

-- 스텝 2 (ID 2): Python 문자열
INSERT INTO PROBLEM (LEARNING_LANGUAGE_ID, STEPUP_STEP_ID, PROBLEM_TYPE, CONTENT, DOMAIN_TYPE,
                     DIFFICULTY, CREATED_AT, UPDATED_AT)
VALUES (1, 2, 'MULTIPLE_CHOICE', 'Python 문자열 "Hello World"의 길이는?', 'STEPUP', 'EASY', NOW(), NOW());
-- P_ID 3

-- 스텝 3 (ID 3): Python IF문
INSERT INTO PROBLEM (LEARNING_LANGUAGE_ID, STEPUP_STEP_ID, PROBLEM_TYPE, CONTENT, DOMAIN_TYPE,
                     DIFFICULTY, CREATED_AT, UPDATED_AT)
VALUES (1, 3, 'MULTIPLE_CHOICE', 'Python에서 조건문 작성 시 반드시 필요한 요소는?', 'STEPUP', 'EASY', NOW(), NOW());
-- P_ID 4

-- 스텝 4 (ID 4): Python FOR문
INSERT INTO PROBLEM (LEARNING_LANGUAGE_ID, STEPUP_STEP_ID, PROBLEM_TYPE, CONTENT, DOMAIN_TYPE,
                     DIFFICULTY, CREATED_AT, UPDATED_AT)
VALUES (1, 4, 'SUBJECTIVE', '1부터 5까지의 숫자를 출력하는 Python FOR문 코드를 작성하세요.', 'STEPUP', 'MEDIUM', NOW(),
        NOW());
-- P_ID 5

-- 스텝 5 (ID 5): Python 리스트
INSERT INTO PROBLEM (LEARNING_LANGUAGE_ID, STEPUP_STEP_ID, PROBLEM_TYPE, CONTENT, DOMAIN_TYPE,
                     DIFFICULTY, CREATED_AT, UPDATED_AT)
VALUES (1, 5, 'MULTIPLE_CHOICE', 'Python 리스트 `[1, 2, 3]`에서 두 번째 요소 "2"를 접근하는 인덱스는?', 'STEPUP',
        'EASY', NOW(), NOW());
-- P_ID 6

-- 스텝 9 (ID 9): Java 객체 생성
INSERT INTO PROBLEM (LEARNING_LANGUAGE_ID, STEPUP_STEP_ID, PROBLEM_TYPE, CONTENT, DOMAIN_TYPE,
                     DIFFICULTY, CREATED_AT, UPDATED_AT)
VALUES (2, 9, 'MULTIPLE_CHOICE', 'Java에서 `MyClass`의 객체를 생성하는 올바른 코드는?', 'STEPUP', 'EASY', NOW(),
        NOW());
-- P_ID 7

-- 스텝 13 (ID 13): JavaScript 변수
INSERT INTO PROBLEM (LEARNING_LANGUAGE_ID, STEPUP_STEP_ID, PROBLEM_TYPE, CONTENT, DOMAIN_TYPE,
                     DIFFICULTY, CREATED_AT, UPDATED_AT)
VALUES (3, 13, 'MULTIPLE_CHOICE', 'JavaScript에서 재할당이 불가능한 변수 선언 키워드는?', 'STEPUP', 'MEDIUM', NOW(),
        NOW());
-- P_ID 8

-- 나머지 스텝 (ID 6, 7, 8, 10, 11, 12, 14, 15, 16)에도 문제 1개씩 추가 (총 16개 문제)
INSERT INTO PROBLEM (LEARNING_LANGUAGE_ID, STEPUP_STEP_ID, PROBLEM_TYPE, CONTENT, DOMAIN_TYPE,
                     DIFFICULTY, CREATED_AT, UPDATED_AT)
VALUES (1, 6, 'MULTIPLE_CHOICE', 'Python 튜플이 리스트와 다른 가장 큰 특징은?', 'STEPUP', 'EASY', NOW(),
        NOW()),                  -- P_ID 9
       (1, 7, 'MULTIPLE_CHOICE', 'Python 딕셔너리에서 키-값 쌍을 접근하는 메서드는?', 'STEPUP', 'MEDIUM', NOW(),
        NOW()),                  -- P_ID 10
       (1, 8, 'MULTIPLE_CHOICE', 'Python 집합(Set)의 특징으로 옳지 않은 것은?', 'STEPUP', 'EASY', NOW(),
        NOW()),                  -- P_ID 11
       (2, 10, 'SUBJECTIVE', 'Java에서 매개변수가 없는 `Car` 클래스의 생성자 코드를 작성하세요.', 'STEPUP', 'MEDIUM', NOW(),
        NOW()),                  -- P_ID 12
       (2, 11, 'MULTIPLE_CHOICE', 'Java에서 상속받은 부모 클래스의 메서드를 재정의하는 것을 무엇이라 하는가?', 'STEPUP', 'EASY',
        NOW(), NOW()),           -- P_ID 13
       (2, 12, 'MULTIPLE_CHOICE', 'Java에서 `@Override` 어노테이션의 역할은?', 'STEPUP', 'EASY', NOW(),
        NOW()),                  -- P_ID 14
       (3, 14, 'SUBJECTIVE', 'JavaScript에서 변수 `x`를 10으로 설정하고 이를 콘솔에 출력하는 코드를 작성하세요.', 'STEPUP',
        'EASY', NOW(), NOW()),   -- P_ID 15
       (3, 15, 'MULTIPLE_CHOICE', 'HTML 요소의 ID를 이용하여 해당 요소를 가져오는 JavaScript 함수는?', 'STEPUP',
        'MEDIUM', NOW(), NOW()), -- P_ID 16
       (3, 16, 'SUBJECTIVE', 'JavaScript에서 ID가 "myButton"인 요소에 클릭 이벤트를 추가하는 코드를 작성하세요.', 'STEPUP',
        'HARD', NOW(), NOW());
-- P_ID 17


-- #######################################################
-- 6. CHOICES (객관식 선택지) - P_ID 1, 3, 4, 6, 7, 8, 9, 10, 11, 13, 14, 16에 연결
-- #######################################################

-- P_ID 1 (Python 변수 선언)
INSERT INTO CHOICE (PROBLEMS_ID, CONTENT, IS_CORRECT, CORRECT_DESCRIPTION, CREATED_AT, UPDATED_AT)
VALUES (1, 'count = 10', TRUE, 'Python은 별도의 키워드 없이 바로 할당합니다.', NOW(), NOW()),
       (1, 'var count = 10;', FALSE, NULL, NOW(), NOW()),
       (1, 'int count = 10;', FALSE, NULL, NOW(), NOW());

-- P_ID 3 (Python 문자열 길이)
INSERT INTO CHOICE (PROBLEMS_ID, CONTENT, IS_CORRECT, CORRECT_DESCRIPTION, CREATED_AT, UPDATED_AT)
VALUES (3, '11', TRUE, '공백을 포함하여 총 11글자입니다.', NOW(), NOW()),
       (3, '10', FALSE, NULL, NOW(), NOW()),
       (3, '12', FALSE, NULL, NOW(), NOW());

-- P_ID 6 (Python 리스트 인덱스)
INSERT INTO CHOICE (PROBLEMS_ID, CONTENT, IS_CORRECT, CORRECT_DESCRIPTION, CREATED_AT, UPDATED_AT)
VALUES (6, '1', TRUE, '리스트 인덱스는 0부터 시작합니다.', NOW(), NOW()),
       (6, '2', FALSE, NULL, NOW(), NOW()),
       (6, '3', FALSE, NULL, NOW(), NOW());

-- P_ID 7 (Java 객체 생성)
INSERT INTO CHOICE (PROBLEMS_ID, CONTENT, IS_CORRECT, CORRECT_DESCRIPTION, CREATED_AT, UPDATED_AT)
VALUES (7, 'MyClass obj = new MyClass();', TRUE, 'new 키워드를 사용하여 객체를 생성합니다.', NOW(), NOW()),
       (7, 'obj = MyClass.create();', FALSE, NULL, NOW(), NOW()),
       (7, 'var obj = new MyClass();', FALSE, NULL, NOW(), NOW());

-- P_ID 8 (JS 재할당 불가능 키워드)
INSERT INTO CHOICE (PROBLEMS_ID, CONTENT, IS_CORRECT, CORRECT_DESCRIPTION, CREATED_AT, UPDATED_AT)
VALUES (8, 'const', TRUE, 'const는 재할당이 불가능합니다.', NOW(), NOW()),
       (8, 'let', FALSE, NULL, NOW(), NOW()),
       (8, 'var', FALSE, NULL, NOW(), NOW());

-- P_ID 16 (JS DOM 요소 가져오기)
INSERT INTO CHOICE (PROBLEMS_ID, CONTENT, IS_CORRECT, CORRECT_DESCRIPTION, CREATED_AT, UPDATED_AT)
VALUES (16, 'document.getElementById()', TRUE, 'getElementById는 ID로 요소를 찾습니다.', NOW(), NOW()),
       (16, 'document.querySelector()', FALSE, NULL, NOW(), NOW()),
       (16, 'document.getElementsByClass()', FALSE, NULL, NOW(), NOW());


-- #######################################################
-- 7. SOLUTIONS (주관식 정답) - P_ID 2, 5, 12, 15, 17에 연결
-- #######################################################

-- P_ID 2 (Python 변수 값 교환)
INSERT INTO SOLUTION (PROBLEMS_ID, CONTENT, CORRECT_DESCRIPTION, CREATED_AT, UPDATED_AT)
VALUES (2, 'a, b = b, a', '파이썬의 튜플 언패킹을 이용한 간결한 방법입니다.', NOW(), NOW());

-- P_ID 5 (Python FOR문)
INSERT INTO SOLUTION (PROBLEMS_ID, CONTENT, CORRECT_DESCRIPTION, CREATED_AT, UPDATED_AT)
VALUES (5, 'for i in range(1, 6): print(i)', 'range(1, 6)은 1부터 5까지의 정수를 생성합니다.', NOW(), NOW());

-- P_ID 12 (Java 생성자)
INSERT INTO SOLUTION (PROBLEMS_ID, CONTENT, CORRECT_DESCRIPTION, CREATED_AT, UPDATED_AT)
VALUES (12, 'public Car() {}', '클래스 이름과 동일하며, 반환 타입이 없습니다.', NOW(), NOW());

-- P_ID 15 (JavaScript 콘솔 출력)
INSERT INTO SOLUTION (PROBLEMS_ID, CONTENT, CORRECT_DESCRIPTION, CREATED_AT, UPDATED_AT)
VALUES (15, 'let x = 10; console.log(x);', 'let 또는 const를 사용하여 변수를 선언합니다.', NOW(), NOW());

-- P_ID 17 (JS 이벤트 리스너)
INSERT INTO SOLUTION (PROBLEMS_ID, CONTENT, CORRECT_DESCRIPTION, CREATED_AT, UPDATED_AT)
VALUES (17, 'document.getElementById("myButton").addEventListener("click", function() {});',
        '가장 일반적인 이벤트 리스너 추가 방법입니다.', NOW(), NOW());


-- #######################################################
-- 8. HINT (힌트) - P_ID 1, 2, 5에 연결
-- #######################################################

-- P_ID 1 힌트
INSERT INTO hint (PROBLEMS_ID, CONTENT, HINT_ORDER, CREATED_AT, UPDATED_AT)
VALUES (1, 'Python은 변수 선언 시 자료형을 명시하지 않습니다.', 1, NOW(), NOW());

-- P_ID 2 힌트
INSERT INTO hint (PROBLEMS_ID, CONTENT, HINT_ORDER, CREATED_AT, UPDATED_AT)
VALUES (2, '파이썬에서는 임시 변수 없이 한 줄로 값을 교환할 수 있는 특별한 문법이 있습니다.', 1, NOW(), NOW());

-- P_ID 5 힌트
INSERT INTO hint (PROBLEMS_ID, CONTENT, HINT_ORDER, CREATED_AT, UPDATED_AT)
VALUES (5, 'range() 함수의 두 번째 인자는 포함되지 않습니다.', 1, NOW(), NOW());