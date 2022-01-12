package ru.training.aos.springsecuritydemo.rest;

import lombok.Data;

@Data
public class AuthenticationReaquestDTO {

	private String email;
	private String password;
}
