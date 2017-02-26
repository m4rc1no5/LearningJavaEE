#!/usr/bin/env bash
docker rm -f $(docker ps | grep marceen/learning_java_ee | awk {'print $1'})