package ru.training.aos.springsecuritydemo.rest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.training.aos.springsecuritydemo.developer.Developer;

@RestController
@RequestMapping("/api/v1/developers")
public class DeveloperRestControllerV1 {
	
	private List<Developer> DEVELOPERS = Stream.of(
			new Developer(1L, "Tom", "Cruise"),
			new Developer(2L, "Al", "Pacino"),
			new Developer(3L, "Robert", "De Niro")).collect(Collectors.toList());

	@GetMapping
	public List<Developer> getAll() {
		return DEVELOPERS;
	}
	
	@GetMapping("/{id}")
	public Developer getById(@PathVariable Long id) {
		return DEVELOPERS.stream().filter(e -> e.getId().equals(id)).findFirst().orElse(null);
	}
}
