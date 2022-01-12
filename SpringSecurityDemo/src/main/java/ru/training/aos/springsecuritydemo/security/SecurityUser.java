package ru.training.aos.springsecuritydemo.security;

import java.util.Collection;
import java.util.List;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import ru.training.aos.springsecuritydemo.model.Status;
import ru.training.aos.springsecuritydemo.model.User;

public class SecurityUser implements UserDetails {
	private final String username;
	private final String password;
	private final boolean isActive;
	private final List <SimpleGrantedAuthority> authorities;
	
	
	public SecurityUser(String username, String password, boolean isActive, List<SimpleGrantedAuthority> authorities) {
		this.username = username;
		this.password = password;
		this.isActive = isActive;
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return isActive;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return isActive;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return isActive;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return isActive;
	}
	
	public static UserDetails fromUser(User user) {
		return new org.springframework.security.core.userdetails.User(user.getFirstName(), 
				user.getPassword(), 
				user.getStatus().equals(Status.ACTIVE),
				user.getStatus().equals(Status.ACTIVE),
				user.getStatus().equals(Status.ACTIVE),
				user.getStatus().equals(Status.ACTIVE),
				user.getRole().getAuthorities());
	}

}
