package com.slackku.API.REST.Persona;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface PersonaService {

    public void createPersona(Persona persona);

    public void deletePersona(Long id);

    public List<Persona> listPersona();

    public Persona findPersona(Long id);

    public PersonaDTO login(String username, String password);

}
