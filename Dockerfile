FROM openjdk:17
WORKDIR /HEALTH-IT
EXPOSE 9091
COPY ./build/libs/health-it-0.0.1-SNAPSHOT.jar health-it.jar
CMD ["java", "-jar", "health-it.jar"]
