-- #######################################################
-- 1. LEARNING_LANGUAGE (학습 언어)
-- #######################################################
INSERT INTO learning_language (language_name, created_at, updated_at)
VALUES ('Python', NOW(), NOW()), -- ID 1
       ('Java', NOW(), NOW()),   -- ID 2
       ('JavaScript', NOW(), NOW());
-- ID 3

-- #####################################################################
-- 2. STEPUP_LARGE_CATEGORY (스텝업 대분류)
-- #####################################################################
INSERT INTO stepup_large_category (learning_language_id, large_category_name, difficulty,
                                   created_at, updated_at)
VALUES (1, 'Python 기초 문법 완벽 마스터', 'LEVEL_1', NOW(), NOW()), -- ID 1
       (1, 'Python 데이터 구조와 알고리즘', 'LEVEL_4', NOW(), NOW()), -- ID 2
       (2, 'Java 프로그래밍 핵심 입문', 'LEVEL_1', NOW(), NOW()),    -- ID 3
       (3, 'JavaScript와 웹 프론트엔드 기초', 'LEVEL_1', NOW(), NOW());
-- ID 4

-- #####################################################################
-- 3. STEPUP_MEDIUM_CATEGORY (스텝업 중분류)
-- #####################################################################
INSERT INTO stepup_medium_category (stepup_large_category_id, medium_category_name,
                                    medium_category_order, created_at, updated_at)
VALUES (1, '1장. 변수와 자료형', 1, NOW(), NOW()),      -- ID 1
       (1, '2장. 조건문과 반복문', 2, NOW(), NOW()),     -- ID 2
       (2, '1장. 리스트와 튜플 활용', 1, NOW(), NOW()),   -- ID 3
       (2, '2장. 딕셔너리와 집합', 2, NOW(), NOW()),     -- ID 4
       (3, '1장. 클래스와 객체 지향', 1, NOW(), NOW()),   -- ID 5
       (3, '2장. 상속과 다형성', 2, NOW(), NOW()),      -- ID 6
       (4, '1장. 자바스크립트 기본 문법', 1, NOW(), NOW()), -- ID 7
       (4, '2장. DOM 조작 입문', 2, NOW(), NOW());
-- ID 8

-- #####################################################################
-- 4. STEPUP_SMALL_CATEGORY (스텝업 소분류)
-- #####################################################################
INSERT INTO stepup_small_category (stepup_medium_category_id, small_category_title,
                                   small_category_order, created_at, updated_at)
VALUES (1, '변수 선언과 초기화', 1, NOW(), NOW()),         -- ID 1
       (1, '문자열 타입 다루기', 2, NOW(), NOW()),         -- ID 2
       (2, 'IF/ELIF/ELSE 구조 이해', 1, NOW(), NOW()), -- ID 3
       (2, 'FOR 반복문 기본', 2, NOW(), NOW()),         -- ID 4
       (3, '리스트 생성 및 접근', 1, NOW(), NOW()),        -- ID 5
       (3, '튜플의 불변성 이해', 2, NOW(), NOW()),         -- ID 6
       (4, '딕셔너리 키와 값', 1, NOW(), NOW()),          -- ID 7
       (4, '집합 연산자 활용', 2, NOW(), NOW()),          -- ID 8
       (5, '객체 생성과 메서드', 1, NOW(), NOW()),         -- ID 9
       (5, '생성자 이해하기', 2, NOW(), NOW()),           -- ID 10
       (6, 'extends 키워드 사용', 1, NOW(), NOW()),     -- ID 11
       (6, '메서드 오버라이딩', 2, NOW(), NOW()),          -- ID 12
       (7, 'var, let, const 차이', 1, NOW(), NOW()), -- ID 13
       (7, '함수와 스코프', 2, NOW(), NOW()),            -- ID 14
       (8, 'getElementById 사용', 1, NOW(), NOW()),  -- ID 15
       (8, '이벤트 리스너 추가', 2, NOW(), NOW());
-- ID 16

-- #####################################################################
-- 5. PROBLEM (모든 문제 INSERT)
-- #####################################################################

-- ##### 5-1. 스텝업 문제 (P_ID 1 ~ 17) #####
INSERT INTO problem (learning_language_id, stepup_small_category_id, problem_type, content,
                     domain_type, difficulty, created_at, updated_at)
VALUES (1, 1, 'MULTIPLE_CHOICE', 'Python에서 정수 10을 변수 "count"에 할당하는 올바른 코드는?', 'STEPUP', 'LEVEL_1',
        NOW(), NOW()),            -- P_ID 1
       (1, 1, 'SUBJECTIVE', 'Python에서 두 변수 `a=5`와 `b=3`의 값을 서로 바꾸는 코드를 작성하세요.', 'STEPUP', 'LEVEL_4',
        NOW(), NOW()),            -- P_ID 2
       (1, 2, 'MULTIPLE_CHOICE', 'Python 문자열 "Hello World"의 길이는?', 'STEPUP', 'LEVEL_1', NOW(),
        NOW()),                   -- P_ID 3
       (1, 3, 'MULTIPLE_CHOICE', 'Python에서 조건문 작성 시 반드시 필요한 요소는?', 'STEPUP', 'LEVEL_2', NOW(),
        NOW()),                   -- P_ID 4
       (1, 4, 'SUBJECTIVE', '1부터 5까지의 숫자를 출력하는 Python FOR문 코드를 작성하세요.', 'STEPUP', 'LEVEL_4', NOW(),
        NOW()),                   -- P_ID 5
       (1, 5, 'MULTIPLE_CHOICE', 'Python 리스트 `[1, 2, 3]`에서 두 번째 요소 "2"를 접근하는 인덱스는?', 'STEPUP',
        'LEVEL_2', NOW(), NOW()), -- P_ID 6
       (2, 9, 'MULTIPLE_CHOICE', 'Java에서 `MyClass`의 객체를 생성하는 올바른 코드는?', 'STEPUP', 'LEVEL_1', NOW(),
        NOW()),                   -- P_ID 7
       (3, 13, 'MULTIPLE_CHOICE', 'JavaScript에서 재할당이 불가능한 변수 선언 키워드는?', 'STEPUP', 'LEVEL_5', NOW(),
        NOW()),                   -- P_ID 8
       (1, 6, 'MULTIPLE_CHOICE', 'Python 튜플이 리스트와 다른 가장 큰 특징은?', 'STEPUP', 'LEVEL_3', NOW(),
        NOW()),                   -- P_ID 9
       (1, 7, 'MULTIPLE_CHOICE', 'Python 딕셔너리에서 키-값 쌍을 접근하는 메서드는?', 'STEPUP', 'LEVEL_5', NOW(),
        NOW()),                   -- P_ID 10
       (1, 8, 'MULTIPLE_CHOICE', 'Python 집합(Set)의 특징으로 옳지 않은 것은?', 'STEPUP', 'LEVEL_3', NOW(),
        NOW()),                   -- P_ID 11
       (2, 10, 'SUBJECTIVE', 'Java에서 매개변수가 없는 `Car` 클래스의 생성자 코드를 작성하세요.', 'STEPUP', 'LEVEL_4',
        NOW(),
        NOW()),                   -- P_ID 12
       (2, 11, 'MULTIPLE_CHOICE', 'Java에서 상속받은 부모 클래스의 메서드를 재정의하는 것을 무엇이라 하는가?', 'STEPUP',
        'LEVEL_2',
        NOW(), NOW()),            -- P_ID 13
       (2, 12, 'MULTIPLE_CHOICE', 'Java에서 `@Override` 어노테이션의 역할은?', 'STEPUP', 'LEVEL_3', NOW(),
        NOW()),                   -- P_ID 14
       (3, 14, 'SUBJECTIVE', 'JavaScript에서 변수 `x`를 10으로 설정하고 이를 콘솔에 출력하는 코드를 작성하세요.', 'STEPUP',
        'LEVEL_1', NOW(), NOW()), -- P_ID 15
       (3, 15, 'MULTIPLE_CHOICE', 'HTML 요소의 ID를 이용하여 해당 요소를 가져오는 JavaScript 함수는?', 'STEPUP',
        'LEVEL_5', NOW(), NOW()), -- P_ID 16
       (3, 16, 'SUBJECTIVE', 'JavaScript에서 ID가 "myButton"인 요소에 클릭 이벤트를 추가하는 코드를 작성하세요.', 'STEPUP',
        'LEVEL_8', NOW(), NOW());
-- P_ID 17

-- ##### 5-2. 사전 레벨 테스트 - Python EASY (P_ID 18 ~ 27) #####
INSERT INTO problem (learning_language_id, stepup_small_category_id, problem_type, content,
                     domain_type, difficulty, created_at, updated_at)
VALUES (1, NULL, 'SUBJECTIVE', '데이터를 저장하기 위해 이름을 붙인 메모리 공간을 무엇이라고 하나요? (한글)', 'LEVEL_TEST',
        'LEVEL_1',
        NOW(), NOW()),                          -- P_ID 18
       (1, NULL, 'MULTIPLE_CHOICE', 'Python에서 사용되지 *않는* 변수 선언 키워드는?', 'LEVEL_TEST', 'LEVEL_1',
        NOW(),
        NOW()),                                 -- P_ID 19
       (1, NULL, 'MULTIPLE_CHOICE', '`print(10 / 3)`의 결과는?', 'LEVEL_TEST', 'LEVEL_2', NOW(),
        NOW()),                                 -- P_ID 20
       (1, NULL, 'SUBJECTIVE', '프로그래밍 언어를 기계어로 번역해주는 프로그램을 무엇이라고 하나요? (영어)', 'LEVEL_TEST',
        'LEVEL_1',
        NOW(), NOW()),                          -- P_ID 21
       (1, NULL, 'FILL_IN_THE_BLANK', '여러 값을 순서대로 저장하는 자료형으로, `[]` 기호를 사용하는 것은 ____입니다. (한글)',
        'LEVEL_TEST', 'LEVEL_2', NOW(), NOW()), -- P_ID 22
       (1, NULL, 'MULTIPLE_CHOICE', '`True` 또는 `False` 값을 가지는 데이터 타입은?', 'LEVEL_TEST', 'LEVEL_2',
        NOW(),
        NOW()),                                 -- P_ID 23
       (1, NULL, 'FILL_IN_THE_BLANK', '문자열 "Hello"의 길이는 `____("Hello")` 함수로 구합니다.', 'LEVEL_TEST',
        'LEVEL_3', NOW(), NOW()),               -- P_ID 24
       (1, NULL, 'SUBJECTIVE', '특정 조건이 참(True)일 때만 코드를 실행하는 구문은 무엇인가요? (영어 소문자)', 'LEVEL_TEST',
        'LEVEL_3', NOW(), NOW()),               -- P_ID 25
       (1, NULL, 'MULTIPLE_CHOICE', 'Python에서 코드 블록을 구분하기 위해 사용하는 것은?', 'LEVEL_TEST', 'LEVEL_2',
        NOW(),
        NOW()),                                 -- P_ID 26
       (1, NULL, 'SUBJECTIVE', '반복문에서 `i = i + 1`과 동일한 의미를 갖는 할당 연산자는 무엇인가요?', 'LEVEL_TEST',
        'LEVEL_3',
        NOW(), NOW());
-- P_ID 27

-- ##### 5-3. 사전 레벨 테스트 - Java EASY (P_ID 28 ~ 37) #####
INSERT INTO problem (learning_language_id, stepup_small_category_id, problem_type, content,
                     domain_type, difficulty, created_at, updated_at)
VALUES (2, NULL, 'MULTIPLE_CHOICE', 'Java 코드를 실행하기 위해 반드시 필요한 환경은?', 'LEVEL_TEST', 'LEVEL_1', NOW(),
        NOW()),                                 -- P_ID 28
       (2, NULL, 'SUBJECTIVE', 'Java에서 정수(integer)를 선언하는 기본 자료형(primitive type) 키워드는 무엇인가요?',
        'LEVEL_TEST', 'LEVEL_1', NOW(), NOW()), -- P_ID 29
       (2, NULL, 'MULTIPLE_CHOICE', 'Java 코드를 컴파일한 결과 생성되는 파일의 확장자는?', 'LEVEL_TEST', 'LEVEL_2',
        NOW(),
        NOW()),                                 -- P_ID 30
       (2, NULL, 'SUBJECTIVE', '모든 Java 애플리케이션의 실행 시작점이 되는 메소드의 이름은 무엇인가요?', 'LEVEL_TEST',
        'LEVEL_1',
        NOW(), NOW()),                          -- P_ID 31
       (2, NULL, 'FILL_IN_THE_BLANK',
        '`String`, `Integer`와 같이 객체를 생성해야 하는 데이터 타입을 ____ 타입이라고 합니다. (한글)', 'LEVEL_TEST', 'LEVEL_2',
        NOW(), NOW()),                          -- P_ID 32
       (2, NULL, 'MULTIPLE_CHOICE', 'Java에서 문장의 끝을 알리는 기호는 무엇인가요?', 'LEVEL_TEST', 'LEVEL_2', NOW(),
        NOW()),                                 -- P_ID 33
       (2, NULL, 'FILL_IN_THE_BLANK', '`int x = 10;`에서 `x`는 ____, `10`은 ____입니다. (한글, /로 구분)',
        'LEVEL_TEST', 'LEVEL_3', NOW(), NOW()), -- P_ID 34
       (2, NULL, 'SUBJECTIVE', '객체를 생성(인스턴스화)할 때 사용하는 키워드는 무엇인가요?', 'LEVEL_TEST', 'LEVEL_3', NOW(),
        NOW()),                                 -- P_ID 35
       (2, NULL, 'MULTIPLE_CHOICE', '객체를 만들기 위한 ''설계도'' 또는 ''틀''을 의미하는 것은?', 'LEVEL_TEST',
        'LEVEL_2',
        NOW(), NOW()),                          -- P_ID 36
       (2, NULL, 'SUBJECTIVE', '값이 할당된 후 절대 변경될 수 없는 변수를 선언하는 키워드는 무엇인가요?', 'LEVEL_TEST', 'LEVEL_3',
        NOW(), NOW());
-- P_ID 37

-- ##### 5-4. 레벨 테스트 - JavaScript EASY (P_ID 38 ~ 47) #####
INSERT INTO problem (learning_language_id, stepup_small_category_id, problem_type, content,
                     domain_type, difficulty, created_at, updated_at)
VALUES (3, NULL, 'MULTIPLE_CHOICE', 'JavaScript 코드가 주로 실행되는 환경은 어디인가요?', 'LEVEL_TEST', 'LEVEL_1',
        NOW(),
        NOW()),                                 -- P_ID 38
       (3, NULL, 'SUBJECTIVE', '웹 페이지의 구조(뼈대)를 담당하는 마크업 언어는 무엇인가요?', 'LEVEL_TEST', 'LEVEL_1', NOW(),
        NOW()),                                 -- P_ID 39
       (3, NULL, 'MULTIPLE_CHOICE', 'JavaScript에서 `var`, `let`과 함께 변수를 선언하는 또 다른 키워드는?',
        'LEVEL_TEST',
        'LEVEL_2', NOW(), NOW()),               -- P_ID 40
       (3, NULL, 'SUBJECTIVE', '웹 페이지의 스타일(디자인)을 담당하는 언어는 무엇인가요?', 'LEVEL_TEST', 'LEVEL_1', NOW(),
        NOW()),                                 -- P_ID 41
       (3, NULL, 'MULTIPLE_CHOICE', '값이 `10`인 변수 `x`와 값이 `"10"`인 변수 `y`가 있을 때, `x == y`의 결과는?',
        'LEVEL_TEST', 'LEVEL_2', NOW(), NOW()), -- P_ID 42
       (3, NULL, 'SUBJECTIVE', '브라우저에서 사용자에게 경고창을 띄우는 함수는 무엇인가요? (괄호 제외)', 'LEVEL_TEST', 'LEVEL_2',
        NOW(), NOW()),                          -- P_ID 43
       (3, NULL, 'FILL_IN_THE_BLANK',
        '`null`과 `undefined`는 모두 ''값이 없음''을 나타내지만, `null`은 개발자가 의도적으로 값을 비운 것이고, `undefined`는 ____되지 않은 상태입니다.',
        'LEVEL_TEST', 'LEVEL_3', NOW(), NOW()), -- P_ID 44
       (3, NULL, 'SUBJECTIVE', 'HTML 요소의 ID를 이용해 해당 요소를 가져오는 함수는 무엇인가요? (`document.` 제외, 괄호 제외)',
        'LEVEL_TEST', 'LEVEL_3', NOW(), NOW()), -- P_ID 45
       (3, NULL, 'MULTIPLE_CHOICE', 'JavaScript의 자료형이 아닌 것은?', 'LEVEL_TEST', 'LEVEL_2', NOW(),
        NOW()),                                 -- P_ID 46
       (3, NULL, 'FILL_IN_THE_BLANK', '배열 `[10, 20, 30]`의 길이는 `arr.____` 속성으로 알 수 있습니다.',
        'LEVEL_TEST', 'LEVEL_3', NOW(), NOW());
-- P_ID 47

-- ##### 5-5. 레벨 테스트 - Python MEDIUM (P_ID 48 ~ 57) #####
INSERT INTO problem (learning_language_id, stepup_small_category_id, problem_type, content,
                     domain_type, difficulty, created_at, updated_at)
VALUES (1, NULL, 'SUBJECTIVE', 'Key와 Value를 한 쌍으로 저장하는 Python의 자료형은 무엇인가요? (한글)', 'LEVEL_TEST',
        'LEVEL_4', NOW(), NOW()),               -- P_ID 48
       (1, NULL, 'MULTIPLE_CHOICE', '순서가 있지만 수정이 불가능한(immutable) 시퀀스 자료형은?', 'LEVEL_TEST',
        'LEVEL_4',
        NOW(), NOW()),                          -- P_ID 49
       (1, NULL, 'SUBJECTIVE', '특정 기능을 수행하는 코드의 묶음으로, `def` 키워드를 사용하여 정의하는 것은 무엇인가요? (한글)',
        'LEVEL_TEST', 'LEVEL_5', NOW(), NOW()), -- P_ID 50
       (1, NULL, 'MULTIPLE_CHOICE', '`"Hello"[1:4]`의 슬라이싱 결과로 올바른 것은?', 'LEVEL_TEST', 'LEVEL_5',
        NOW(),
        NOW()),                                 -- P_ID 51
       (1, NULL, 'SUBJECTIVE', '다른 파일에 정의된 함수나 클래스를 가져올 때 사용하는 키워드는 무엇인가요?', 'LEVEL_TEST',
        'LEVEL_4',
        NOW(), NOW()),                          -- P_ID 52
       (1, NULL, 'FILL_IN_THE_BLANK',
        '`[1, 1, 2, 3]` 리스트에서 중복된 값을 제거하려면 `____(myList)`로 변환할 수 있습니다.', 'LEVEL_TEST', 'LEVEL_6',
        NOW(), NOW()),                          -- P_ID 53
       (1, NULL, 'SUBJECTIVE', '`try...except` 구문에서 오류 발생 여부와 상관없이 항상 실행되는 블록을 정의하는 키워드는?',
        'LEVEL_TEST', 'LEVEL_5', NOW(), NOW()), -- P_ID 54
       (1, NULL, 'MULTIPLE_CHOICE', '객체지향 프로그래밍에서 ''설계도''에 해당하는 것은?', 'LEVEL_TEST', 'LEVEL_6',
        NOW(),
        NOW()),                                 -- P_ID 55
       (1, NULL, 'SUBJECTIVE', '클래스로부터 실제 메모리에 생성된 객체를 무엇이라고 하나요? (한글)', 'LEVEL_TEST', 'LEVEL_5',
        NOW(), NOW()),                          -- P_ID 56
       (1, NULL, 'FILL_IN_THE_BLANK', '클래스 내부에 정의된 함수, 즉 객체의 동작을 나타내는 것을 ____라고 합니다. (영어)',
        'LEVEL_TEST', 'LEVEL_6', NOW(), NOW());
-- P_ID 57

-- ##### 5-6. 레벨 테스트 - Java MEDIUM (P_ID 58 ~ 67) #####
INSERT INTO problem (learning_language_id, stepup_small_category_id, problem_type, content,
                     domain_type, difficulty, created_at, updated_at)
VALUES (2, NULL, 'SUBJECTIVE',
        '객체지향 프로그래밍(OOP)의 4가지 주요 특징 중 하나로, 부모 클래스의 속성과 메소드를 자식 클래스가 물려받는 것을 무엇이라고 하나요? (한글)',
        'LEVEL_TEST', 'LEVEL_4', NOW(), NOW()), -- P_ID 58
       (2, NULL, 'MULTIPLE_CHOICE', '객체의 속성(필드)은 숨기고, 외부에는 메소드만 공개하여 데이터를 보호하는 OOP 특징은?',
        'LEVEL_TEST', 'LEVEL_4', NOW(), NOW()), -- P_ID 59
       (2, NULL, 'SUBJECTIVE', '순서가 없고 중복을 허용하지 않는 Java의 컬렉션 인터페이스는 무엇인가요?', 'LEVEL_TEST',
        'LEVEL_5',
        NOW(), NOW()),                          -- P_ID 60
       (2, NULL, 'SUBJECTIVE', '실행 시 발생하는 오류를 처리하기 위해 `try` 블록과 함께 사용하는 블록은 무엇인가요?', 'LEVEL_TEST',
        'LEVEL_5', NOW(), NOW()),               -- P_ID 61
       (2, NULL, 'FILL_IN_THE_BLANK', '하나의 메소드나 클래스가 다양한 타입의 객체를 처리할 수 있는 성질을 ____이라고 합니다. (한글)',
        'LEVEL_TEST', 'LEVEL_4', NOW(), NOW()), -- P_ID 62
       (2, NULL, 'MULTIPLE_CHOICE', '다른 클래스의 멤버에 접근할 수 있는 범위를 지정하는 키워드(접근 제어자)가 아닌 것은?',
        'LEVEL_TEST',
        'LEVEL_6', NOW(), NOW()),               -- P_ID 63
       (2, NULL, 'FILL_IN_THE_BLANK',
        '`new` 키워드를 통해 객체가 생성될 때, 필드 초기화를 위해 가장 먼저 호출되는 것은 ____입니다. (한글)', 'LEVEL_TEST', 'LEVEL_5',
        NOW(), NOW()),                          -- P_ID 64
       (2, NULL, 'SUBJECTIVE', 'Java에서 문자열 객체를 비교할 때, 주소값이 아닌 실제 내용(값)을 비교하는 메소드는 무엇인가요?',
        'LEVEL_TEST', 'LEVEL_6', NOW(), NOW()), -- P_ID 65
       (2, NULL, 'MULTIPLE_CHOICE', '객체를 생성하지 않고도 클래스 이름으로 바로 접근할 수 있는 메소드를 선언하는 키워드는?',
        'LEVEL_TEST',
        'LEVEL_5', NOW(), NOW()),               -- P_ID 66
       (2, NULL, 'SUBJECTIVE', '실체가 없는 메소드, 즉 선언부만 있고 구현부가 없는 메소드를 무엇이라고 하나요? (한글)', 'LEVEL_TEST',
        'LEVEL_6', NOW(), NOW());
-- P_ID 67

-- ##### 5-7. 레벨 테스트 - JavaScript MEDIUM (P_ID 68 ~ 77) #####
INSERT INTO problem (learning_language_id, stepup_small_category_id, problem_type, content,
                     domain_type, difficulty, created_at, updated_at)
VALUES (3, NULL, 'SUBJECTIVE',
        '웹 페이지의 특정 요소를 선택하고, 스타일을 변경하거나 이벤트를 추가하는 등 HTML을 제어하는 인터페이스(API)를 무엇이라고 하나요? (약어)',
        'LEVEL_TEST', 'LEVEL_4', NOW(), NOW()), -- P_ID 68
       (3, NULL, 'MULTIPLE_CHOICE', '`"10"`과 `10`을 `===` 연산자로 비교했을 때의 결과는?', 'LEVEL_TEST',
        'LEVEL_4',
        NOW(), NOW()),                          -- P_ID 69
       (3, NULL, 'SUBJECTIVE', '배열 `arr`의 맨 뒤에 요소 `5`를 추가하는 메소드는 무엇인가요? (괄호 제외)', 'LEVEL_TEST',
        'LEVEL_5', NOW(), NOW()),               -- P_ID 70
       (3, NULL, 'SUBJECTIVE', '서버와 데이터를 비동기적으로 주고받기 위해 사용되는, JavaScript 객체 표기법은 무엇인가요? (약어)',
        'LEVEL_TEST', 'LEVEL_5', NOW(), NOW()), -- P_ID 71
       (3, NULL, 'FILL_IN_THE_BLANK',
        '`let`과 `const`는 `if`문이나 `for`문 내부에서만 유효한 ____ 스코프를 가집니다. (영어)', 'LEVEL_TEST', 'LEVEL_4',
        NOW(), NOW()),                          -- P_ID 72
       (3, NULL, 'MULTIPLE_CHOICE',
        '배열 `[1, 2, 3]`의 각 요소를 제곱한 새 배열 `[1, 4, 9]`를 만드는 가장 적절한 배열 메소드는?', 'LEVEL_TEST', 'LEVEL_6',
        NOW(), NOW()),                          -- P_ID 73
       (3, NULL, 'SUBJECTIVE', '사용자의 클릭, 키보드 입력 등 브라우저에서의 특정 동작을 ____라고 합니다. (한글)', 'LEVEL_TEST',
        'LEVEL_5', NOW(), NOW()),               -- P_ID 74
       (3, NULL, 'FILL_IN_THE_BLANK',
        'ES6에서 도입된, 객체나 배열에서 값을 쉽게 추출할 수 있는 문법을 ''구조 분해 ____''이라고 합니다.', 'LEVEL_TEST', 'LEVEL_6',
        NOW(), NOW()),                          -- P_ID 75
       (3, NULL, 'SUBJECTIVE', '전통적인 `function` 선언 방식보다 간결하게 함수를 선언할 수 있는 ES6 문법은 무엇인가요? (한글)',
        'LEVEL_TEST', 'LEVEL_5', NOW(), NOW()), -- P_ID 76
       (3, NULL, 'SUBJECTIVE', 'JavaScript 객체를 JSON 문자열로 변환하는 메소드는 무엇인가요? (괄호 제외, `JSON.` 포함)',
        'LEVEL_TEST', 'LEVEL_6', NOW(), NOW());
-- P_ID 77

-- ##### 5-8. 레벨 테스트 - Python HARD (P_ID 78 ~ 87) #####
INSERT INTO problem (learning_language_id, stepup_small_category_id, problem_type, content,
                     domain_type, difficulty, created_at, updated_at)
VALUES (1, NULL, 'SUBJECTIVE', '운영체제(OS)로부터 시스템 자원을 할당받는 작업의 단위를 무엇이라고 하나요? (한글)', 'LEVEL_TEST',
        'LEVEL_7', NOW(), NOW()),               -- P_ID 78 (CS 공통)
       (1, NULL, 'SUBJECTIVE', '프로세스 내에서 실제로 작업을 수행하는 실행의 단위는 무엇인가요? (한글)', 'LEVEL_TEST', 'LEVEL_7',
        NOW(), NOW()),                          -- P_ID 79 (CS 공통)
       (1, NULL, 'SUBJECTIVE',
        'CPython에서 여러 스레드가 동시에 Python 바이트코드를 실행하는 것을 막는 잠금(Lock) 메커니즘은 무엇인가요? (약어)', 'LEVEL_TEST',
        'LEVEL_9', NOW(), NOW()),               -- P_ID 80
       (1, NULL, 'MULTIPLE_CHOICE', '함수 실행을 잠시 멈추고 값을 반환한 뒤, 나중에 다시 이어서 실행할 수 있게 해주는 키워드는?',
        'LEVEL_TEST', 'LEVEL_8', NOW(), NOW()), -- P_ID 81
       (1, NULL, 'SUBJECTIVE', '함수를 인자로 받거나, 함수를 결과로 반환할 수 있는 함수를 무엇이라고 하나요? (한글)', 'LEVEL_TEST',
        'LEVEL_8', NOW(), NOW()),               -- P_ID 82
       (1, NULL, 'FILL_IN_THE_BLANK',
        '다른 함수를 꾸며주어(wrapping) 기존 코드 수정 없이 새로운 기능을 추가하는 함수를 ____라고 합니다. (영어)', 'LEVEL_TEST',
        'LEVEL_9',
        NOW(), NOW()),                          -- P_ID 83
       (1, NULL, 'MULTIPLE_CHOICE', '`map(int, ["1", "2"])`의 반환 타입은?', 'LEVEL_TEST', 'LEVEL_7',
        NOW(),
        NOW()),                                 -- P_ID 84
       (1, NULL, 'SUBJECTIVE', '컴퓨터 네트워크에서 클라이언트가 서버에 데이터를 요청하기 위해 사용하는 표준화된 통신 규약은 무엇인가요? (약어)',
        'LEVEL_TEST', 'LEVEL_7', NOW(), NOW()), -- P_ID 85 (CS 공통)
       (1, NULL, 'FILL_IN_THE_BLANK',
        '`[x for x in range(10)]`와 같이 리스트를 간결하게 생성하는 문법을 리스트 ____이라고 합니다. (한글)', 'LEVEL_TEST',
        'LEVEL_8',
        NOW(), NOW()),                          -- P_ID 86
       (1, NULL, 'SUBJECTIVE', '클래스가 객체 자신을 참조할 때 사용하는 첫 번째 매개변수의 관례적인 이름은?', 'LEVEL_TEST',
        'LEVEL_10',
        NOW(), NOW());
-- P_ID 87

-- ##### 5-9. 레벨 테스트 - Java HARD (P_ID 88 ~ 97) #####
INSERT INTO problem (learning_language_id, stepup_small_category_id, problem_type, content,
                     domain_type, difficulty, created_at, updated_at)
VALUES (2, NULL, 'SUBJECTIVE', 'Java에서 더 이상 사용되지 않는 객체를 메모리에서 자동으로 해제해주는 기능을 무엇이라고 하나요? (한글)',
        'LEVEL_TEST', 'LEVEL_7', NOW(), NOW()), -- P_ID 88
       (2, NULL, 'MULTIPLE_CHOICE', '`List`, `Set`, `Map` 등 Java의 자료구조 집합을 무엇이라고 부르나요?',
        'LEVEL_TEST',
        'LEVEL_7', NOW(), NOW()),               -- P_ID 89
       (2, NULL, 'SUBJECTIVE',
        '컴파일 시에는 타입 검사를 하지만, 런타임 시에는 해당 타입 정보를 제거하는 Java 제네릭의 특징을 무엇이라고 하나요?', 'LEVEL_TEST',
        'LEVEL_9',
        NOW(), NOW()),                          -- P_ID 90
       (2, NULL, 'MULTIPLE_CHOICE', '`Exception`과 `Error`의 공통 부모 클래스는 무엇인가요?', 'LEVEL_TEST',
        'LEVEL_8',
        NOW(), NOW()),                          -- P_ID 91
       (2, NULL, 'SUBJECTIVE', '서로 다른 스레드가 동일한 자원에 동시에 접근하는 것을 막는 기술을 무엇이라고 하나요? (한글)',
        'LEVEL_TEST',
        'LEVEL_8', NOW(), NOW()),               -- P_ID 92 (CS 공통)
       (2, NULL, 'FILL_IN_THE_BLANK', '데이터베이스에 접근하여 데이터를 조작하는 데 사용되는 쿼리 언어는 `____` 입니다. (약어)',
        'LEVEL_TEST', 'LEVEL_7', NOW(), NOW()), -- P_ID 93 (CS 공통)
       (2, NULL, 'SUBJECTIVE',
        '`List<String> names`를 `String[]` 배열로 변환하는 스트림 API 메소드는 무엇인가요? (괄호 포함)', 'LEVEL_TEST',
        'LEVEL_9',
        NOW(), NOW()),                          -- P_ID 94
       (2, NULL, 'FILL_IN_THE_BLANK',
        '`HashMap`과 `Hashtable`의 가장 큰 차이점 중 하나는 `HashMap`은 ____ 값 저장을 허용한다는 것입니다.', 'LEVEL_TEST',
        'LEVEL_8', NOW(), NOW()),               -- P_ID 95
       (2, NULL, 'SUBJECTIVE', '부모 클래스의 메소드를 자식 클래스에서 다시 정의(재정의)하는 것을 무엇이라고 하나요? (영어)',
        'LEVEL_TEST',
        'LEVEL_8', NOW(), NOW()),               -- P_ID 96
       (2, NULL, 'SUBJECTIVE', '인터페이스(Interface)에 선언된 모든 메소드는 기본적으로 어떤 접근 제어자를 가지나요? (Java 8 기준)',
        'LEVEL_TEST', 'LEVEL_10', NOW(), NOW());
-- P_ID 97

-- ##### 5-10. 레벨 테스트 - JavaScript HARD (P_ID 98 ~ 107) #####
INSERT INTO problem (learning_language_id, stepup_small_category_id, problem_type, content,
                     domain_type, difficulty, created_at, updated_at)
VALUES (3, NULL, 'SUBJECTIVE',
        '비동기 작업이 완료된 후 실행될 함수(콜백)를 등록하는 객체로, `pending`, `fulfilled`, `rejected` 상태를 갖는 것은?',
        'LEVEL_TEST', 'LEVEL_8', NOW(), NOW()), -- P_ID 98
       (3, NULL, 'SUBJECTIVE',
        '`Promise`를 기반으로 비동기 코드를 동기식 코드처럼 보이게 작성할 수 있게 해주는 두 개의 키워드는 무엇인가요? (a / b)', 'LEVEL_TEST',
        'LEVEL_9', NOW(), NOW()),               -- P_ID 99
       (3, NULL, 'MULTIPLE_CHOICE', '일반 함수와 달리, 화살표 함수(`=>`)의 `this`는 어떻게 결정되나요?', 'LEVEL_TEST',
        'LEVEL_8', NOW(), NOW()),               -- P_ID 100
       (3, NULL, 'SUBJECTIVE', '외부 함수의 변수에 접근할 수 있는 내부 함수와 그 환경의 조합을 무엇이라고 하나요? (한글)', 'LEVEL_TEST',
        'LEVEL_9', NOW(), NOW()),               -- P_ID 101
       (3, NULL, 'MULTIPLE_CHOICE', '여러 개의 `Promise` 중 가장 *먼저* 완료(성공 또는 실패)되는 것의 결과를 반환하는 메소드는?',
        'LEVEL_TEST', 'LEVEL_8', NOW(), NOW()), -- P_ID 102
       (3, NULL, 'FILL_IN_THE_BLANK', '`setTimeout(func, 1000)` 코드는 `func` 함수를 몇 초 뒤에 실행하나요? (숫자만)',
        'LEVEL_TEST', 'LEVEL_7', NOW(), NOW()), -- P_ID 103
       (3, NULL, 'SUBJECTIVE', '웹 브라우저가 서버로부터 데이터를 요청하고 받아오는 데 사용되는 표준 통신 프로토콜은 무엇인가요? (약어)',
        'LEVEL_TEST', 'LEVEL_7', NOW(), NOW()), -- P_ID 104 (CS 공통)
       (3, NULL, 'FILL_IN_THE_BLANK',
        'JavaScript 객체의 속성과 값을 한 쌍으로 묶은 데이터를 전송하는 표준 형식은 `____` 입니다. (약어)', 'LEVEL_TEST',
        'LEVEL_10',
        NOW(), NOW()),                          -- P_ID 105
       (3, NULL, 'SUBJECTIVE', '변수 선언이 코드의 최상단으로 끌어올려지는 듯한 동작을 하는 JavaScript의 특징은 무엇인가요? (한글)',
        'LEVEL_TEST', 'LEVEL_9', NOW(), NOW()), -- P_ID 106
       (3, NULL, 'SUBJECTIVE', '객체의 원형(부모) 역할을 하는 객체를 무엇이라고 하나요? (영어)', 'LEVEL_TEST', 'LEVEL_10',
        NOW(),
        NOW());
-- P_ID 107

-- ##### 5-11. [신규] 레벨 테스트 - 코딩/문법 빈칸 채우기 (P_ID 108 ~ 112) #####
INSERT INTO problem (learning_language_id, stepup_small_category_id, problem_type, content,
                     domain_type, difficulty, created_at, updated_at)
VALUES (1, NULL, 'FILL_IN_THE_BLANK',
        'Python 딕셔너리에 "apple" 키와 1 값을 ____하려면, `my_dict____"apple"____ = 1` 코드를 사용합니다.',
        'LEVEL_TEST',
        'LEVEL_5', NOW(), NOW()),               -- P_ID 108 (3개 빈칸)
       (2, NULL, 'FILL_IN_THE_BLANK',
        'Java에서 `List<String> names`의 모든 요소를 출력하는 향상된 for문은 `____ (String name ____ names) ____ System.out.println(name); ____` 입니다.',
        'LEVEL_TEST', 'LEVEL_6', NOW(), NOW()), -- P_ID 109 (4개 빈칸)
       (3, NULL, 'FILL_IN_THE_BLANK',
        'JavaScript에서 `Promise`가 성공했을 때 `result` 값을 받아 처리하는 코드는 `myPromise.____(____ => { console.log(result); });` 입니다.',
        'LEVEL_TEST', 'LEVEL_8', NOW(), NOW()), -- P_ID 110 (2개 빈칸)
       (1, NULL, 'FILL_IN_THE_BLANK',
        'Python 예외 처리: `____:` 시도할 코드. `____ ValueError:` `ValueError` 처리. `____:` 예외 미발생 시 실행. `____:` 항상 실행.',
        'LEVEL_TEST', 'LEVEL_7', NOW(), NOW()), -- P_ID 111 (4개 빈칸)
       (2, NULL, 'FILL_IN_THE_BLANK',
        'Java Stream API로 리스트 `list`에서 "A"로 시작하는 요소만 필터링: `list.____().____(s -> s.startsWith("A")).collect(Collectors.____());`',
        'LEVEL_TEST', 'LEVEL_9', NOW(), NOW());
-- P_ID 112 (3개 빈칸)

-- #####################################################################
-- 6. CHOICE (모든 객관식 선택지)
-- #####################################################################
INSERT INTO choice (problem_id, content, is_correct, created_at, updated_at)
VALUES
    -- 스텝업 (P_ID 1 ~ 17)
    (1, 'count = 10', TRUE, NOW(), NOW()),
    (1, 'var count = 10;', FALSE, NOW(), NOW()),
    (1, 'int count = 10;', FALSE, NOW(), NOW()),
    (3, '11', TRUE, NOW(), NOW()),
    (3, '10', FALSE, NOW(), NOW()),
    (3, '12', FALSE, NOW(), NOW()),
    (6, '1', TRUE, NOW(), NOW()),
    (6, '2', FALSE, NOW(), NOW()),
    (6, '3', FALSE, NOW(), NOW()),
    (7, 'MyClass obj = new MyClass();', TRUE, NOW(), NOW()),
    (7, 'obj = MyClass.create();', FALSE, NOW(), NOW()),
    (7, 'var obj = new MyClass();', FALSE, NOW(), NOW()),
    (8, 'const', TRUE, NOW(), NOW()),
    (8, 'let', FALSE, NOW(), NOW()),
    (8, 'var', FALSE, NOW(), NOW()),
    (16, 'document.getElementById()', TRUE, NOW(), NOW()),
    (16, 'document.querySelector()', FALSE, NOW(), NOW()),
    (16, 'document.getElementsByClass()', FALSE, NOW(), NOW()),
    -- (P_ID 4, 9, 10, 11, 13, 14에 대한 선택지 데이터는 원본에 없어서 생략합니다)

    -- Python EASY (P_ID 18 ~ 27)
    (19, 'int', TRUE, NOW(), NOW()),
    (19, 'var', FALSE, NOW(), NOW()),
    (19, 'let', FALSE, NOW(), NOW()),
    (20, '3.333...', TRUE, NOW(), NOW()),
    (20, '3', FALSE, NOW(), NOW()),
    (20, '3.0', FALSE, NOW(), NOW()),
    (23, 'Boolean (불리언)', TRUE, NOW(), NOW()),
    (23, 'String (문자열)', FALSE, NOW(), NOW()),
    (23, 'Integer (정수)', FALSE, NOW(), NOW()),
    (26, '들여쓰기(Indentation)', TRUE, NOW(), NOW()),
    (26, '중괄호({})', FALSE, NOW(), NOW()),
    (26, '세미콜론(;)', FALSE, NOW(), NOW()),
    -- Java EASY (P_ID 28 ~ 37)
    (28, 'JVM (Java Virtual Machine)', TRUE, NOW(), NOW()),
    (28, 'JDK (Java Development Kit)', FALSE, NOW(), NOW()),
    (28, 'JRE (Java Runtime Environment)', FALSE, NOW(), NOW()),
    (30, '.class', TRUE, NOW(), NOW()),
    (30, '.java', FALSE, NOW(), NOW()),
    (30, '.exe', FALSE, NOW(), NOW()),
    (33, '; (세미콜론)', TRUE, NOW(), NOW()),
    (33, ': (콜론)', FALSE, NOW(), NOW()),
    (33, '. (점)', FALSE, NOW(), NOW()),
    (36, '클래스(Class)', TRUE, NOW(), NOW()),
    (36, '메소드(Method)', FALSE, NOW(), NOW()),
    (36, '변수(Variable)', FALSE, NOW(), NOW()),
    -- JavaScript EASY (P_ID 38 ~ 47)
    (38, '웹 브라우저(Web Browser)', TRUE, NOW(), NOW()),
    (38, '데이터베이스(Database)', FALSE, NOW(), NOW()),
    (38, '운영체제(OS)', FALSE, NOW(), NOW()),
    (40, 'const', TRUE, NOW(), NOW()),
    (40, 'int', FALSE, NOW(), NOW()),
    (40, 'function', FALSE, NOW(), NOW()),
    (42, 'true', TRUE, NOW(), NOW()),
    (42, 'false', FALSE, NOW(), NOW()),
    (42, '오류 발생', FALSE, NOW(), NOW()),
    (46, 'Character', TRUE, NOW(), NOW()),
    (46, 'String', FALSE, NOW(), NOW()),
    (46, 'Number', FALSE, NOW(), NOW()),
    -- Python MEDIUM (P_ID 48 ~ 57)
    (49, '튜플(Tuple)', TRUE, NOW(), NOW()),
    (49, '리스트(List)', FALSE, NOW(), NOW()),
    (49, '딕셔너리(Dictionary)', FALSE, NOW(), NOW()),
    (51, '"ell"', TRUE, NOW(), NOW()),
    (51, '"ello"', FALSE, NOW(), NOW()),
    (51, '"ellH"', FALSE, NOW(), NOW()),
    (55, '클래스(Class)', TRUE, NOW(), NOW()),
    (55, '인스턴스(Instance)', FALSE, NOW(), NOW()),
    (55, '모듈(Module)', FALSE, NOW(), NOW()),
    -- Java MEDIUM (P_ID 58 ~ 67)
    (59, '캡슐화(Encapsulation)', TRUE, NOW(), NOW()),
    (59, '상속(Inheritance)', FALSE, NOW(), NOW()),
    (59, '다형성(Polymorphism)', FALSE, NOW(), NOW()),
    (63, 'static', TRUE, NOW(), NOW()),
    (63, 'final', FALSE, NOW(), NOW()),
    (63, 'void', FALSE, NOW(), NOW()),
    (66, 'static', TRUE, NOW(), NOW()),
    (66, 'final', FALSE, NOW(), NOW()),
    (66, 'abstract', FALSE, NOW(), NOW()),
    -- JavaScript MEDIUM (P_ID 68 ~ 77)
    (69, 'false', TRUE, NOW(), NOW()),
    (69, 'true', FALSE, NOW(), NOW()),
    (69, '오류 발생', FALSE, NOW(), NOW()),
    (73, 'map()', TRUE, NOW(), NOW()),
    (73, 'forEach()', FALSE, NOW(), NOW()),
    (73, 'filter()', FALSE, NOW(), NOW()),
    -- Python HARD (P_ID 78 ~ 87)
    (81, 'yield', TRUE, NOW(), NOW()),
    (81, 'return', FALSE, NOW(), NOW()),
    (81, 'continue', FALSE, NOW(), NOW()),
    (84, 'map 객체 (이터레이터)', TRUE, NOW(), NOW()),
    (84, '리스트', FALSE, NOW(), NOW()),
    (84, '오류 발생', FALSE, NOW(), NOW()),
    (85, 'HTTP', TRUE, NOW(), NOW()),
    (85, 'TCP/IP', FALSE, NOW(), NOW()),
    (85, 'FTP', FALSE, NOW(), NOW()),
    -- Java HARD (P_ID 88 ~ 97)
    (89, 'Java 컬렉션 프레임워크 (JCF)', TRUE, NOW(), NOW()),
    (89, 'Java 스트림 API (Stream API)', FALSE, NOW(), NOW()),
    (89, 'Java 제네릭 (Generics)', FALSE, NOW(), NOW()),
    (91, 'Throwable', TRUE, NOW(), NOW()),
    (91, 'Object', FALSE, NOW(), NOW()),
    (91, 'Exception', FALSE, NOW(), NOW()),
    (92, '동기화(Synchronization)', TRUE, NOW(), NOW()),
    (92, '병렬성(Parallelism)', FALSE, NOW(), NOW()),
    (92, '캡슐화(Encapsulation)', FALSE, NOW(), NOW()),
    -- JavaScript HARD (P_ID 98 ~ 107)
    (100, '함수가 선언된 렉시컬 스코프의 this를 따름', TRUE, NOW(), NOW()),
    (100, '함수를 호출한 객체', FALSE, NOW(), NOW()),
    (100, '항상 전역 객체(window)', FALSE, NOW(), NOW()),
    (102, 'Promise.race()', TRUE, NOW(), NOW()),
    (102, 'Promise.all()', FALSE, NOW(), NOW()),
    (102, 'Promise.any()', FALSE, NOW(), NOW()),
    (104, 'HTTP', TRUE, NOW(), NOW()),
    (104, 'WebSocket', FALSE, NOW(), NOW()),
    (104, 'FTP', FALSE, NOW(), NOW());

-- #####################################################################
-- 7. SOLUTION (모든 단답형 주관식 정답)
-- #####################################################################
INSERT INTO solution (problem_id, content, created_at, updated_at)
VALUES
    -- 스텝업
    (2, 'a, b = b, a', NOW(), NOW()),
    (5, 'for i in range(1, 6): print(i)', NOW(), NOW()),
    (12, 'public Car() {}', NOW(), NOW()),
    (15, 'let x = 10; console.log(x);', NOW(), NOW()),
    (17, 'document.getElementById("myButton").addEventListener("click", function() {});', NOW(),
     NOW()),
    -- Python EASY
    (18, '변수', NOW(), NOW()),
    (21, 'Compiler', NOW(), NOW()),
    (25, 'if', NOW(), NOW()),
    (27, 'i += 1', NOW(), NOW()),
    -- Java EASY
    (29, 'int', NOW(), NOW()),
    (31, 'main', NOW(), NOW()),
    (35, 'new', NOW(), NOW()),
    (37, 'final', NOW(), NOW()),
    (33, '==', NOW(), NOW()),
    (34, 'new ArrayList<>()', NOW(), NOW()),
    -- JavaScript EASY
    (39, 'HTML', NOW(), NOW()),
    (41, 'CSS', NOW(), NOW()),
    (43, 'alert', NOW(), NOW()),
    (45, 'getElementById', NOW(), NOW()),
    -- Python MEDIUM
    (48, '딕셔너리', NOW(), NOW()),
    (50, '함수', NOW(), NOW()),
    (52, 'import', NOW(), NOW()),
    (54, 'finally', NOW(), NOW()),
    (56, '인스턴스', NOW(), NOW()),
    -- Java MEDIUM
    (58, '상속', NOW(), NOW()),
    (60, 'Set', NOW(), NOW()),
    (61, 'catch', NOW(), NOW()),
    (65, 'equals()', NOW(), NOW()),
    (67, '추상 메소드', NOW(), NOW()),
    -- JavaScript MEDIUM
    (68, 'DOM', NOW(), NOW()),
    (70, 'push', NOW(), NOW()),
    (71, 'JSON', NOW(), NOW()),
    (74, '이벤트', NOW(), NOW()),
    (76, '화살표 함수', NOW(), NOW()),
    (77, 'JSON.stringify', NOW(), NOW()),
    -- Python HARD
    (78, '프로세스', NOW(), NOW()),
    (79, '스레드', NOW(), NOW()),
    (80, 'GIL', NOW(), NOW()),
    (82, '고차 함수', NOW(), NOW()),
    (83, 'loads', NOW(), NOW()),
    (84, '{"key": "value"}', NOW(), NOW()),
    (87, 'self', NOW(), NOW()),
    -- Java HARD
    (88, '가비지 컬렉션', NOW(), NOW()),
    (90, '타입 소거', NOW(), NOW()),
    (94, 'toArray()', NOW(), NOW()),
    (96, '오버라이딩', NOW(), NOW()),
    (97, 'public', NOW(), NOW()),
    -- JavaScript HARD
    (98, 'Promise', NOW(), NOW()),
    (99, 'async/await', NOW(), NOW()),
    (101, '클로저', NOW(), NOW()),
    (103, 'JSON.stringify', NOW(), NOW()),
    (105, 'arr.reduce((acc, val) => acc + val, 0)', NOW(), NOW()),
    (106, 'obj.hasOwnProperty("key")', NOW(), NOW()),
    (107, 'prototype', NOW(), NOW());

-- #####################################################################
-- 8. BLANK_SOLUTION (모든 빈칸 채우기 정답)
-- #####################################################################
INSERT INTO blank_solution (problem_id, blank_order, content, created_at, updated_at)
VALUES
    -- Python EASY
    (22, 1, '리스트', NOW(), NOW()),
    (24, 1, 'len', NOW(), NOW()),
    -- Java EASY
    (32, 1, '참조', NOW(), NOW()),
    (34, 1, '변수/값', NOW(), NOW()),
    (35, 1, 'static', NOW(), NOW()),
    (36, 1, 'args', NOW(), NOW()),
    (37, 1, '!=', NOW(), NOW()),
    -- JavaScript EASY
    (44, 1, '할당', NOW(), NOW()),
    (45, 1, 'getElementById', NOW(), NOW()),
    (46, 1, '===', NOW(), NOW()),
    (47, 1, 'length', NOW(), NOW()),
    -- Python MEDIUM
    (53, 1, 'set', NOW(), NOW()),
    (55, 1, 'name', NOW(), NOW()),
    (56, 1, 'for', NOW(), NOW()),
    (57, 1, 'method', NOW(), NOW()),
    -- Java MEDIUM
    (62, 1, '다형성', NOW(), NOW()),
    (64, 1, '생성자', NOW(), NOW()),
    (66, 1, '캡슐화', NOW(), NOW()),
    (66, 2, 'private', NOW(), NOW()),
    (67, 1, 'HashMap', NOW(), NOW()),
    -- JavaScript MEDIUM
    (72, 1, '블록', NOW(), NOW()),
    (75, 1, '할당', NOW(), NOW()),
    (76, 1, 'then', NOW(), NOW()),
    (77, 1, 'setTimeout', NOW(), NOW()),
    -- Python HARD
    (83, 1, '데코레이터', NOW(), NOW()),
    (85, 1, 'finally', NOW(), NOW()),
    (86, 1, '컴프리헨션', NOW(), NOW()),
    (87, 1, 'import', NOW(), NOW()),
    -- Java HARD
    (93, 1, 'SQL', NOW(), NOW()),
    (95, 1, 'null', NOW(), NOW()),
    (96, 1, 'map', NOW(), NOW()),
    (97, 1, 'Thread', NOW(), NOW()),
    -- JavaScript HARD
    (103, 1, '1', NOW(), NOW()),
    (105, 1, 'JSON', NOW(), NOW()),
    (106, 1, 'filter', NOW(), NOW()),
    (107, 1, 'in', NOW(), NOW()),
    (108, 1, '추가', NOW(), NOW()),
    (108, 2, '[', NOW(), NOW()),
    (108, 3, ']', NOW(), NOW()),
    (109, 1, 'for', NOW(), NOW()),
    (109, 2, ':', NOW(), NOW()),
    (109, 3, '{', NOW(), NOW()),
    (109, 4, '}', NOW(), NOW()),
    (110, 1, 'then', NOW(), NOW()),
    (110, 2, 'result', NOW(), NOW()),
    (111, 1, 'try', NOW(), NOW()),
    (111, 2, 'except', NOW(), NOW()),
    (111, 3, 'else', NOW(), NOW()),
    (111, 4, 'finally', NOW(), NOW()),
    (112, 1, 'stream', NOW(), NOW()),
    (112, 2, 'filter', NOW(), NOW()),
    (112, 3, 'toList', NOW(), NOW());
-- #####################################################################
-- 9. HINTS (모든 힌트)
-- #####################################################################
INSERT INTO hint (problem_id, content, hint_order, created_at, updated_at)
VALUES
    -- 스텝업
    (1, 'Python은 변수 선언 시 자료형을 명시하지 않습니다.', 1, NOW(), NOW()),
    (2, '파이썬에서는 임시 변수 없이 한 줄로 값을 교환할 수 있는 특별한 문법이 있습니다.', 1, NOW(), NOW()),
    (5, 'range() 함수의 두 번째 인자는 포함되지 않습니다.', 1, NOW(), NOW()),
    -- Python EASY
    (18, '컴퓨터가 데이터를 기억하는 공간에 붙이는 이름입니다.', 1, NOW(), NOW()),
    (21, '사람이 작성한 코드를 컴퓨터가 이해할 수 있게 번역하는 프로그램입니다.', 1, NOW(), NOW()),
    (26, 'Python은 {} 대신 공백 4칸 또는 탭을 사용합니다.', 1, NOW(), NOW()),
    -- Java EASY
    (29, 'Java는 C언어와 유사한 기본 자료형 키워드를 사용합니다.', 1, NOW(), NOW()),
    (32, 'new 키워드로 생성된 객체가 저장되는 메모리 영역의 주소를 가리키는 타입입니다.', 1, NOW(), NOW()),
    -- JavaScript EASY
    (39, '웹 페이지를 동적으로 만들기 위한 언어입니다.', 1, NOW(), NOW()),
    (42, '값은 비교하지만, 타입은 비교하지 않는 연산자입니다.', 1, NOW(), NOW()),
    -- Python MEDIUM
    (49, '() 기호를 사용하여 생성합니다.', 1, NOW(), NOW()),
    (52, '라이브러리나 다른 .py 파일의 코드를 불러오는 명령어입니다.', 1, NOW(), NOW()),
    -- Java MEDIUM
    (58, '객체지향의 중요한 개념 중 하나입니다.', 1, NOW(), NOW()),
    (60, 'Java의 `HashSet`이 대표적인 예입니다.', 1, NOW(), NOW()),
    -- JavaScript MEDIUM
    (68, 'HTML 문서를 객체 모델로 표현한 것입니다.', 1, NOW(), NOW()),
    (71, 'JavaScript Object Notation의 약자입니다.', 1, NOW(), NOW()),
    (76, '`function` 키워드 대신 `=>` 기호를 사용합니다.', 1, NOW(), NOW()),
    -- Python HARD
    (78, '메인 프로그램의 실행 흐름과는 별개로 실행되는 작은 작업 단위입니다.', 1, NOW(), NOW()),
    (80, '하나의 파이썬 인터프리터가 한 번에 하나의 스레드만 실행하도록 강제하는 기능입니다.', 1, NOW(), NOW()),
    -- Java HARD
    (88, 'JVM이 알아서 메모리를 관리해주는 기능입니다.', 1, NOW(), NOW()),
    (90, '컴파일러에게는 타입 정보를 알려주지만, 실행 시에는 해당 정보를 지우는 기술입니다.', 1, NOW(), NOW()),
    -- JavaScript HARD
    (98, '비동기 작업의 성공 또는 실패를 나타내는 객체입니다.', 1, NOW(), NOW()),
    (101, 'JavaScript의 핵심 개념 중 하나로, 스코프 체인과 관련이 있습니다.', 1, NOW(), NOW());

-- #####################################################################
-- 10. PROBLEM_EXPLANATION (모든 해설)
-- #####################################################################
INSERT INTO problem_explanation (problem_id, choice_id, content, created_at, updated_at)
VALUES
    -- 스텝업
    (1, NULL, 'Python은 별도의 키워드 없이 바로 할당합니다.', NOW(), NOW()),
    (2, NULL, '파이썬의 튜플 언패킹을 이용한 간결한 방법입니다.', NOW(), NOW()),
    (3, NULL, '공백을 포함하여 총 11글자입니다.', NOW(), NOW()),
    (5, NULL, 'range(1, 6)은 1부터 5까지의 정수를 생성합니다.', NOW(), NOW()),
    (6, NULL, '리스트 인덱스는 0부터 시작합니다.', NOW(), NOW()),
    (7, NULL, 'new 키워드를 사용하여 객체를 생성합니다.', NOW(), NOW()),
    (8, NULL, 'const는 재할당이 불가능합니다.', NOW(), NOW()),
    (12, NULL, '클래스 이름과 동일하며, 반환 타입이 없습니다.', NOW(), NOW()),
    (15, NULL, 'let 또는 const를 사용하여 변수를 선언합니다.', NOW(), NOW()),
    (16, NULL, 'getElementById는 ID로 요소를 찾습니다.', NOW(), NOW()),
    (17, NULL, '가장 일반적인 이벤트 리스너 추가 방법입니다.', NOW(), NOW()),
    -- Python EASY
    (18, NULL, '변수(Variable)는 데이터를 저장하기 위해 할당된 메모리 공간에 붙이는 이름입니다.', NOW(), NOW()),
    (19, NULL, 'Java나 JavaScript와 달리 Python은 변수 선언 시 별도의 키워드(var, let, int 등)를 사용하지 않습니다.', NOW(),
     NOW()),
    (20, NULL, 'Python 3.x에서 `/` 연산자는 항상 실수(float) 나눗셈을 수행합니다. (몫만 구하려면 `//` 사용)', NOW(), NOW()),
    (25, NULL, '`if`는 조건을 검사하는 키워드입니다.', NOW(), NOW()),
    (26, NULL, 'Python은 중괄호({}) 대신 들여쓰기(Indentation)를 사용하여 코드 블록(영역)을 구분합니다.', NOW(), NOW()),
    -- Java EASY
    (29, NULL, 'Java의 기본 자료형(Primitive Type) 중 정수는 `int`입니다. `Integer`는 Wrapper 클래스입니다.', NOW(),
     NOW()),
    (33, NULL, 'Java에서는 각 코드 라인(구문)의 끝을 세미콜론(;)으로 마칩니다.', NOW(), NOW()),
    -- JavaScript EASY
    (40, NULL, '`const`는 재할당이 불가능한 상수를 선언합니다. `let`은 재할당이 가능하고, `int`는 JavaScript의 키워드가 아닙니다.',
     NOW(), NOW()),
    (42, NULL, '`==` 연산자(동등 연산자)는 비교 시 타입을 강제로 변환합니다. 따라서 숫자 `10`과 문자열 `"10"`이 같다고 판단합니다.', NOW(),
     NOW()),
    -- Python MEDIUM
    (49, NULL, '튜플(Tuple)은 `()`로 생성하며, 한 번 생성되면 내부 요소를 변경하거나 삭제할 수 없습니다.', NOW(), NOW()),
    (51, NULL,
     '문자열 슬라이싱 `[start:end]`에서 `start` 인덱스는 포함되고 `end` 인덱스는 포함되지 않습니다. "Hello"에서 인덱스 1은 ''e'', 2는 ''l'', 3은 ''l''입니다.',
     NOW(), NOW()),
    (54, NULL, '`finally` 블록은 `try...except` 구문에서 예외가 발생하든, 발생하지 않든 관계없이 항상 마지막에 실행됩니다.', NOW(),
     NOW()),
    -- Java MEDIUM
    (59, NULL, '캡슐화(Encapsulation)는 데이터(필드)를 `private`으로 숨기고, `public` 메소드를 통해서만 접근을 허용하는 기법입니다.',
     NOW(), NOW()),
    (65, NULL, '`==`는 주소(참조) 비교, `.equals()`는 객체의 내용(값) 비교입니다. (String의 경우)', NOW(), NOW()),
    -- JavaScript MEDIUM
    (69, NULL, '`===` 연산자(일치 연산자)는 값과 타입을 모두 비교합니다. 숫자 `10`과 문자열 `"10"`은 타입이 다르므로 `false`입니다.',
     NOW(), NOW()),
    (73, NULL, '`map()` 메소드는 배열의 각 요소에 대해 주어진 함수를 호출한 결과를 모아 새로운 배열을 반환합니다.', NOW(), NOW()),
    -- Python HARD
    (80, NULL,
     'GIL(Global Interpreter Lock)은 하나의 파이썬 프로세스에서 여러 스레드가 있어도, 한 번에 오직 하나의 스레드만 Python 바이트코드를 실행할 수 있도록 제어하는 잠금 장치입니다.',
     NOW(), NOW()),
    (81, NULL, '`yield`는 제너레이터(Generator)를 만들 때 사용하며, 함수의 실행을 일시 중지하고 값을 반환합니다.', NOW(), NOW()),
    -- Java HARD
    (88, NULL, '가비지 컬렉션(GC)은 JVM의 `Heap` 영역에서 더 이상 참조되지 않는 객체(쓰레기)를 찾아내어 메모리에서 자동으로 제거하는 기능입니다.',
     NOW(), NOW()),
    (92, NULL,
     '여러 스레드가 공유 자원(데이터)에 동시에 접근하여 문제가 생기는 것을 ''경쟁 상태(Race Condition)''라고 하며, 이를 막기 위해 한 번에 하나의 스레드만 접근하도록 제어하는 것을 동기화(Synchronization)라고 합니다.',
     NOW(), NOW()),
    -- JavaScript HARD
    (99, NULL,
     '`async` 키워드는 함수가 비동기적으로 동작함을 알리고, `await` 키워드는 `Promise`가 완료될 때까지 함수의 실행을 기다리게 합니다.', NOW(),
     NOW()),
    (101, NULL,
     '클로저(Closure)는 함수와 그 함수가 선언된 렉시컬 환경(Lexical Scope)의 조합입니다. 이를 통해 내부 함수가 외부 함수의 변수에 접근할 수 있습니다.',
     NOW(), NOW());

-- #####################################################################
-- 11. LEVEL_TEST_LEVEL (레벨정보)
-- #####################################################################
INSERT INTO public.level_test_level (created_at, id, updated_at, level_test_end_msg,
                                     level_test_level_name)
VALUES (NOW(), 1, NOW(), '레벨이 확정되었습니다 (LV1)', 'LV1'),
       (NOW(), 2, NOW(), '레벨이 확정되었습니다 (LV2)', 'LV2'),
       (NOW(), 3, NOW(), '레벨이 확정되었습니다 (LV3)', 'LV3'),
       (NOW(), 4, NOW(), '레벨이 확정되었습니다 (LV4)', 'LV4'),
       (NOW(), 5, NOW(), '레벨이 확정되었습니다 (LV5)', 'LV5'),
       (NOW(), 6, NOW(), '레벨이 확정되었습니다 (LV6)', 'LV6'),
       (NOW(), 7, NOW(), '레벨이 확정되었습니다 (LV7)', 'LV7'),
       (NOW(), 8, NOW(), '레벨이 확정되었습니다 (LV8)', 'LV8'),
       (NOW(), 9, NOW(), '레벨이 확정되었습니다 (LV9)', 'LV9'),
       (NOW(), 10, NOW(), '레벨이 확정되었습니다 (LV10)', 'LV10');

-- #####################################################################
-- 12. 테스트 사용자정보
-- #####################################################################
INSERT INTO Users (id, USER_EMAIL, USER_NAME, PASSWORD, USER_ROLE, USER_STATUS, TOTAL_POINT,
                   TOTAL_STUDY_SECOND, LAST_LOGIN_AT, CREATED_AT, UPDATED_AT)
VALUES (9999,
        'helloworld@test.com',
        '테스트계정',
        '0000',
        'USER',
        'ACTIVE',
        0,
        0,
        NOW(),
        NOW(),
        NOW());