# Camel ActiveMQ client with CDI extension

### Description
Demonstrates usage of Camel Java DSL, CDI, [CDI Kubernetes extension](https://fabric8.io/guide/cdi.html) for connecting an ActiveMQ instance.

Creates a new project, and gives the default service account view role. That is needed for A-MQ Kube discovery protocol to query Kubernetes.
The example docker container, expects environment variables for the username and password to connect to A-MQ.

### Start an ActiveMQ Broker on OpenShift

    oc new-project mq-client-cdi
    oc policy add-role-to-user view system:serviceaccount:mq-client-cdi:default
    oc process -f amq62-basic.json -v MQ_USERNAME=admin,MQ_PASSWORD=admin | oc create -f -

### Run the project on OpenShift

    mvn clean install -s configuration/settings.xml
    mvn -Pf8-local-deploy -s configuration/settings.xml

### Generate the project from archetype
You can generate this project using the following archetype. More info [here](
https://access.redhat.com/documentation/en/red-hat-xpaas/version-0/red-hat-xpaas-a-mq-image/#example_deployment_workflow)

Add the following repos to your settings.xml
 * JBoss Fuse repository: https://repo.fusesource.com/nexus/content/groups/public/
 * RedHat GA repository: https://maven.repository.redhat.com/ga


    mvn archetype:generate \
      -DarchetypeCatalog=https://repo.fusesource.com/nexus/content/groups/public/archetype-catalog.xml \
      -DarchetypeGroupId=io.fabric8.archetypes \
      -DarchetypeVersion=2.2.0.redhat-079 \
      -DarchetypeArtifactId=cdi-camel-mq-archetype

