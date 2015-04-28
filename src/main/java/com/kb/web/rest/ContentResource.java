package com.kb.web.rest;

import java.io.InputStream;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.codahale.metrics.annotation.Timed;
import com.kb.domain.Content;
import com.kb.domain.MimeType;
import com.kb.service.content.ContentService;

/**
 * REST controller for managing Category.
 */
@RestController
@RequestMapping("/api")
public class ContentResource {
    private final Logger log = LoggerFactory.getLogger(ContentResource.class);
    
    @Resource(name = "contentService")
    private ContentService contentService;
    
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/content", method = RequestMethod.POST)
	@Timed
	public Content postContent(@RequestParam("content") final MultipartFile file) throws Exception {
		log.info("Storing file");
		InputStream istream = file.getInputStream();

		MimeType mimeType = MimeType.fromType(file.getContentType());

		Content content = new Content();
		content.setContentStream(istream);
		content.setMimeType(mimeType);

		return contentService.storeContent(content);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/content", method = RequestMethod.GET)
	public void getContentByPath(final HttpServletResponse response, @RequestParam("contentPath") final String contentPath) throws Exception {
		log.info("Getting file");
		Content contentResource = contentService.getContent(contentPath);

		InputStream in = contentResource.getContentStream();
		OutputStream out = response.getOutputStream();

		response.setHeader(HttpHeaders.CONTENT_TYPE, contentResource.getMimeType().getType());

		IOUtils.copy(in, out);
		IOUtils.closeQuietly(in);
	}

}
