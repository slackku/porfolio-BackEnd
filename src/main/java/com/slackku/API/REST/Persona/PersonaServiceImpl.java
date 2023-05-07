package com.slackku.API.REST.Persona;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public PersonaDTO getPersonaDTO(String username, String password) {
        Persona persona = personaRepository.findByUsernameAndPassword(username, password);
        PersonaDTO userDTO = new PersonaDTO(persona.getId(), persona.getName(), persona.getEmail(),
                persona.getProfileImg(),
                persona.getBannerImg(), persona.getPais(), persona.getProvincia(), persona.getOcupacion(),
                persona.getSobreMi(), persona.getEducacion(), persona.getExperiencia(), persona.getProyecto());
        return userDTO;
    }

    @Override
    public ResponseEntity<PersonaDTO> login(String username, String password) {
        if (personaRepository.existsByUsernameAndPassword(username, password)) {
            PersonaDTO person = getPersonaDTO(username, password);
            return new ResponseEntity<>(person, null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
    }

}
