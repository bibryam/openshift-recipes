package com.ofbizian.buildconfig;

import io.fabric8.kubernetes.generator.annotation.KubernetesModelProcessor;
import io.fabric8.openshift.api.model.DeploymentConfigBuilder;
import io.fabric8.openshift.api.model.ImageStreamBuilder;
import io.fabric8.openshift.api.model.TemplateBuilder;

@KubernetesModelProcessor
public class MainKubernetesModelProcessor {

    public void withDeploymentConfigBuilderTemplate(DeploymentConfigBuilder builder) {
        new DeploymentConfigKubernetesModelProcessor().on(builder);
    }

    public void withImageStreamBuilderTemplate(ImageStreamBuilder builder) {
        new ImageStreamKubernetesModelProcessor().on(builder);
    }

    public void withTemplateBuilder(TemplateBuilder builder) {
        new BuildConfigKubernetesModelProcessor().on(builder);
    }
}
