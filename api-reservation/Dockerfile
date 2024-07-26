FROM openjdk:21-slim

COPY target/api-reservation-*.jar application/api-reservation.jar

CMD java -jar application/api-reservation.jar --spring.config.location=classpath:/application-docker.yml

EXPOSE 8070
