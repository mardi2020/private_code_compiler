FROM openjdk:11-jdk
USER root

ADD . .

RUN chmod a+x ./main.java
RUN chmod a+x ./entrypoint.sh


ENTRYPOINT ["./entrypoint.sh"]
