package com.ofbizian;

import org.apache.camel.CamelContext;
import org.apache.camel.spring.boot.CamelSpringBootApplicationController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("file:/etc/config/service.properties")
public class Components {

	@Value("${host.name}")
	private String hostName;

    @Bean
    CamelSpringBootApplicationController applicationController(ApplicationContext applicationContext, CamelContext camelContext) {
        return new CamelSpringBootApplicationController(applicationContext, camelContext);
    }

    @Bean(name = "counterBean")
    SomeBean counterBean() {
        return new SomeBean(hostName);
    }
}
