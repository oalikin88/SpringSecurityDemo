package ru.training.aos.springsecuritydemo.rest;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.training.aos.springsecuritydemo.model.User;
import ru.training.aos.springsecuritydemo.repositories.UserRepository;
import ru.training.aos.springsecuritydemo.security.JwtTokenProvider;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthRestController {
	
	private final AuthenticationManager authenticationManager;
	private UserRepository userRepository;
	private JwtTokenProvider jwtTokenProvider;
	
	
	
	public AuthRestController(AuthenticationManager authenticationManager, UserRepository userRepository,
			JwtTokenProvider jwtTokenProvider) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@PostMapping("/login")
	public ResponseEntity<?> authenticate(@RequestBody AuthenticationReaquestDTO requestDTO) {
		
		try {
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDTO.getEmail(), requestDTO.getPassword()));
			User user = userRepository.findByEmail(requestDTO.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User doesn't exists"));
			String token = jwtTokenProvider.createToken(requestDTO.getEmail(), user.getRole().name());
			Map<Object, Object> response = new HashMap<>();
			response.put("email", requestDTO.getEmail());
			response.put("token", token);
			
			return ResponseEntity.ok(response);
			
		} catch (AuthenticationException e) {
			return new ResponseEntity<>("Invalid email/password combination", HttpStatus.FORBIDDEN);
		}
		
	}

	@PostMapping("/logout")
	public void logout (HttpServletRequest request, HttpServletResponse response) {
		SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
		securityContextLogoutHandler.logout(request, response, null);
	}
}
