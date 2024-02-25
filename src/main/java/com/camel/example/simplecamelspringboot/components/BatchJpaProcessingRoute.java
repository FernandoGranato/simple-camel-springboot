package com.camel.example.simplecamelspringboot.components;

import com.camel.example.simplecamelspringboot.beans.NameAddress;
import com.camel.example.simplecamelspringboot.process.InboundMessageProcessor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class BatchJpaProcessingRoute extends RouteBuilder {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void configure() throws Exception {
        from("timer:readDB?period=10000")
                .routeId("readDBId")
                .to("jpa:" + NameAddress.class.getName() + "?namedQuery=fetchAllRows")
                .split(body())
                .process(new InboundMessageProcessor())
                .log(LoggingLevel.INFO, "Transformed body: ${body}")
                .convertBodyTo(String.class)
                .to("file:src/data/output?fileName=outputFile.csv&fileExist=append&appendChars=\\n")
                .toD("jpa:"+ NameAddress.class.getName()+ "?nativeQuery=DELETE FROM NAME_ADDRESS WHERE id= ${header.consumedId}&useExecuteUpdate=true")
                .end();
    }
}
