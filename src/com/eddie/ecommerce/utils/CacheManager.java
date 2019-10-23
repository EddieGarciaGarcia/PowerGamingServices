package com.eddie.ecommerce.utils;

import com.eddie.ecommerce.model.Usuario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ehcache.Cache;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

import java.time.Duration;
import java.util.List;


public class CacheManager {

	private static Logger logger=LogManager.getLogger(CacheManager.class);
	private static org.ehcache.CacheManager cacheManager;
    private static Integer expiryToken = 30;
    private static Integer expiryTokenEstatics = 1;

	public CacheManager() {
		cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
		cacheManager.init();
		//Cache de datos estaticos
		cacheManager.createCache(Constantes.NOMBRE_CACHE_ESTATICOS,
				CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, List.class,
						ResourcePoolsBuilder.heap(100)).withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofDays(expiryTokenEstatics))));
		cacheManager.createCache(Constantes.NOMBRE_CACHE_lOGIN,
				CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, Usuario.class,
						ResourcePoolsBuilder.heap(100)).withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofMinutes(expiryToken))));
	}

	public static Cache<String, List> getCachePG(String nombreCache) {
		return cacheManager.getCache(nombreCache, String.class, List.class);
	}
	public static Cache<String, Usuario> getCacheLogin(String nombreCache) {
		return cacheManager.getCache(nombreCache, String.class, Usuario.class);
	}
}
