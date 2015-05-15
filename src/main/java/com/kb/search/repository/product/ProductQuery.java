package com.kb.search.repository.product;


public class ProductQuery {
/*
	public static final String QUERY = "{ "
			+ "\"query_string\": "
			+ "{ \"default_field\": \"title\", "
			+ "\"query\": \"?0\" } "
			+ "} ";
*/	
	public static final String QUERY = "{ "
			+ "\"query_string\": "
			+ "{ \"fields\": [\"title\", \"description\"], "
			+ "\"query\": \"?0 OR ?1\" } "
			+ "} ";
	
	public static final String FUZZY_QUERY = "{ "
			+ "\"fuzzy_like_this\" : { \"fields\" : [\"title\", \"description\"], "
			+ "\"like_text\" : \"text like this one\" } }";
	
	
}
