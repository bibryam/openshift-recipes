package com.ofbizian.route;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

public class SimpleAMQRouteBuilder extends RouteBuilder {

    @Value("AMQ_CLIENT_TYPE")
    private String producerOrConsumer;

    @Value("AMQ_CONSUMER_PRODUCER_START_URI")
    private String startURI;

    @Value("AMQ_CONSUMER_PRODUCER_END_URI")
    private String endURI;

    Logger log = LoggerFactory.getLogger(this.getClass());

    public void configure() {

        log.info("configuring routebuilder");

        if(producerOrConsumer.equalsIgnoreCase("producer")){

            log.info("building producer route");

            from(startURI)
                    .setBody(constant("{ \"hello\": \"world\" }"))
                    .setHeader("hello", constant("world"))
                    .log("Sending message...")
                    .to(endURI).log("Sent!");
        }

        if(producerOrConsumer.equalsIgnoreCase("consumer")){

            log.info("building consumer route");

            from(startURI).log("Got message: ${body}");
        }

    }

    public String getProducerOrConsumer() {
        return producerOrConsumer;
    }

    public void setProducerOrConsumer(String producerOrConsumer) {
        this.producerOrConsumer = producerOrConsumer;
    }

    public String getStartURI() {
        return startURI;
    }

    public void setStartURI(String startURI) {
        this.startURI = startURI;
    }

    public String getEndURI() {
        return endURI;
    }

    public void setEndURI(String endURI) {
        this.endURI = endURI;
    }
}
