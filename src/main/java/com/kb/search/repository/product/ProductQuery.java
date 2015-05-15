package com.kb.search.repository.product;


public class ProductQuery {
	//?0 == null || this.field == ?0
	public static final String QUERY = "{ "
			+ "\"query_string\": "
			+ "{ \"fields\": [\"title\", \"description\", \"brand\", \"origin\", \"certifiedHalal\", \"codeGenerate\", "
			+ "\"unitDescription\", \"unitHide\", \"available\", \"code\", \"quantity\", \"basePrice\", \"categoryId\", "
			+ "\"subCategoryId\", \"subSubCategoryId\", \"unitId\", \"pictureId\", \"companyId\"], "
			+ "\"query\": \"*?0* OR *?1* OR *?2* OR *?3* OR ?4 OR ?5 OR *?6* OR ?7 OR ?8 OR"
			+ " *?9* OR ?10 OR ?11 OR ?12 OR ?13 OR ?14 OR ?15 OR ?16 OR ?17 \" } "
			+ "} ";
	
}
