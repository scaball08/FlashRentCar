package com.test.auth;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import com.test.models.services.UserServiceImpl;


@Configuration
@EnableResourceServer
public class ResourceServerConfig extends  ResourceServerConfigurerAdapter{
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public void configure(HttpSecurity http) throws Exception {
		// Se dan los permisos para los recursos de nuestro servidor  ya sean arhivos o endpoints de nuestra api
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/cars","/api/autos").permitAll()
		.anyRequest().authenticated()
		.and().cors().configurationSource(corsConfigurationSource()); 
		logger.info("Adminitrar endpoints mediante su rol");
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		
		CorsConfiguration corsConfi = new CorsConfiguration();
		
		corsConfi.setAllowedOrigins(Arrays.asList("http://localhost:4200")); 
		corsConfi.setAllowedMethods(Arrays.asList("GET","POST","PUT","OPTIONS","DELETE"));
		corsConfi.setAllowCredentials(true); 
		corsConfi.setAllowedHeaders(Arrays.asList("Content-Type","Authorization")); 
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		
		source.registerCorsConfiguration("/**", corsConfi);
		

	  return source;
	}
	

	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter(){

		 FilterRegistrationBean<CorsFilter> bean  = new  FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
		
	     bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
	     
	     return bean;
	}

}
