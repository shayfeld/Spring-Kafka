FROM openjdk:17
ADD target/SpringKafka-0.0.1-SNAPSHOT.jar docker-spring.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","docker-spring.jar"]
