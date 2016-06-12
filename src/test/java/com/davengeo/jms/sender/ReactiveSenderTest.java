package com.davengeo.jms.sender;

import com.davengeo.jms.conf.SenderProperties;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jms.core.JmsTemplate;

@RunWith(MockitoJUnitRunner.class)
public class ReactiveSenderTest {

  @Mock
  JmsTemplate jmsTemplate;
  private ReactiveSender reactiveSender;

  @Before
  public void setup() {
    SenderProperties senderProperties = new SenderProperties();
    senderProperties.setDestination("dest");
    reactiveSender = new ReactiveSender(jmsTemplate, senderProperties);
  }



  @Test
  public void testSend() throws Exception {
    String pojo = "pojo";

    reactiveSender.send(pojo);

  }
}
