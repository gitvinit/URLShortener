package com.urlshortener.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class ShortenUrlDaoImpl implements ShortenUrlDao{
	
	private Map<String, String> urlMap = new HashMap<String, String>();
	
	public String getUrl(final String shortUrl){
		return urlMap.get(shortUrl);
	}
	
	public void addUrl(final String shortUrl, final String longUrl){
		urlMap.put(shortUrl, longUrl);
	}

}
