package com.kb.config;

import org.elasticsearch.client.Client;
import org.elasticsearch.node.NodeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories("com.kb.search")
@Profile("!" + Constants.SPRING_PROFILE_PRODUCTION)
public class ElasticsearchDevConfig {
    private final Logger log = LoggerFactory.getLogger(ElasticsearchDevConfig.class);
    
	@Bean
	public ElasticsearchTemplate elasticsearchTemplate() {
		log.info("Creating dev instance of elastic search");
		Client client = nodeBuilder().local(true).node().client();
		return new ElasticsearchTemplate(client);
	}
	
	@Bean
	public NodeBuilder nodeBuilder() {
		return new NodeBuilder();
	}
	
}
