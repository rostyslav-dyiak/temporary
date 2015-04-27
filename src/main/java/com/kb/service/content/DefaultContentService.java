package com.kb.service.content;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.AutoCloseInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kb.domain.Content;
import com.kb.domain.MimeType;
import com.kb.exception.ContentReadException;
import com.kb.exception.ContentSaveException;

/**
 * Default implementation of ContentFacade.
 * 
 * @author rostyslav
 *
 */
public class DefaultContentService implements ContentService {
	private static final Logger LOG = LoggerFactory.getLogger(DefaultContentService.class);

	private static final char EXTENSION_SEPARATOR = '.';

	private ContentResourceNamingStrategy namingStrategy;
	
	private String contentRepositoryPath;
	
	@Override
	public Content getContent(final String path) {
		LOG.info("Get content by uri: {}", path);
		Content content = null;

		LOG.info("Read file from " + path);

		try {
			File file = new File(getFullfilePath(path));
			AutoCloseInputStream  fileInputStream = new AutoCloseInputStream(new FileInputStream(file));
			String fileExtension = FilenameUtils.getExtension(file.getName());

			content = new Content();
			content.setContentStream(fileInputStream);
			content.setPath(path);
			content.setSize(Long.valueOf(file.getTotalSpace()));
			content.setMimeType(MimeType.fromExtension(EXTENSION_SEPARATOR + fileExtension));

		} catch (FileNotFoundException e) {
			String message = "Content not found";
			LOG.error("Can't read file from file system", e);
			throw new ContentReadException(message);
		}
		
		return content;
	}

	@Override
	public Content storeContent(final Content content) {
		
		String identifier = namingStrategy.createIdentifier(content);
		LOG.info("Store content with identifier: {}", identifier);
		content.setPath(identifier);

		try {
			String path = getFullfilePath(content.getPath());
			LOG.info("Storing file in: {}", path);
			File file = new File(path);
			if (file.createNewFile()) {
				IOUtils.copy(content.getContentStream(), new FileOutputStream(file));
			}

		} catch (Exception e) {
			String message = "Could not store content";
			LOG.error("Can't save file", e);
			throw new ContentSaveException(message);
		} finally {
			try {
				if (content.getContentStream() != null) {
					content.getContentStream().close();
				}
			} catch (IOException e) {
				throw new ContentSaveException("Could not close the content input stream", e);
			}
			
		}
		
		return content;
	}

	private String getFullfilePath(final String filePath) {
		return getContentRepositoryPath() + filePath;
	}

	public String getContentRepositoryPath() {
		return contentRepositoryPath;
	}

	public void setContentRepositoryPath(final String contentRepositoryPath) {
		this.contentRepositoryPath = contentRepositoryPath;
	}

	public void setNamingStrategy(final ContentResourceNamingStrategy namingStrategy) {
		this.namingStrategy = namingStrategy;
	}
	
}
