package com.camel.example.simplecamelspringboot;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTestContextBootstrapper;
import org.springframework.test.context.BootstrapWith;

@CamelSpringBootTest
@SpringBootTest
class LegacyFileRouteTest {

    @Autowired
    private CamelContext context;

    @EndpointInject("mock:result")
    protected MockEndpoint mockEndpoint;

    @Autowired
    private ProducerTemplate producerTemplate;

    /**
     * Mock From and To using producer
     *
     * @throws Exception
     */
    @Test
    public void testFileMoveByMockingFromEndpoint() throws Exception {
//        String expectedBody = "OutboundNameAddress(name=Fernando, address= 11  Cerignola  Foggia  71042)";
//        mockEndpoint.expectedBodiesReceived(expectedBody);
//        mockEndpoint.expectedMinimumMessageCount(1);
//
//        AdviceWith.adviceWith(context, "legacyFileMoveRoute", routeBuilder -> {
//            routeBuilder.replaceFromWith("direct:mockStart");
//            routeBuilder.weaveByToUri("file:*").replace().to(mockEndpoint);
//        });
//
//        context.start();
//        producerTemplate.sendBody("direct:mockStart", "name, houseNumber, city, province, postalCode".concat("\n").concat("Fernando, 11, Cerignola, Foggia, 71042"));
//        mockEndpoint.assertIsSatisfied();
    }

}
