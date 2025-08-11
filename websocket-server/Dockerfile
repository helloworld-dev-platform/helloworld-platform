FROM gradle:8.7-jdk21 AS build
WORKDIR /app

# 1. 빌드 파일만 복사
COPY build.gradle gradlew gradlew.bat ./
COPY gradle ./gradle

# 2. 의존성 다운로드 (캐시 활용)
RUN gradle dependencies --no-daemon

# 3. 나머지 소스 코드 복사
COPY src ./src

# 4. 애플리케이션 빌드
RUN gradle clean bootJar --no-daemon
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/build/libs/*-SNAPSHOT.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]