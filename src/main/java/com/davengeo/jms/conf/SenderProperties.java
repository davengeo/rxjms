/*
 * Made in 2016.
 * http://www.apache.org/licenses/LICENSE-2.0
 * me@davengeo.com
 */
package com.davengeo.jms.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "jms.sender.conf", ignoreUnknownFields = true)
public class SenderProperties {

  private Long timeToLive;
  private String destination;
  private String listener;
  private String prefix;
  private String usage;
  private List<String> jaxbContexts;

}
