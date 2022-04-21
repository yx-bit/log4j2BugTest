FROM openjdk:8-jdk-alpine
MAINTAINER  202402590@qq.com
RUN mkdir -p /home/test && cd /home/test
WORKDIR /home/test
COPY src/main/java/Server.java  /home/test/Server.java
RUN  javac -encoding utf-8 Server.java
EXPOSE 1099
EXPOSE 10990
ENTRYPOINT ["java", "Server"]