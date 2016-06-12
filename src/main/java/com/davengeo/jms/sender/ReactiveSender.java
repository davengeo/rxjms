/*
 * Made in 2016.
 * http://www.apache.org/licenses/LICENSE-2.0
 * me@davengeo.com
 */
package com.davengeo.jms.sender;

import com.davengeo.jms.conf.SenderProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import reactor.core.publisher.Mono;

public class ReactiveSender {

    private final JmsTemplate jmsTemplate;
    private final SenderProperties senderProperties;

    @Autowired
    public ReactiveSender(JmsTemplate jmsTemplate, SenderProperties senderProperties) {
        this.jmsTemplate = jmsTemplate;
        this.senderProperties = senderProperties;
    }

    public void convertAndSend(String destination, Object pojo,
                               String correlationId) {
        jmsTemplate.convertAndSend(destination, pojo, message -> {
            message.setJMSCorrelationID(correlationId);
            return message;
        });
    }

    Mono<Void> send(Object pojo) {
        return Mono.create(emitter -> {
            try {
                this.convertAndSend(senderProperties.getDestination(), pojo, "");
                emitter.complete();
            } catch (Exception ex) {
                emitter.fail(ex);
            }
        })
          .log()
          .then();
    }


}
