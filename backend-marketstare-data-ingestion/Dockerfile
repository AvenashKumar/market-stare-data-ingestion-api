FROM adoptopenjdk/openjdk16:alpine-slim

WORKDIR /apps/backend
COPY ./target/backend-marketstare-data-ingestion-0.0.1-SNAPSHOT.jar /apps/backend/backend-marketstare-data-ingestion.jar
RUN ln -snf /usr/share/zoneinfo/America/New_York /etc/localtime && echo "America/New_York" > /etc/timezone
ENTRYPOINT ["java", "-jar", "backend-marketstare-data-ingestion.jar"]
EXPOSE 8080