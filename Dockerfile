#
# Build stage
#
FROM maven:3.8.1-openjdk-17-slim
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk18-openshift:latest
COPY --from=build /home/app/target/spring-boot-complete-0.0.1-SNAPSHOT.jar /usr/local/lib/spring-boot-complete-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/spring-boot-complete-0.0.1-SNAPSHOT.jar"]
