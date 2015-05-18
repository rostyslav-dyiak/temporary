package com.kb.config;

import javax.annotation.Resource;

import org.elasticsearch.client.Client;
import org.elasticsearch.node.NodeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.repository.query.QueryLookupStrategy.Key;

import com.kb.converter.Converter;
import com.kb.domain.Product;
import com.kb.search.model.ProductSearch;

@Configuration
@EnableElasticsearchRepositories(value = { "com.kb.search" }, queryLookupStrategy = Key.USE_DECLARED_QUERY)
@Profile("!" + Constants.SPRING_PROFILE_PRODUCTION)
public class ElasticsearchDevConfig {
    private final Logger log = LoggerFactory.getLogger(ElasticsearchDevConfig.class);
    
	@Resource(name = "productEntitySearchConverter")
	private Converter<Product, ProductSearch> productEntitySearchConverter;
    
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
