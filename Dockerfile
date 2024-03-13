FROM openjdk:8
COPY ./target/demo001-0.0.1-SNAPSHOT.jar demo001-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","demo001 ho-0.0.1-SNAPSHOT.jar"]