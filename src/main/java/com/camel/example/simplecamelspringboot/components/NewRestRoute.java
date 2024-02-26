package com.camel.example.simplecamelspringboot.components;
import com.camel.example.simplecamelspringboot.beans.NameAddress;
import com.camel.example.simplecamelspringboot.process.InboundRestProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Predicate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import java.net.ConnectException;

@Component
public class NewRestRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        Predicate isAjaxCity = header("userCity").isEqualTo("Ajax");

        onException(JMSException.class, ConnectException.class)
                .routeId("jmsExceptionRoute")
                .handled(true)
                .log(LoggingLevel.INFO, "JMS exception has occurred; handling gracefully")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(502))
                .transform()
                .simple("Message processed and result generated with body: ${body}");


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
                .process(new InboundRestProcessor())
                //Setup rule
                .choice()
                .when(isAjaxCity)
                    .to("direct:toQueue")
                .otherwise()
                    .to("direct:toDB")
                    .to("direct:toQueue")
                .end()
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200))
                .transform()
                .simple("Message processed and result generated with body: ${body}")
                .endRest();


        from("direct:toDB")
                .routeId("toDBId")
                .log(LoggingLevel.INFO, ">>>> In to DB endpoint")
                .to("jpa:" + NameAddress.class.getName());

        from("direct:toQueue")
                .routeId("toQueueId")
                .log(LoggingLevel.INFO, ">>>> In to QUEUE endpoint")
                .to("activemq:queue:nameaddressqueue?exchangePattern=InOnly");

    }


}
