package com.camel.example.simplecamelspringboot.components;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class QueueReceiverRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("activemq:queue:nameaddressqueue")
                .routeId("queueReceiverId")
                .log(LoggingLevel.INFO,">>>>>>>>>>>>>>>>> Message received from Queue: ${body}");
    }
}
