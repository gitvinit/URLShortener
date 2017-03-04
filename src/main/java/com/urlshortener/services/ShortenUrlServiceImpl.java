package com.urlshortener.services;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.hash.Hashing;
import com.urlshortener.dao.ShortenUrlDao;

@Service
public class ShortenUrlServiceImpl implements ShortenUrlService{
	
	private ShortenUrlDao shortenUrlDao;
	@Autowired
	public ShortenUrlServiceImpl(ShortenUrlDao shortenUrlDao) {
		this.shortenUrlDao = shortenUrlDao;
	}
	
	private static final String baseURl = "http://localhost:8080/";
	
	public String shortenUrl(String url){
		String urlKey = generateShortenedUrl(url);
		if(shortenUrlDao.getUrl(urlKey) != null)
			return null;
		shortenUrlDao.addUrl(urlKey, url);
		
		return baseURl + urlKey;
	}
	
	public String retrieveOriginalUrl(String url){
		return shortenUrlDao.getUrl(url);
	}
	
	/**
	 * Generate shortened url key.
	 * @param url
	 * @return
	 */
	private String generateShortenedUrl(final String url){
		return Hashing.murmur3_32().
				hashString(url, StandardCharsets.UTF_8)
				.toString();
	}

}
