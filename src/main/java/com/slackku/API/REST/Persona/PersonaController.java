package com.slackku.API.REST.Persona;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.slackku.API.REST.Educacion.Educacion;
import com.slackku.API.REST.Educacion.EducacionController;
import com.slackku.API.REST.Exception.ResourceNotFoundException;
import com.slackku.API.REST.Experiencia.Experiencia;
import com.slackku.API.REST.Experiencia.ExperienciaController;
import com.slackku.API.REST.Proyecto.Proyecto;
import com.slackku.API.REST.Proyecto.ProyectoController;

@RestController
@CrossOrigin(origins = "https://portfolio-slacku.web.app")
public class PersonaController {

    @Autowired
    private PersonaService personaServiceImpl;
    @Autowired
    private EducacionController educacionController;
    @Autowired
    private ExperienciaController experienciaController;
    @Autowired
    private ProyectoController proyectoController;

    @GetMapping("/persona/traer")
    public List<Persona> listaPersonas() {
        return personaServiceImpl.listPersona();
    }

    @GetMapping("/persona/traer/{id}")
    public PersonaDTO traerPersona(@PathVariable Long id) {
        Persona persona = personaServiceImpl.findPersonaById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe la persona de id: " + id));
        PersonaDTO personaDTO = new PersonaDTO(persona.getId(),
                persona.getNombre(),
                persona.getEmail(),
                persona.getProfileImg(),
                persona.getPais(),
                persona.getProvincia(),
                persona.getOcupacion(),
                persona.getSobreMi(),
                persona.getEducacion(),
                persona.getExperiencia(),
                persona.getProyecto());
        return personaDTO;
    }

    @PostMapping("/persona/crear")
    public void createPersona(@RequestBody Persona persona) {
        personaServiceImpl.createPersona(persona);
    }

    // @DeleteMapping("/persona/delete/{id}")
    public void borrarPersona(@PathVariable Long id) {
        personaServiceImpl.deletePersona(id);
    }

    @PutMapping("/persona/modificar/{id}")
    public ResponseEntity<Persona> modificarPersona(@PathVariable Long id, @RequestBody Persona updatedPersona) {
        Persona originalPersona = personaServiceImpl.findPersonaById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe proyecto de id: " + id));

        if (personaServiceImpl.hasChanges(id, updatedPersona)) {
            originalPersona.setNombre(updatedPersona.getNombre());
            originalPersona.setOcupacion(updatedPersona.getOcupacion());
            originalPersona.setPais(updatedPersona.getPais());
            originalPersona.setProvincia(updatedPersona.getProvincia());
            originalPersona.setProfileImg(updatedPersona.getProfileImg());
            originalPersona.setSobreMi(updatedPersona.getSobreMi());
            personaServiceImpl.createPersona(originalPersona);
            return ResponseEntity.ok(originalPersona);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(null);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<PersonaDTO> login(@RequestBody Persona persona) {
        return personaServiceImpl.login(persona.getUsername(), persona.getPassword());
    }

    // Post Mappings EDU-EXP-PROY

    @PostMapping("/persona/add-educ/{id}")
    public ResponseEntity<Educacion> chargeEducations(@PathVariable Long id,
            @RequestBody Educacion educacion) {
        Persona personaLocal = personaServiceImpl.findPersonaById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe la persona de id: " + id));
        educacion.setPers(personaLocal);
        Educacion educ = educacionController.crearEducacion(educacion);
        personaLocal.getEducacion().add(educ);
        return ResponseEntity.status(HttpStatus.CREATED).body(educ);

    }

    @PostMapping("/persona/add-exp/{id}")
    public ResponseEntity<Experiencia> chargeExperiencias(@PathVariable Long id,
            @RequestBody Experiencia experiencia) {
        Persona personaLocal = personaServiceImpl.findPersonaById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe la persona de id: " + id));
        experiencia.setPers(personaLocal);
        Experiencia exp = experienciaController.crearExperiencia(experiencia);
        personaLocal.getExperiencia().add(exp);
        return ResponseEntity.status(HttpStatus.CREATED).body(exp);
    }

    @PostMapping("/persona/add-proy/{id}")
    public ResponseEntity<Proyecto> chargeProyectos(@PathVariable Long id,
            @RequestBody Proyecto proyecto) {
        Persona personaLocal = personaServiceImpl.findPersonaById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe la persona de id: " + id));
        proyecto.setPers(personaLocal);
        Proyecto proy = proyectoController.crearProyecto(proyecto);
        personaLocal.getProyecto().add(proy);
        return ResponseEntity.status(HttpStatus.CREATED).body(proy);
    }

    // Delete Mappings EDU-EXP-PROY

    @DeleteMapping("/persona/remove-educ/{idEduc}")
    public ResponseEntity<Educacion> removeEducation(@PathVariable Long idEduc) {
        educacionController.eliminarEducacion(idEduc);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping("/persona/remove-exp/{idExp}")
    public ResponseEntity<Experiencia> removeExperiencia(@PathVariable Long idExp) {
        experienciaController.eliminarExperiencia(idExp);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping("/persona/remove-proy/{idProy}")
    public ResponseEntity<Proyecto> removeProyecto(@PathVariable Long idProy) {
        proyectoController.eliminarProyecto(idProy);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
