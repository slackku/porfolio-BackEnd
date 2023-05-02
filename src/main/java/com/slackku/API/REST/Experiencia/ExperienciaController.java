package com.slackku.API.REST.Experiencia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exp")
public class ExperienciaController {
    @Autowired
    ExperienciaServiceImpl experienciaServiceImpl;

    @PostMapping("/crear")
    public Experiencia crearEducacion(@RequestBody Experiencia experiencia) {
        return experienciaServiceImpl.createExperiencia(experiencia);
    }

    @GetMapping("/traer")
    public List<Experiencia> traerlistaEducacion() {
        return experienciaServiceImpl.listExperiencia();
    }

    @GetMapping("/traer/{id}")
    public Experiencia traerEducacion(@PathVariable Long id) {
        return experienciaServiceImpl.findExperienciaById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void eliminarEducacion(@PathVariable Long id) {
        experienciaServiceImpl.deleteExperiencia(id);
    }

    @PostMapping("/modificar/{id}")
    public Experiencia modificarEducacion(@PathVariable Long id,
            @RequestParam("nombreEmp") String nNombreEmp,
            @RequestParam("puesto") String nPuesto,
            @RequestParam("fecStart") String fecStart,
            @RequestParam("fecEnd") String fecEnd) {
        Experiencia expLocal = experienciaServiceImpl.findExperienciaById(id);
        expLocal.setNombreEmp(nNombreEmp);
        expLocal.setPuesto(nPuesto);
        expLocal.setFecStart(fecStart);
        expLocal.setFecEnd(fecEnd);
        experienciaServiceImpl.createExperiencia(expLocal);
        return expLocal;
    }
}
