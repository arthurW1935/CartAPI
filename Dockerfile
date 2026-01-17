FROM maven:3.8.5-openjdk-17-slim AS build

WORKDIR /app

COPY . .

RUN mvn clean verify

FROM openjdk:17.0.1-jdk-slim AS runtime

WORKDIR /app

COPY --from=build /app/target/CartAPI-0.0.1-SNAPSHOT.jar /app/CartAPI.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/CartAPI.jar"]
