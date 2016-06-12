/*
 * Made in 2016.
 * http://www.apache.org/licenses/LICENSE-2.0
 * me@davengeo.com
 */
package com.davengeo.jms.sender;

import com.davengeo.jms.conf.SenderProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;

public class ReactiveSender {

    private final JmsTemplate jmsTemplate;
    private final SenderProperties senderProperties;

    @Autowired
    public ReactiveSender(JmsTemplate jmsTemplate, SenderProperties senderProperties) {
        this.jmsTemplate = jmsTemplate;
        this.senderProperties = senderProperties;
    }

    public void convertAndSend(String destination, Object pojo, String correlationId, MessagePostProcessor messagePostProcessor) {
        jmsTemplate.convertAndSend(destination, pojo, message -> {
            message.setJMSCorrelationID(correlationId);
            messagePostProcessor.postProcessMessage(message);
            return message;
        });
    }

    public void convertAndSend(Object pojo) {
        jmsTemplate.convertAndSend(senderProperties.getDestination());
    }


}
