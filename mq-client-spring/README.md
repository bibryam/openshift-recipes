# Camel ActiveMQ client with Spring

### Description
Demonstrates usage of Camel Spring DSL, Fabric8 MQ client.

Creates a new project, and gives the default service account view role. That is needed for A-MQ Kube discovery protocol to query Kubernetes.
The example docker container, expects environment variables for the username and password to connect to A-MQ.

### Start an ActiveMQ Broker on OpenShift

    oc new-project mq-client-spring
    oc policy add-role-to-user view system:serviceaccount:mq-client-spring:default
    oc process -f amq62-basic.json -v MQ_USERNAME=admin,MQ_PASSWORD=admin | oc create -f -

### Run the project on OpenShift
    mvn clean install -s configuration/settings.xml
    mvn -Pf8-local-deploy -s configuration/settings.xml

### Notes
Notice that the broker service name dooesn't have any underscore or dash as those characters cause a bug that prevents the client to discover the broker.
