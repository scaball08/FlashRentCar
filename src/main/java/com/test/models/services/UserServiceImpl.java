package com.test.models.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.test.models.entity.UserLogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.models.dao.UserDao;


@Service
public class UserServiceImpl implements UserDetailsService , IUserService{

	@Autowired
	private UserDao userDao;
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	@Transactional(readOnly = true)
	public UserLogin findByUsername(String username) {
		// obtener usuario username
		return userDao.findByUsername(username);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Login de usuario
		
		UserLogin user = userDao.findByUsername(username);
		
		if (user==null) {
			logger.error("Error en el login: No existe el usuario en el sistema!");
			
			throw new UsernameNotFoundException("Error en el login: No existe el usuario '"+ username +"'en el sistema!");
		}
		
		List<GrantedAuthority> authorities = user.getRoles()
				.stream()
				.map(role-> new SimpleGrantedAuthority(role.getNombre()) )
				.peek(authority-> logger.info("Role: " + authority.getAuthority()))
				.collect(Collectors.toList());
		
		return new User(user.getUsername(), user.getPassword(), user.getEnabled(), true, true, true, authorities);
	}

	@Override
	@Transactional
	public UserLogin save(UserLogin userLogin) {
		
		return userDao.save(userLogin);
	}

}
