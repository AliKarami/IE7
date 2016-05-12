#!/usr/bin/env bash

APP_CONTEXT=bourse

rm -rf $APP_CONTEXT.war

cd target
jar -cvf ../$APP_CONTEXT.war ./*
