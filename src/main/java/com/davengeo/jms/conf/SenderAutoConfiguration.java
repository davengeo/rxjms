/*
 * Made in 2016.
 * http://www.apache.org/licenses/LICENSE-2.0
 * me@davengeo.com
 */
package com.davengeo.jms.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MarshallingMessageConverter;

import javax.jms.ConnectionFactory;

@Configuration
@ConditionalOnProperty(value = "jms.sender.enable", havingValue = "true")
@ConditionalOnClass(JmsTemplate.class)
@EnableConfigurationProperties({SenderProperties.class})
public class SenderAutoConfiguration {

  private final SenderProperties senderProperties;

  @Autowired
  public SenderAutoConfiguration(SenderProperties senderProperties) {
    this.senderProperties = senderProperties;
  }

  @Bean
  public JmsTemplatePostProcessor jmsTemplatePostProcessor(ConnectionFactory connectionFactory,
                                                           MarshallingMessageConverter marshallingMessageConverter) {
    return new JmsTemplatePostProcessor(senderProperties, connectionFactory, marshallingMessageConverter);
  }
}
