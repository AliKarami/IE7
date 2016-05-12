#!/usr/bin/env bash

rm -rf bourse.war

cd target
jar -cvf ../bourse.war ./*
