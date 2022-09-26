FROM openjdk:18
ENV PORT=8080
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dserver.port=${PORT}", "-Xmx512m", "-Xms512m", "org.springframework.boot.loader.JarLauncher"]