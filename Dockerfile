FROM tomcat:9-jre8-alpine

RUN ["rm", "-fr", "/usr/local/tomcat/webapps/ROOT"]

ARG WAR_FILE=target/*.war

ADD ${WAR_FILE} /usr/local/tomcat/webapps/ROOT.war
