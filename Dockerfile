FROM openjdk:8u131-jre-alpine
RUN apk update && apk add jq && apk add bash

ADD target/vertx-ql-0.0.1-SNAPSHOT-fat.jar /service.jar
ADD schemas /schemas

ADD docker-entrypoint.sh /docker-entrypoint.sh

WORKDIR /

EXPOSE 5701
EXPOSE 5702
EXPOSE 5703
EXPOSE 5704
EXPOSE 5705
EXPOSE 15704
EXPOSE 8083

#CMD ["java"]
ENTRYPOINT [ "/docker-entrypoint.sh" ]