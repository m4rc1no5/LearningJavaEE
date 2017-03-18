#!/usr/bin/env bash
cp -f ../../Processor/target/Processor.war Processor.war
docker build -t marceen/processor .
docker run -d -p 8283:8080 --name processor marceen/processor