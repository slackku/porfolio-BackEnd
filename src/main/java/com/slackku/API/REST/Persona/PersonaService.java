package com.slackku.API.REST.Persona;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface PersonaService {

    public void createPersona(Persona persona);

    public void deletePersona(Long id);

    public List<Persona> listPersona();

    public Optional<Persona> findPersonaById(Long id);

    public PersonaDTO getPersonaDTO(String username, String password);

    public ResponseEntity<PersonaDTO> login(String username, String password);

    public Boolean hasChanges(Long id, Persona persona);
}
