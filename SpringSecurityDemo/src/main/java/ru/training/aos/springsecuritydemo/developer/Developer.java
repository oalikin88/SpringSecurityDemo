package ru.training.aos.springsecuritydemo.developer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Developer {

	private Long id;
	private String firstName;
	private String lastName;
	
}
