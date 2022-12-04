FROM alpine:latest

WORKDIR /

COPY . .

RUN .mvnw/ clean package

EXPOSE 8084

# Start app
CMD ["java", "-jar", "/target/springboot-backend-0.0.1-SNAPSHOT.jar"]

