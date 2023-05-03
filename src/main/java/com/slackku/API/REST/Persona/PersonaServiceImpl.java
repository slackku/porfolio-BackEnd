package com.slackku.API.REST.Persona;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    PersonaRepository personaRepository;

    @Override
    public void createPersona(Persona persona) {
        personaRepository.save(persona);
    }

    @Override
    public void deletePersona(Long id) {
        personaRepository.deleteById(id);
    }

    @Override
    public List<Persona> listPersona() {
        return personaRepository.findAll();
    }

    @Override
    public Persona findPersona(Long id) {
        return personaRepository.findById(id).get();
    }

    @Override
    public PersonaDTO login(String username, String password) {
        Persona persona = personaRepository.findByUsernameAndPassword(username, password);
        PersonaDTO userDTO = new PersonaDTO(persona.getId(), persona.getName(), persona.getEmail(),
                persona.getProfileImg(),
                persona.getBannerImg(), persona.getPais(), persona.getProvincia(), persona.getOcupacion(),
                persona.getSobreMi(), persona.getEducacion(), persona.getExperiencia(), persona.getProyecto());
        return userDTO;
    }

}
