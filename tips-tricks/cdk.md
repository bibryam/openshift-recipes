# CDK



cd /Users/bibryam/_archive/cdk/.vagrant/machines/default/virtualbox/.docker/
bilgin:.docker bibryam$ ls -la

scp -i /Users/bibryam/_archive/cdk/.vagrant/machines/default/virtualbox/private_key -P 2222 vagrant@127.0.0.1:/home/vagrant/.docker/* /Users/bibryam/_archive/cdk/.vagrant/machines/default/virtualbox/.docker/



curl https://$HOST:2376/images/json \
  --cert ~/.docker/cert.pem \
  --key ~/.docker/key.pem \
  --cacert ~/.docker/ca.pem


curl https://10.1.2.2:2376/images/json   --cert cert.pem   --key key.pem   --cacert ca.pem
docker  --tlscacert=ca.pem --tlscert=cert.pem --tlskey=key.pem   -H=10.1.2.2:2376 version


vagrant ssh-config
scp -i /Users/bibryam/_archive/cdk/.vagrant/machines/default/virtualbox/private_key -P 2222 vagrant@127.0.0.1:/home/vagrant/.docker/* /Users/bibryam/_archive/cdk/.vagrant/machines/default/virtualbox/.docker/

oc login 10.0.2.15:8443 --username=system:admin -n default --certificate-authority=/var/lib/openshift/openshift.local.config/master/ca.crt

oc login -u system:admin
mkdir ~/.kube/
ln -s  /var/lib/openshift/openshift.local.config/master/admin.kubeconfig  config

ab -n 100  http://payroll-app-wildfly-swarm.rhel-cdk.10.1.2.2.xip.io/payroll

oc new-app --docker-image=myfear/swarm-sample:latest

oc describe service swarm-sample

oc expose service swarm-sample --hostname=wildfly-swarm.vagrant.f8

oc policy add-role-to-user system:service account:<project>:turbine
oc policy add-role-to-user admin system:serviceaccount:wildfly-swarm:turbine
https://10.1.2.2:8443/api/v1/endpoints?labelSelector=hystrix.enabled=true


payroll-app-wildfly-swarm.rhel-cdk.10.1.2.2.xip.io/hystrix.stream


These are the steps that I took when I tried to run kubeflix in CDK:

- oc create -f http://central.maven.org/maven2/io/fabric8/kubeflix/packages/kubeflix/1.0.15/kubeflix-1.0.15-kubernetes.yml

- oc new-app kubeflix

- oc expose service turbine-server --hostname=turbine-server.10.1.2.2.xip.io

- oc policy add-role-to-user cluster-admin account:helloworld-msa:turbine

- curl http://turbine-server.10.1.2.2.xip.io/discovery

Docker taging

docker tag docker.io/java:openjdk-8-jdk 172.30.124.220:5000/openshift/java:openjdk-8-jdk
docker tag docker.io/java:openjdk-8-jdk hub.openshift.10.1.2.2.xip.io:5000/openshift/java:openjdk-8-jdk
docker push 172.30.106.172:5000/openshift/java:openjdk-8-jdk
docker login -u admin -e admin@google.com -p mdIZ6pcM34FiHNhoFEXCiyA2dKwRTGCJkBOaeymb8R0 172.30.106.172:5000


Dockerfile’s that were used to build the image are available in the image in the /root/buildinfo folder


1. oc login 10.0.2.15:8443 --username=system:admin -n default --certificate-authority=/var/lib/origin/openshift.local.config/master/ca.crt  








