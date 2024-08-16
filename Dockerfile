FROM eclipse-temurin:17
COPY target/employee.jar employee.jar
CMD ["java","-jar","employee.jar"]