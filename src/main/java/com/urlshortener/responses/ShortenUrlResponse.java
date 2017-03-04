package com.urlshortener.responses;

public class ShortenUrlResponse {
	
	private final String originalUrl;
	private final String shortUrl;
	
	public ShortenUrlResponse(){
		this.originalUrl = "";
		this.shortUrl = "";
	}
	
	public ShortenUrlResponse(final String originalUrl, final String shortUrl) {
		this.originalUrl = originalUrl;
		this.shortUrl = shortUrl;
	}

	public String getOriginalUrl() {
		return originalUrl;
	}

	public String getShortUrl() {
		return shortUrl;
	}

}
