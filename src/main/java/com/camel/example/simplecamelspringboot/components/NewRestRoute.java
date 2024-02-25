package com.camel.example.simplecamelspringboot.components;

import com.camel.example.simplecamelspringboot.beans.NameAddress;
import com.camel.example.simplecamelspringboot.process.InboundMessageProcessor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class NewRestRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        restConfiguration()
                .component("jetty")
                .host("0.0.0.0")
                .port(8080)
                .bindingMode(RestBindingMode.json)
                .enableCORS(true);

        rest("masterClass")
                .produces("application/json")
                .post("nameAddress").type(NameAddress.class)
                .route()
                .routeId("myRestRouteId")
//                .log(LoggingLevel.INFO, "${body}")
//                .process(new InboundMessageProcessor())
//                .log(LoggingLevel.INFO, "Transformed body: ${body}")
//                .convertBodyTo(String.class)
//                .to("file:src/data/output?fileName=outputFile.csv&fileExist=append&appendChars=\\n");
                .to("jpa:" + NameAddress.class.getName());

    }


}
