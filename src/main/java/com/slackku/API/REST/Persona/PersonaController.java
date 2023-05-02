package com.slackku.API.REST.Persona;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.slackku.API.REST.Educacion.Educacion;
import com.slackku.API.REST.Educacion.EducacionController;
import com.slackku.API.REST.Experiencia.Experiencia;
import com.slackku.API.REST.Experiencia.ExperienciaController;

@RestController
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    private PersonaService personaServiceImpl;
    @Autowired
    private EducacionController educacionController;
    @Autowired
    private ExperienciaController experienciaController;

    @GetMapping("/traer")
    public List<Persona> listaPersonas() {
        return personaServiceImpl.listPersona();
    }

    @GetMapping("/traer/{id}")
    public Persona traerPersona(@PathVariable Long id) {
        return personaServiceImpl.findPersona(id);
    }

    @PostMapping("/crear")
    public void createPersona(@RequestBody Persona persona) {
        personaServiceImpl.createPersona(persona);
    }

    @DeleteMapping("/delete/{id}")
    public void borrarPersona(@PathVariable Long id) {
        personaServiceImpl.deletePersona(id);
    }

    @PostMapping("/modificar/{id}")
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

    @PostMapping("/login")
    public PersonaDTO login(@RequestBody Persona persona) {
        return personaServiceImpl.login(persona.getUsername(), persona.getPassword());
    }

    @PostMapping("/add-educ/{id}")
    public Set<Educacion> chargeEducations(@PathVariable Long id, @RequestBody Educacion educacion) {
        educacion.setPers(personaServiceImpl.findPersona(id));
        Educacion educ = educacionController.crearEducacion(educacion);
        Persona personaLocal = personaServiceImpl.findPersona(id);
        personaLocal.getEducacion().add(educ);
        return personaLocal.getEducacion();
    }

    @DeleteMapping("/remove-educ/{id}")
    public void removeEducation(@PathVariable Long id, @RequestParam("idEduc") Long idEduc) {
        Persona personaLocal = personaServiceImpl.findPersona(id);
        Set<Educacion> educacions = personaLocal.getEducacion();
        educacionController.eliminarEducacion(idEduc);
        personaLocal.setEducacion(educacions);
        personaServiceImpl.createPersona(personaLocal);
    }

    @PostMapping("/add-exp/{id}")
    public Set<Experiencia> chargExperiencias(@PathVariable Long id, @RequestBody Experiencia experiencia) {
        experiencia.setPers(personaServiceImpl.findPersona(id));
        Experiencia exp = experienciaController.crearExperiencia(experiencia);
        Persona personaLocal = personaServiceImpl.findPersona(id);
        personaLocal.getExperiencia().add(exp);
        return personaLocal.getExperiencia();
    }

    @DeleteMapping("/remove-exp/{id}")
    public void removeExperiencia(@PathVariable Long id, @RequestParam("idExp") Long idExp) {
        Persona personaLocal = personaServiceImpl.findPersona(id);
        Set<Experiencia> experiencias = personaLocal.getExperiencia();
        experienciaController.eliminarExperiencia(idExp);
        personaLocal.setExperiencia(experiencias);
        personaServiceImpl.createPersona(personaLocal);
    }
}
