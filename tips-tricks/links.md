# Links


https://10.1.2.2:8443/swaggerapi/oapi/v1


OSE demo examples
https://github.com/ffirg/openshift


Working with OpenShift configurations
https://blog.openshift.com/working-openshift-configurations/

$ oc types
oc get svc


oc edit bc/wombat
One can also though use:
oc edit bc wombat

oc explain bc


bilgin:cdk bibryam$ oc explain  svc.metadata

bilgin:cdk bibryam$ oc edit svc shell -o json

bilgin:cdk bibryam$ oc edit svc shell -o json

$ oc get bc/wombat -o json > config.json

$ oc replace -f config.json





$ oc explain bc.spec.serviceAccount
$ oc patch bc/wombat --patch '{"spec":{"serviceAccount": "secret:spy"}}'
$ oc get bc/wombat -o json > config.json
$ oc replace -f config.json



oc new-app wildfly:10.0~https://github.com/bibryam/bootwildfly.git
curl     172.30.117.241:8080/hello

 oc expose service bootwildfly
http://bootwildfly-bootwildfly.rhel-cdk.10.1.2.2.xip.io/hello


oc scale dc bootwildfly –replicas=5

