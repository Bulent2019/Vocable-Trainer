package com.example.Project.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Project.model.MyUser;
import com.example.Project.model.MYUserRepository;


	@Service
	public class UserDetailServiceImpl implements UserDetailsService  {
		private final MYUserRepository repository;

		@Autowired
		public UserDetailServiceImpl(MYUserRepository userRepository) {
			this.repository = userRepository;
		}

	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	    {   
	    	MyUser curruser = repository.findByUsername(username);
	        UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPasswordHash(), 
	        		AuthorityUtils.createAuthorityList(curruser.getRole()));
	        return user;
	    }   
}
