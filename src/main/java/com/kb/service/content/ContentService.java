package com.kb.service.content;

import com.kb.domain.Content;

/**
 * Facade for content.
 * 
 * @author rostyslav
 *
 */
public interface ContentService {
	
	/**
	 * Method return content by uri.
	 * 
	 * @param uri
	 *            to content storage.
	 * @return ContentResource for given uri.
	 */
	Content getContent(String uri);

	/**
	 * Method saves ContentResource to storage.
	 * 
	 * @param resource
	 *            ContentResource to save
	 * @return saved ContentResource
	 */
	Content storeContent(Content resource);
}
