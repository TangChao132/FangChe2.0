FROM openjdk:17-alpine
WORKDIR /app

COPY ./jars/* jars/
COPY entrypoint.sh entrypoint.sh
RUN chmod +x entrypoint.sh
EXPOSE 8080 8081 8082 8888
ENTRYPOINT ["entrypoint.sh"]