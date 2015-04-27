package com.kb.config;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kb.service.content.ContentResourceNamingStrategy;
import com.kb.service.content.ContentService;
import com.kb.service.content.DefaultContentService;

@Configuration
public class ContentConfig {
    private final Logger log = LoggerFactory.getLogger(ContentConfig.class);
    
    @Value("${content.location}")
	private String location;
	
	@Resource(name = "namingStrategy")
	private ContentResourceNamingStrategy namingStrategy;
	
    @Bean
    public ContentService contentService() {
    	log.info("Creating content service");
    	DefaultContentService contentService = new DefaultContentService();
		contentService.setContentRepositoryPath(location);
		contentService.setNamingStrategy(namingStrategy);
    	
        return contentService;
    }

}
