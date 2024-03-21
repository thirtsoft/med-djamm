FROM maven:3-eclipse-temurin-17 AS build
COPY . .
RUN mvn clean package -DskipTests
FROM eclipse-temurin:17-alpine
COPY --from=build /target/med-djamm-0.0.1-SNAPSHOT.jar med-djamm.jar
EXPOSE 8092
ENTRYPOINT ["java","-jar","med-djamm.jar"]