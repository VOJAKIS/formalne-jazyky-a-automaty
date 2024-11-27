#!/bin/bash

if [ ! -f target/*.jar ]; then
	mvn clean package
fi

java -jar target/*.jar