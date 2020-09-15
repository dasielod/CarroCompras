FROM java:8
COPY ./target/*.jar /app/prueba-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/app/prueba-0.0.1-SNAPSHOT.jar"]