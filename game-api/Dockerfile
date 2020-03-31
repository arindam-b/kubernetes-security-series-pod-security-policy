FROM maven:3.6.3-jdk-11 AS build 

ENV APP_HOME=/root/dev/app/

COPY src $APP_HOME/src
  
COPY pom.xml $APP_HOME/
  
RUN mvn -f $APP_HOME/pom.xml clean package -DskipTests

FROM openjdk:11-slim

COPY --from=build /root/dev/app/target/application.jar /usr/app/app.jar

COPY --from=build /root/dev/app/target/dependency-jars /usr/app/dependency-jars

EXPOSE 8080 

CMD java -Dcom.sun.management.jmxremote -noverify ${JAVA_OPTS} -jar /usr/app/app.jar
