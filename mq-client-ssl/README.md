# Camel ActiveMQ client with Spring

### Description

Simple AMQ Client for sending a receiving a message to an AMQ broker with SSL.  Can act as a producer or consumer based on the
parameters passed to the template.

### Start an ActiveMQ Broker on OpenShift

    oc new-project mq-client-ssl
    oc secrets new client-truststore src/main/resources/client.ts

### Run the project on OpenShift

    mvn clean install -s configuration/settings.xml
    mvn -Pf8-local-deploy -s configuration/settings.xml
