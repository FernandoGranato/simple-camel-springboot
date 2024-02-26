package com.camel.example.simplecamelspringboot.process;

import com.camel.example.simplecamelspringboot.beans.NameAddress;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;


public class InboundRestProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        NameAddress nameAddress = exchange.getIn().getBody(NameAddress.class);
        exchange.getIn().setHeader("userCity", nameAddress.getCity());
    }
}
