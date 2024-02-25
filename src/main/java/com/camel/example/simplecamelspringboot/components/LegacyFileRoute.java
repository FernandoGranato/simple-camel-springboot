package com.camel.example.simplecamelspringboot.components;

import com.camel.example.simplecamelspringboot.process.InboundMessageProcessor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.beanio.BeanIODataFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LegacyFileRoute extends RouteBuilder {
    Logger logger = LoggerFactory.getLogger(getClass());
    BeanIODataFormat inboundDataFormat = new BeanIODataFormat("InboundMessageBeanIOMapping.xml", "inputMessageStream");

    @Override
    public void configure() throws Exception {

        from("file:src/data/input?fileName=inputFile.csv")
                .routeId("legacyFileMoveRoute")
                .split(body().tokenize("\n", 1, true))
                .unmarshal(inboundDataFormat)
                .process(new InboundMessageProcessor())
                .log(LoggingLevel.INFO, "Transformed body: ${body}")
                .convertBodyTo(String.class)
                .to("file:src/data/output?fileName=outputFile.csv&fileExist=append&appendChars=\\n")
                .end();
    }
}
