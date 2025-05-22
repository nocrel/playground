FROM tomcat:10.1-jdk17-temurin

WORKDIR /app

COPY build/libs/playground-0.0.1-SNAPSHOT-plain.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]