package com.urlshortener.controllers;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.urlshortener.requests.ShortenUrlRequest;
import com.urlshortener.responses.ShortenUrlResponse;
import com.urlshortener.services.ShortenUrlService;

@RestController
public class ShortenUrlController {
	
	private ShortenUrlService shortenUrlService;
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public ShortenUrlController(ShortenUrlService shortenUrlService) {
		this.shortenUrlService = shortenUrlService;
	}	
	
	/**
	 * Endpoint to generate shortened url.
	 * @param shortenUrlRequest
	 * @return
	 */
	@RequestMapping(value = "/urlshortener", method = RequestMethod.POST)
	public ShortenUrlResponse shortenUrl(@Valid @RequestBody final ShortenUrlRequest shortenUrlRequest){
		log.info("Request recieved to shorten URL");
		final String shortenedUrl = shortenUrlService.shortenUrl(shortenUrlRequest.getUrl());
		log.info("Generated short URL" + shortenedUrl);
		return new ShortenUrlResponse(shortenUrlRequest.getUrl(), shortenedUrl);
	}
	
	/**
	 * Retrieve original url and redirect user to the original url.
	 * @param urlKey
	 * @param response
	 */
	@RequestMapping(value= "/{key}", method = RequestMethod.GET)
	public void redirect(@PathVariable("key") final String urlKey, final HttpServletResponse response) {
		log.info("Request recieved to translate short url "+  urlKey);
		final String originalUrl = shortenUrlService.retrieveOriginalUrl(urlKey);
		if(originalUrl !=null)	{
			log.info("Redirecting user to " + originalUrl);
			response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
			response.setHeader("Location", originalUrl);
		}
		
	}


}
