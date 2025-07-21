FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY build/libs/backend-api-0.0.1-SNAPSHOT.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar app.jar"]