#!/bin/bash

. ~/newsbox/resources/env.profile

JARS=`ls $APP_HOME/jars/* | tr [:space:] ":"`

java -cp "$JARS$RESOURCES" \
    -Denv=$environment \
    -Dlog4j.configuration=log4j-prod.properties \
    com.zelious.java.newsbox.MainServer &
