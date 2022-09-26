FROM openjdk:18
EXPOSE 8080
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
ENTRYPOINT ["java", "-Dserver.port=${PORT}", "/app.jar"]