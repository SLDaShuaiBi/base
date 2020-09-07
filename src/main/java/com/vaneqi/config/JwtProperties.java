package com.vaneqi.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author qinlei
 * @Date 2020/8/19
 * @Desc
 */
@Component
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtProperties {
    private String header;
    private String headerPrefix;
    private String secret;
    private Long expiration;
}
