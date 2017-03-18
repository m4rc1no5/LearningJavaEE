#!/usr/bin/env bash
docker rm -f $(docker ps | grep marceen/processor | awk {'print $1'})