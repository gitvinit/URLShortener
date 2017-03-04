package com.urlshortener.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface ShortenUrlDao {
	public String getUrl(final String shortUrl);
	
	public void addUrl(final String shortUrl, final String longUrl);

}
