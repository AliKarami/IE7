#!/usr/bin/env bash

rm -rf bin/*.class
javac -cp /opt/tomcat/apache-tomcat-8.0.33/lib/servlet-api.jar -sourcepath src -d bin src/brs/*.java
rm -rf ../JSPs/target/WEB-INF/classes/*
cp -rf bin/* ../JSPs/target/WEB-INF/classes
