package com.slackku.API.REST.Educacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @RequestMapping("/educ")
public class EducacionController {
    @Autowired
    private EducacionServiceImpl educacionServiceImpl;

    // Controles utilizados en PersonaController, Se crea/elimina/modifica solo si es de una persona

    // @PostMapping("/crear")
    public Educacion crearEducacion(@RequestBody Educacion educacion) {
        return educacionServiceImpl.createEducacion(educacion);
    }

    // @GetMapping("/traer")
    public List<Educacion> traerlistaEducacion() {
        return educacionServiceImpl.listEducacions();
    }

    // @GetMapping("/traer/{id}")
    public Educacion traerEducacion(@PathVariable Long id) {
        return educacionServiceImpl.findEducacion(id);
    }

    // @DeleteMapping("/delete/{id}")
    public void eliminarEducacion(@PathVariable Long id) {
        educacionServiceImpl.deleteEducacion(id);
    }

    // @PostMapping("/modificar/{id}")
    public Educacion modificarEducacion(@PathVariable Long id,
            @RequestParam("nombreInst") String nNombreInst,
            @RequestParam("carrera") String carrera,
            @RequestParam("fecStart") String fecStart, @RequestParam("fecEnd") String fecEnd) {
        Educacion educLocal = educacionServiceImpl.findEducacion(id);
        educLocal.setNombreInst(nNombreInst);
        educLocal.setCarrera(carrera);
        educLocal.setFecStart(fecStart);
        educLocal.setFecEnd(fecEnd);
        educacionServiceImpl.createEducacion(educLocal);
        return educLocal;
    }
}
