package com.ofbizian.buildconfig;

import com.google.common.collect.ImmutableList;
import io.fabric8.kubernetes.api.model.*;
import io.fabric8.openshift.api.model.DeploymentConfigBuilder;
import io.fabric8.openshift.api.model.DeploymentTriggerImageChangeParams;
import io.fabric8.openshift.api.model.DeploymentTriggerPolicy;
import io.fabric8.openshift.api.model.RollingDeploymentStrategyParams;
import io.fabric8.utils.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeploymentConfigKubernetesModelProcessor {

    public void on(DeploymentConfigBuilder builder) {
        builder.withSpec(builder.getSpec())
                .editSpec()
                    .withReplicas(1)
                    .withSelector(getSelectors())
                    .withNewStrategy()
                        .withType("Rolling")
                        .withRollingParams(getRollingDeploymentStrategyParams())
                    .endStrategy()
                    .editTemplate()
                        .editSpec()
                            .withContainers(getContainers())
                            .withRestartPolicy("Always")
                            .withVolumes(getVolumes())
                        .endSpec()
                    .endTemplate()
                    .withTriggers(getTriggers())
                .endSpec()
            .build();
    }

    private List<Volume> getVolumes(){

        Volume amqSecret = new Volume();
        amqSecret.setSecret(new SecretVolumeSource("client-truststore"));
        amqSecret.setName("truststore");
        return new ImmutableList.Builder<Volume>().add(amqSecret).build();

    }

    private List<VolumeMount> getVolumeMounts(){
        return new ImmutableList.Builder<VolumeMount>()
                .add(new VolumeMount("/var/run/secrets/truststore","truststore",true))
                .build();
    }

    private RollingDeploymentStrategyParams getRollingDeploymentStrategyParams() {
        RollingDeploymentStrategyParams rolling = new RollingDeploymentStrategyParams();
        rolling.setTimeoutSeconds(new Long(240));
        rolling.setMaxSurge(new IntOrString("30%"));
        rolling.setMaxUnavailable(new IntOrString("20%"));
        return rolling;
    }

    private List<DeploymentTriggerPolicy> getTriggers() {
        DeploymentTriggerPolicy configChange = new DeploymentTriggerPolicy();
        configChange.setType("ConfigChange");

        ObjectReference from = new ObjectReference();
        from.setName("amq-client-ssl:latest");
        from.setKind("ImageStreamTag");

        DeploymentTriggerImageChangeParams imageChangeParms = new DeploymentTriggerImageChangeParams();
        imageChangeParms.setFrom(from);
        imageChangeParms.setAutomatic(true);

        DeploymentTriggerPolicy imageChange = new DeploymentTriggerPolicy();
        imageChange.setType("ImageChange");
        imageChange.setImageChangeParams(imageChangeParms);
        imageChangeParms.setContainerNames(Lists.newArrayList("amq-client-ssl"));

        List<DeploymentTriggerPolicy> triggers = new ArrayList<DeploymentTriggerPolicy>();
        triggers.add(configChange);
        triggers.add(imageChange);

        return triggers;
    }

    private List<ContainerPort> getPorts() {
        List<ContainerPort> ports = new ArrayList<ContainerPort>();
        ContainerPort jolokia = new ContainerPort();
        jolokia.setContainerPort(8778);
        jolokia.setProtocol("TCP");
        jolokia.setName("jolokia");
        ports.add(jolokia);

        return ports;
    }

    private List<EnvVar> getEnvironmentVariables() {
        List<EnvVar> envVars = new ArrayList<EnvVar>();

        envVars.add(new EnvVar("BROKER_URL","${BROKER_URL}",null));
        envVars.add(new EnvVar("BROKER_USERNAME","${BROKER_USERNAME}",null));
        envVars.add(new EnvVar("BROKER_PASSWORD","${BROKER_PASSWORD}",null));
        envVars.add(new EnvVar("BROKER_TRUSTSTORE_PASSWORD","${BROKER_TRUSTSTORE_PASSWORD}",null));
        envVars.add(new EnvVar("BROKER_TRUSTSTORE_FILE","${BROKER_TRUSTSTORE_FILE}",null));
        envVars.add(new EnvVar("AMQ_CLIENT_TYPE","${AMQ_CLIENT_TYPE}",null));
        envVars.add(new EnvVar("AMQ_CONSUMER_PRODUCER_START_URI","${AMQ_CONSUMER_PRODUCER_START_URI}",null));
        envVars.add(new EnvVar("AMQ_CONSUMER_PRODUCER_END_URI","${AMQ_CONSUMER_PRODUCER_END_URI}",null));

        return envVars;
    }

    private Container getContainers() {
        Container container = new Container();
        container.setImage("${IS_PULL_NAMESPACE}/amq-client-ssl:latest");
        container.setImagePullPolicy("Always");
        container.setName("amq-client-ssl");
        container.setPorts(getPorts());
        container.setEnv(getEnvironmentVariables());
        container.setVolumeMounts(getVolumeMounts());

        return container;
    }

    private Map<String, String> getSelectors() {
        Map<String, String> selectors = new HashMap<>();
        selectors.put("app", "amq-client-ssl");
        selectors.put("deploymentconfig", "amq-client-ssl");

        return selectors;
    }

}
