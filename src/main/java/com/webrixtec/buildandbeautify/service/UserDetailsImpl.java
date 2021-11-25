package com.webrixtec.buildandbeautify.service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.webrixtec.buildandbeautify.model.UserModel;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String email;
	private String username;
	private String role;
	@JsonIgnore
	private String password;
	private Collection<? extends GrantedAuthority> authorities;





	public UserDetailsImpl(Long id,String username, String password, String email
//			,Collection<? extends GrantedAuthority> authorities
			) {
		this.id = id;
		this.username = email;
		this.password = password;
		this.email=email;
//		this.authorities = authorities;
	}

	public static UserDetails build(UserModel user) {
//		List<GrantedAuthority> authorities = user.getRoles().stream()
//				.map(role -> new SimpleGrantedAuthority(role.getRolename()))
//				.collect(Collectors.toList());
		return new UserDetailsImpl(user.getId(), 
				user.getName(), 
				user.getPassword(),
				user.getEmail()
//				authorities
				);
	}
//	public static UserDetails build(Long id,String email, String password, String username, String role) {
//		return new UserDetailsImpl(id,email,password,username,role);
//	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	

	public String getRole() {
		return role;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}

	

}
