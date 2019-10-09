package com.eddie.ecommerce.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ehcache.Cache;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

import java.time.Duration;
import java.util.List;
import java.util.ResourceBundle;


public class CacheManager {

	private static Logger logger=LogManager.getLogger(CacheManager.class);
	private static org.ehcache.CacheManager cacheManager;
	private static ResourceBundle webConfiguration = ResourceBundle.getBundle("WebConfiguration");

	public CacheManager() {
		cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
		cacheManager.init();
		//Cache de datos estaticos
		cacheManager.createCache(Constantes.NOMBRE_CACHE_ESTATICOS,
				CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, List.class, ResourcePoolsBuilder.heap(10)).withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofDays(Integer.parseInt(webConfiguration.getString("cache.expiry.time"))))));
	}

	public static Cache<String, List> getCachePG(String nombreCache) {
		return cacheManager.getCache(nombreCache, String.class, List.class);
	}
}
