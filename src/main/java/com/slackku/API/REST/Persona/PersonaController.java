package com.slackku.API.REST.Persona;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.slackku.API.REST.Educacion.Educacion;
import com.slackku.API.REST.Educacion.EducacionController;
import com.slackku.API.REST.Experiencia.Experiencia;
import com.slackku.API.REST.Experiencia.ExperienciaController;
import com.slackku.API.REST.Proyecto.Proyecto;
import com.slackku.API.REST.Proyecto.ProyectoController;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
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
        Persona persona = personaServiceImpl.findPersona(id);
        PersonaDTO personaDTO = new PersonaDTO(persona.getId(),
                persona.getName(),
                persona.getEmail(),
                persona.getProfileImg(),
                persona.getBannerImg(),
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

    @DeleteMapping("/persona/delete/{id}")
    public void borrarPersona(@PathVariable Long id) {
        personaServiceImpl.deletePersona(id);
    }

    @PostMapping("/persona/modificar/{id}")
    public Persona modificarPersona(@PathVariable Long id,
            @RequestParam("name") String Nname,
            @RequestParam("ocupacion") String Nocup,
            @RequestParam("pais") String Npais,
            @RequestParam("provincia") String Nprovincia,
            @RequestParam("sobreMi") String NsobreMi) {
        Persona personaLocal = personaServiceImpl.findPersona(id);
        personaLocal.setName(Nname);
        personaLocal.setOcupacion(Nocup);
        personaLocal.setPais(Npais);
        personaLocal.setProvincia(Nprovincia);
        personaLocal.setSobreMi(NsobreMi);
        personaServiceImpl.createPersona(personaLocal);
        return personaLocal;
    }

    @PostMapping("/persona/add-educ/{id}{isSessionOn}")
    public List<Educacion> chargeEducations(@PathVariable Long id, @PathVariable Integer isSessionOn,@RequestBody Educacion educacion) {
        
        educacion.setPers(personaServiceImpl.findPersona(id));
        Educacion educ = educacionController.crearEducacion(educacion);
        Persona personaLocal = personaServiceImpl.findPersona(id);
        personaLocal.getEducacion().add(educ);
        return personaLocal.getEducacion();
    }

    @DeleteMapping("/persona/remove-educ/{id}")
    public void removeEducation(@PathVariable Long id, @RequestParam("idEduc") Long idEduc) {
        Persona personaLocal = personaServiceImpl.findPersona(id);
        List<Educacion> educacions = personaLocal.getEducacion();
        educacionController.eliminarEducacion(idEduc);
        personaLocal.setEducacion(educacions);
        personaServiceImpl.createPersona(personaLocal);
    }

    @PostMapping("/persona/add-exp/{id}")
    public List<Experiencia> chargeExperiencias(@PathVariable Long id, @RequestBody Experiencia experiencia) {
        experiencia.setPers(personaServiceImpl.findPersona(id));
        Experiencia exp = experienciaController.crearExperiencia(experiencia);
        Persona personaLocal = personaServiceImpl.findPersona(id);
        personaLocal.getExperiencia().add(exp);
        return personaLocal.getExperiencia();
    }

    @DeleteMapping("/persona/remove-exp/{id}")
    public void removeExperiencia(@PathVariable Long id, @RequestParam("idExp") Long idExp) {
        Persona personaLocal = personaServiceImpl.findPersona(id);
        List<Experiencia> experiencias = personaLocal.getExperiencia();
        experienciaController.eliminarExperiencia(idExp);
        personaLocal.setExperiencia(experiencias);
        personaServiceImpl.createPersona(personaLocal);
    }

    @PostMapping("/persona/add-proy/{id}")
    public List<Proyecto> chargeProyectos(@PathVariable Long id, @RequestBody Proyecto proyecto) {
        proyecto.setPers(personaServiceImpl.findPersona(id));
        Proyecto proy = proyectoController.crearProyecto(proyecto);
        Persona personaLocal = personaServiceImpl.findPersona(id);
        personaLocal.getProyecto().add(proy);
        return personaLocal.getProyecto();
    }

    @DeleteMapping("/persona/remove-proy/{id}")
    public void removeProyecto(@PathVariable Long id, @RequestParam("idProy") Long idProy) {
        Persona personaLocal = personaServiceImpl.findPersona(id);
        List<Proyecto> proyectos = personaLocal.getProyecto();
        experienciaController.eliminarExperiencia(idProy);
        personaLocal.setProyecto(proyectos);
        personaServiceImpl.createPersona(personaLocal);
    }

    @PostMapping("/login")
    public ResponseEntity<PersonaDTO> login(@RequestBody Persona persona) {
        return personaServiceImpl.login(persona.getUsername(), persona.getPassword());
    }
}
