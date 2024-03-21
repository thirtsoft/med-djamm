FROM eclipse-temurin:17-alpine
VOLUME /tmp
COPY /target/*.jar med-djamm.jar
EXPOSE 8092
ENTRYPOINT ["java","-jar","med-djamm.jar"]