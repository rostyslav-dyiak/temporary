package com.kb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.InputStream;

/**
 * Model for storing content.
 *
 * @author rostyslav
 *
 */
public class Content {

	private String path;

	private Long size;

	@JsonIgnore
	private InputStream contentStream;

	private MimeType mimeType;

	public String getPath() {
		return path;
	}

	public void setPath(final String path) {
		this.path = path;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(final Long size) {
		this.size = size;
	}

	public InputStream getContentStream() {
		return contentStream;
	}

	public void setContentStream(final InputStream contentStream) {
		this.contentStream = contentStream;
	}

	public MimeType getMimeType() {
		return mimeType;
	}

	public void setMimeType(final MimeType mimeType) {
		this.mimeType = mimeType;
	}

	@Override
	public String toString() {
		return "Content [path=" + path + ", size=" + size + ", mimeType=" + mimeType + "]";
	}
}
