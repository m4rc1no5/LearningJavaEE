#!/usr/bin/env bash
cp -f ../../LearningJavaEE/target/LearningJavaEE.war LearningJavaEE.war
docker build -t marceen/learning_java_ee .
docker run -d -p 8282:8080 -p 8787:8787 --name learning_java_ee marceen/learning_java_ee