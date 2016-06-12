/*
 * Copyright (c) 2016 Proximus.
 * me@davengeo.com
 */
package com.davengeo.jms.conf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MarshallingMessageConverter;

import javax.jms.ConnectionFactory;

@Slf4j
public class JmsTemplatePostProcessor implements BeanPostProcessor {

    private static final String LOG_PREFIX = "******************";
    private final SenderProperties senderProperties;
    private final ConnectionFactory connectionFactory;
    private final MarshallingMessageConverter marshallingMessageConverter;

    public JmsTemplatePostProcessor(SenderProperties senderProperties,
                                    ConnectionFactory connectionFactory,
                                    MarshallingMessageConverter marshallingMessageConverter) {
        this.senderProperties = senderProperties;
        this.connectionFactory = connectionFactory;
        this.marshallingMessageConverter = marshallingMessageConverter;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof JmsTemplate) {
            ((JmsTemplate) bean).setConnectionFactory(connectionFactory);
            ((JmsTemplate) bean).setDefaultDestinationName(senderProperties.getDestination());
            ((JmsTemplate) bean).setMessageConverter(marshallingMessageConverter);
            ((JmsTemplate) bean).setTimeToLive(senderProperties.getTimeToLive());
            ((JmsTemplate) bean).setMessageTimestampEnabled(true);
            log.info("{}Custom JmsTemplate ready to use.", LOG_PREFIX);
        }
        return bean;
    }
}
