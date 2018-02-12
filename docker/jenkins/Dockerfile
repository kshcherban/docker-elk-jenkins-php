FROM jenkins/jenkins:2.89.3-alpine

COPY plugins.txt /usr/share/jenkins/plugins.txt

RUN install-plugins.sh `cat /usr/share/jenkins/plugins.txt`

# install curator for ES clean job
USER root

RUN apk --update add python py-setuptools py-pip jq && \
    pip install elasticsearch-curator==5.4.0 && \
    apk del py-pip && \
    rm -rf /var/cache/apk/*

ENV JAVA_OPTS="-Dpermissive-script-security.enabled=true -Djenkins.install.runSetupWizard=false"

USER jenkins

COPY seed-job/ /usr/share/jenkins/ref/jobs/_seed-job

COPY init_scripts/*.groovy  /usr/share/jenkins/ref/init.groovy.d/

COPY jobs/ /tmp/jobs/

# COPY can chown on the fly in newer docker but i have older one installed
#RUN chown -Rv jenkins:jenkins /var/jenkins_home/ /usr/share/jenkins/ref/init.groovy.d/
