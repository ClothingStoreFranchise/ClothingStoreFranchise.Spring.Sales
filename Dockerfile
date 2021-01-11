FROM adoptopenjdk/openjdk11:alpine-jre

EXPOSE 8085

ADD target/sales.jar sales.jar

ENTRYPOINT ["java", "-jar", "sales.jar"]