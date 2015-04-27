package com.kb.service.content;

import com.kb.domain.Content;

/**
 * ContentResourceNamingStrategy interface for resource naming strategy.
 * 
 * @author rostyslav
 *
 */
public interface ContentResourceNamingStrategy {
	/**
	 * 
	 * @param resource
	 *            ContentResource
	 * @return Identifier for ContentResource
	 */
	String createIdentifier(Content resource);

}
