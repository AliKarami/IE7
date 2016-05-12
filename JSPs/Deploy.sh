#!/usr/bin/env bash

APP_CONTEXT=bourse
rm -rf $CATALINA_HOME/webapps/$APP_CONTEXT.war
rm -rf $CATALINA_HOME/webapps/$APP_CONTEXT/*
cp -r $APP_CONTEXT.war $CATALINA_HOME/webapps
