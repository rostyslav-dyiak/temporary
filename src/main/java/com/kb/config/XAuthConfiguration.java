package com.kb.config;

import com.kb.security.xauth.TokenProvider;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * Created by rdyyak on 24.04.15.
 */
@Configuration
public class XAuthConfiguration implements EnvironmentAware {

    private RelaxedPropertyResolver propertyResolver;

    @Override
    public void setEnvironment(Environment environment) {
        this.propertyResolver = new RelaxedPropertyResolver(environment, "authentication.xauth.");
    }

    @Bean
    public TokenProvider tokenProvider(){
        String secret = propertyResolver.getProperty("secret", String.class, "mySecretXAuthSecret");
        int validityInSeconds = propertyResolver.getProperty("tokenValidityInSeconds", Integer.class, 3600);
        return new TokenProvider(secret, validityInSeconds);
    }
}
