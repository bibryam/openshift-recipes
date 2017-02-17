# Camel Spring Boot Config Map Example

### Description
Demonstrates usage of Camel Java DSL and Spring.
It uses Kubernetes ConfigMap to pass the configuration to the docker container.

This quickstart run Apache Camel in a standalone Java Spring Boot container.
It is based on Fuse 6.2 hence Camel 2.15.1 and corresponding Spring version.


### Build the project

    oc new-project spring-boot-configmap

    oc create configmap demo --from-file=service.properties
    oc describe configmap demo
    oc edit configmap demo

    mvn clean install -s configuration/settings.xml
    mvn -Pf8-local-deploy -s configuration/settings.xml

    oc rsh $(oc get pods -l component=fis-camel-spring-boot-demo --template '{{range .items}}{{.metadata.name}}{{end}}') cat /etc/config/service.properties

### Option 1: Building (with hawt-app-maven-plugin) and running locally

    mvn clean install -s configuration/settings.xml
    target/hawt-app/bin/run.sh

### Option 2: Building (with spring-boot-maven-plugin) and running locally

    mvn clean install -s configuration/settings.xml -Pspring-boot-maven-plugin
    mvn spring-boot:run -s configuration/settings.xml -Pspring-boot-maven-plugin

### Run locally

    mvn clean install exec:java

### Running the example in OpenShift
The example can be built and deployed using a single goal:

    mvn -Pf8-deploy

### Running the example using OpenShift S2I template
The example can also be built and run using the included S2I template quickstart-template.json.

    oc new-app -f quickstart-template.json


