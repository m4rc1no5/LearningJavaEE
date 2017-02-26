# LearningJavaEE
Examples of use JavaEE

[ ![Codeship Status for m4rc1no5/LearningJavaEE](https://app.codeship.com/projects/50813ba0-7df2-0134-15a5-5a094b81b9ee/status?branch=develop)](https://app.codeship.com/projects/181546)

Documentation
=============

Table of contents
-----------------

1. [Installation](#installation)
2. [API](#api)
3. [Logs](#logs)

<a name="installation"></a>

Installation
------------

### Launch on Docker ###

1. Install docker image wildfly10 from [DockerImages](https://github.com/m4rc1no5/DockerImages)
2. Build app
    ```bash
    mvn clean install
    ```
3. Run app on Docker
    ```bash
    cd docker/app
    ./run.sh
    ```

<a name="api"></a>

API
---

- /api/rest/helloWorld/currentTime

<a name="logs"></a>

Logs
-------------

### View logs from WildFly ###

1. Execute an interactive bash shell on the container
    ```bash
    docker exec -it learning_java_ee bash
    ```
2. View server.log
    ```bash
    less /opt/wildfly-10.1.0.Final/standalone/log/server.log
    ```