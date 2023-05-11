package com.slackku.API.REST.Experiencia;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.slackku.API.REST.Exception.ResourceNotFoundException;

@RestController
@RequestMapping("/exp")
@CrossOrigin(origins = "https://portfolio-slacku.web.app/")
public class ExperienciaController {

    @Autowired
    private ExperienciaServiceImpl experienciaServiceImpl;

    // Controles utilizados en PersonaController, Se crea/elimina/modifica solo si
    // es de una persona

    // @PostMapping("/crear")
    public Experiencia crearExperiencia(@RequestBody Experiencia experiencia) {
        return experienciaServiceImpl.createExperiencia(experiencia);
    }

    // @GetMapping("/traer")
    public List<Experiencia> traerlistaExperiencia() {
        return experienciaServiceImpl.listExperiencia();
    }

    // @GetMapping("/traer/{id}")
    public Optional<Experiencia> traerExperiencia(@PathVariable Long id) {
        return experienciaServiceImpl.findExperienciaById(id);
    }

    // @DeleteMapping("/delete/{id}")
    public void eliminarExperiencia(@PathVariable Long id) {
        experienciaServiceImpl.deleteExperiencia(id);
    }

    @PutMapping("/modificar/{id}")
    public ResponseEntity<Experiencia> modificarExperiencia(@PathVariable Long id,
            @RequestBody Experiencia updatedExperiencia) throws Exception {
        Experiencia originalExperiencia = experienciaServiceImpl.findExperienciaById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe experiencia de id: " + id));

        if (experienciaServiceImpl.hasChanges(id, updatedExperiencia)) {
            originalExperiencia.setNombreEmp(updatedExperiencia.getNombreEmp());
            originalExperiencia.setPuesto(updatedExperiencia.getPuesto());
            originalExperiencia.setFecStart(updatedExperiencia.getFecStart());
            originalExperiencia.setFecEnd(updatedExperiencia.getFecEnd());
            experienciaServiceImpl.createExperiencia(originalExperiencia);
            return ResponseEntity.ok(originalExperiencia);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(null);
        }
    }
}
