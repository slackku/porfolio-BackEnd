package com.slackku.API.REST.Educacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.DeleteMapping;
// // import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slackku.API.REST.Exception.ResourceNotFoundException;

@RestController
@RequestMapping("/educ")
@CrossOrigin(origins = "http://localhost:4200/")
public class EducacionController {
    @Autowired
    private EducacionServiceImpl educacionServiceImpl;

    // Controles utilizados en PersonaController, Se crea/elimina/modifica solo si
    // es de una persona

    // @PostMapping("/crear")
    public Educacion crearEducacion(@RequestBody Educacion educacion) {
        return educacionServiceImpl.createEducacion(educacion);
    }

    // @GetMapping("/traer")
    public List<Educacion> traerlistaEducacion() {
        return educacionServiceImpl.listEducacions();
    }

    // @GetMapping("/traer/{id}")
    public Educacion traerEducacion(@PathVariable Long id) throws Exception{
        return educacionServiceImpl.findEducacionById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe educacion de id: " + id));
    }

    // @DeleteMapping("/delete/{id}")
    public void eliminarEducacion(@PathVariable Long id) {
        educacionServiceImpl.deleteEducacion(id);
    }

    @PutMapping("/modificar/{id}")
    public ResponseEntity<Educacion> modificarEducacion(@PathVariable Long id, @RequestBody Educacion updatedEducacion)
            throws Exception {
        Educacion originalEducacion = educacionServiceImpl.findEducacionById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe experiencia de id: " + id));

        if (educacionServiceImpl.hasChanges(id, updatedEducacion)) {
            originalEducacion.setNombreInst(updatedEducacion.getNombreInst());
            originalEducacion.setCarrera(updatedEducacion.getCarrera());
            originalEducacion.setFecStart(updatedEducacion.getFecStart());
            originalEducacion.setFecEnd(updatedEducacion.getFecEnd());
            educacionServiceImpl.createEducacion(originalEducacion);
            return ResponseEntity.ok(originalEducacion);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(null);
        }
    }
}
