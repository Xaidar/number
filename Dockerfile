FROM java:8
EXPOSE 8080
ADD target/number-0.0.1.jar number-0.0.1.jar
ENTRYPOINT ["java", "-jar", "/number-0.0.1.jar"]