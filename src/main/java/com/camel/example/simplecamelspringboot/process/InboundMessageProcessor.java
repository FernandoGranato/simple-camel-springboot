package com.camel.example.simplecamelspringboot.process;

import com.camel.example.simplecamelspringboot.beans.NameAddress;
import com.camel.example.simplecamelspringboot.beans.OutboundNameAddress;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InboundMessageProcessor implements Processor {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void process(Exchange exchange) throws Exception {

        NameAddress nameAddress = exchange.getIn().getBody(NameAddress.class);
        exchange.getIn().setBody(new OutboundNameAddress(nameAddress.getName(), returnOutboundAddress(nameAddress)));
        exchange.getIn().setHeader("consumedId", nameAddress.getId());
    }

    private String returnOutboundAddress(NameAddress nameAddress) {
        return nameAddress.getHouseNumber() + " " + nameAddress.getCity() + " " + nameAddress.getProvince() + " " + nameAddress.getPostalCode();
    }
}
