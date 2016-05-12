#!/usr/bin/env bash

sudo rm -rf /opt/tomcat/apache-tomcat-8.0.33/webapps/bourse.war
sudo rm -rf /opt/tomcat/apache-tomcat-8.0.33/webapps/bourse/*
cp -r bourse.war /opt/tomcat/apache-tomcat-8.0.33/webapps
