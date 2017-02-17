# Camel and CDI

### Description
The Simplest possible example that demonstrates the usage of Camel Java DSL and CDI


### Run locally

    mvn clean install exec:java

### Run the project on OpenShift

    oc new-project hello-world
    mvn clean install -s configuration/settings.xml
    mvn -Pf8-local-deploy -s configuration/settings.xml

# Hello world FIS

Choose an archetype from https://docs.openshift.com/enterprise/3.1/using_images/xpaas_images/fuse.html

Generate project interactively:

    mvn archetype:generate \
    -DarchetypeCatalog=https://repo.fusesource.com/nexus/content/groups/public/archetype-catalog.xml \
    -DarchetypeGroupId=io.fabric8.archetypes \
    -DarchetypeVersion=2.2.0.redhat-079 \
    -DarchetypeArtifactId=cdi-camel-archetype

Generate project in batch mode:

    mvn archetype:generate \
    -DarchetypeCatalog=https://repo.fusesource.com/nexus/content/groups/public/archetype-catalog.xml \
    -DarchetypeGroupId=io.fabric8.archetypes \
    -DarchetypeVersion=2.2.0.redhat-079 \
    -DarchetypeArtifactId=cdi-camel-archetype \
    -B \
    -DgroupId=com.ofbizian \
    -DartifactId=fis-demo \
    -Dversion=1.0-SNAPSHOT \
    -Dpackage=com.ofbizian


Build using preconfigured settings file:

     mvn clean install -s configuration/settings.xml


Fabric8 Maven Workflow

    oc login
    oc new-project fis-hello-world

Build
    mvn -Pf8-local-deploy

Push
    oc new-project fabric8
    oc get routes -n default -l='docker-registry=default'

    mvn docker:push \
    -Ddocker.registry=hub.openshift.10.1.2.2.xip.io \
    -Ddocker.username=$(oc whoami) \
    -Ddocker.password=$(oc whoami -t) \
    -s configuration/settings.xml

OpenShift Source-to-Image (S2I) Workflow

    oc create -n openshift -f https://raw.githubusercontent.com/jboss-fuse/application-templates/master/fis-image-streams.json
    oc create -f quickstart-template.json -n fis-hello-world
    oc process --parameters -f quickstart-template.json
    oc process --parameters -n fis-hello-world s2i-quickstart-cdi-camel


Instantiate the template

    oc process s2i-quickstart-cdi-camel  -v GIT_REPO=https://github.com/bibryam/fis-example.git
    oc process s2i-quickstart-cdi-camel  -v GIT_REPO=https://github.com/bibryam/fis-example.git | oc create -f -
    oc new-app --template=s2i-quickstart-cdi-camel -p GIT_REPO=https://github.com/bibryam/fis-example.git

Creating a Template from Existing Objects

    oc export all --as-template=<template_name>

$ git commit -am "changing my camel route"

$ git push -u origin master

$ oc start-build fis-createorderservices -n fis-project
