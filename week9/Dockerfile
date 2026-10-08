# ==============================================================================
# CAMPUS PLACEMENT AND INTERNSHIP PORTAL — DOCKERFILE
# Module 5: Database Architecture, Integration Layer & System Testing
# Author: Himanshu Yadav (XXHimanshuXX | himanshu.yadav060107@gmail.com)
# ==============================================================================

# STAGE 1: Build Java WAR Artifact using Maven & JDK 11
FROM maven:3.9-eclipse-temurin-11 AS build

WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests

# STAGE 2: Production Containerization on Apache Tomcat 9
FROM tomcat:9.0-jdk11

# Purge default web applications to keep container lightweight
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy packaged WAR as default ROOT application
COPY --from=build /app/target/campus-placement-portal-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# Expose standard HTTP port
EXPOSE 8080

# Launch Catalina Servlet Engine
CMD ["catalina.sh", "run"]
