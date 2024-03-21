FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/med-djamm-0.0.1-SNAPSHOT.jar med-djamm.jar
EXPOSE 8092
ENTRYPOINT ["java","-jar","med-djamm.jar"]