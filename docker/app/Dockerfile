FROM marceen/wildfly10
MAINTAINER Marcin Zaremba, marcin.zaremba@gmail.pl

# build bash history
RUN printf "less /opt/wildfly-10.1.0.Final/standalone/log/server.log\n" >> /root/.bash_history

# deploy
COPY LearningJavaEE.war ${DEPLOYMENT_DIR}

EXPOSE 8080
