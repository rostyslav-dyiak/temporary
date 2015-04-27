package com.kb.service.content;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.kb.domain.Content;

/**
 * Class for randomly resource naming.
 * 
 * @author rostyslav
 *
 */
@Component("namingStrategy")
public class RandomUuidNamingStrategy implements ContentResourceNamingStrategy {

	@Override
	public String createIdentifier(final Content resource) {
		UUID uuid = UUID.randomUUID();
		return "/" + uuid.toString() + resource.getMimeType().getExtension();
	}

}
