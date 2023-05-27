FROM openjdk:17
VOLUME /tmp
ARG JAR_FILE=target/order-application-1.0-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]