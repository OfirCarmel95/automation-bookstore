FROM maven:3.8.4-jdk-11-slim

WORKDIR /automation-bookstore

COPY . .

COPY pom.xml /tmp/pom.xml

RUN mvn -B -f /tmp/pom.xml -s /usr/share/maven/ref/settings-docker.xml dependency:resolve



