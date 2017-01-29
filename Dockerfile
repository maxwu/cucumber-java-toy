# version: 1.1

FROM maven:latest
#FROM maven:3.3.9-jdk-8-onbuild
MAINTAINER maxwu "maxwunj@gmail.com"

USER root
# RUN apt-get update
RUN mkdir /var/ccm-toy 
RUN cd /var/ccm-toy
RUN git clone https://github.com/maxwu/cucumber-java-toy.git

WORKDIR /var/ccm-toy

EXPOSE 22 443 80 
ENTRYPOINT ["ls”, "-a", "-l"]
