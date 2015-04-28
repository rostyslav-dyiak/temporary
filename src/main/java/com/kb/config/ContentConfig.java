package com.kb.config;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.kb.service.content.ContentResourceNamingStrategy;
import com.kb.service.content.ContentService;
import com.kb.service.content.DefaultContentService;

@Configuration
public class ContentConfig implements EnvironmentAware {
    private final Logger log = LoggerFactory.getLogger(ContentConfig.class);
    
	@Resource(name = "namingStrategy")
	private ContentResourceNamingStrategy namingStrategy;
	
    private RelaxedPropertyResolver propertyResolver;

    @Override
    public void setEnvironment(final Environment environment) {
        this.propertyResolver = new RelaxedPropertyResolver(environment, "content.");
    }
	
    @Bean
    public ContentService contentService() {
    	log.info("Creating content service");
    	DefaultContentService contentService = new DefaultContentService();
		contentService.setContentRepositoryPath(propertyResolver.getProperty("location"));
		contentService.setNamingStrategy(namingStrategy);
    	
        return contentService;
    }

}
