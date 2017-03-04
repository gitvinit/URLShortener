package com.urlshortener.services;

import org.springframework.stereotype.Service;

@Service
public interface ShortenUrlService {
	
	/**
	 * Generate shortened url.
	 * @param url
	 * @return
	 */
	public String shortenUrl(String url);
	
	/**
	 * Retrieve original url.
	 * @param shortUrl
	 * @return
	 */
	public String retrieveOriginalUrl(String shortUrl);

}
