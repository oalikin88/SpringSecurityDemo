package ru.training.aos.springsecuritydemo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.training.aos.springsecuritydemo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByEmail(String email);

}
