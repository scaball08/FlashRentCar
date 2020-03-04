package com.test.auth;


import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.test.models.entity.UserLogin;
import com.test.models.services.IUserService;
import com.test.models.services.UserServiceImpl;



@Component
public class InfoAdicionalToken implements TokenEnhancer {
	
	@Autowired
	private IUserService usuarioService;
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		logger.info("Agregando informacion adicional");
		
		UserLogin usuario = usuarioService.findByUsername(authentication.getName());
		

		Map<String,Object> info = new HashMap<>();
		
		info.put("info_adicional", "hola que tal".concat(authentication.getName()));
		
		info.put("nombre", usuario.getNombre() );
		info.put("apellido", usuario.getApellido() );
		info.put("email", usuario.getEmail());

		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		return accessToken;
	}

}
