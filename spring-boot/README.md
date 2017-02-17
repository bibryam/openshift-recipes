# Camel Spring Boot Example

This quickstart runs Apache Camel in a standalone Java Spring Boot container.
It is based on Fuse 6.2 hence Camel 2.15.1 and corresponding Spring versions.

### Option 1: Building (with hawt-app-maven-plugin) and running locally

    mvn clean install -s configuration/settings.xml
    target/hawt-app/bin/run.sh

### Option 2: Building (with spring-boot-maven-plugin) and running locally

    mvn clean install -s configuration/settings.xml -Pspring-boot-maven-plugin
    mvn spring-boot:run -s configuration/settings.xml -Pspring-boot-maven-plugin

### Run the project on OpenShift
The example can be built and deployed using a single goal:

    mvn -Pf8-deploy

### Running the example using OpenShift S2I template
The example can also be built and run using the included S2I template quickstart-template.json.

    oc new-app -f quickstart-template.json
