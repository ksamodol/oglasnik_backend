package io.github.ksamodol.oglasnikbackend.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {
    private String base64Secret;
    private Integer validityInSeconds;

    public String getBase64Secret() {
        return base64Secret;
    }

    public void setBase64Secret(String base64Secret) {
        this.base64Secret = base64Secret;
    }

    public Integer getValidityInSeconds() {
        return validityInSeconds;
    }

    public void setValidityInSeconds(Integer validityInSeconds) {
        this.validityInSeconds = validityInSeconds;
    }
}
