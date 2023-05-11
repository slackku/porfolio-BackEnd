package com.slackku.API.REST.Persona;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.slackku.API.REST.Exception.ResourceNotFoundException;

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
    public Optional<Persona> findPersonaById(Long id) {
        return personaRepository.findById(id);
    }

    @Override
    public PersonaDTO getPersonaDTO(String username, String password) {
        Persona persona = personaRepository.findByUsernameAndPassword(username, password);
        PersonaDTO userDTO = new PersonaDTO(persona.getId(), persona.getNombre(), persona.getEmail(),
                persona.getProfileImg(), persona.getPais(), persona.getProvincia(), persona.getOcupacion(),
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

    @Override
    public Boolean hasChanges(Long id, Persona updatedPersona) {
        Persona originalPersona = personaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe proyecto de id: " + id));
        return (!originalPersona.getNombre().equals(updatedPersona.getNombre())) ||
                (!originalPersona.getProvincia().equals(updatedPersona.getProvincia())) ||
                (!originalPersona.getPais().equals(updatedPersona.getPais())) ||
                (!originalPersona.getOcupacion().equals(updatedPersona.getOcupacion())) ||
                (!originalPersona.getSobreMi().equals(updatedPersona.getSobreMi())) ||
                (!originalPersona.getProfileImg().equals(updatedPersona.getProfileImg()));
    }

}
