package com.ofbizian;

import org.apache.camel.spring.boot.FatJarRouter;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyFatJarRouter extends FatJarRouter {

    @Override
    public void configure() throws Exception {
                from("timer://test?fixedRate=true&period=5000")
                .to("log:com.ofbizian?showAll=true&multiline=true");
    }

}
