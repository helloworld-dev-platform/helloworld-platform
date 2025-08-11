FROM gradle:8.8-jdk21 AS build
WORKDIR /app
COPY . .
RUN gradle clean bootJar --no-daemon
#jar 파일 경로 확인하기 위한 디버깅
RUN find /app/build -maxdepth 2 -type f -name "*.jar" -ls
RUN ls -la /app/build || true
RUN ls -la /app/build/libs || true

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/libs/*-SNAPSHOT.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]