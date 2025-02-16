FROM openjdk:23-jdk

WORKDIR /app

COPY target/transaction-api-0.0.1-SNAPSHOT.jar /app/transaction-api.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/transaction-api.jar"]