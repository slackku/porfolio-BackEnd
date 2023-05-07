package com.slackku.API.REST.Persona;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
    public Persona findByUsernameAndPassword(String username, String password);
    public Boolean existsByUsernameAndPassword(String username, String password);
}
