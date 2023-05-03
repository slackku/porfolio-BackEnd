package com.slackku.API.REST.Proyecto;

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
// @RequestMapping("/proyecto")
public class ProyectoController {
    @Autowired
    private ProyectoServiceImpl proyectoServiceImpl;

    // Controles utilizados en PersonaController, Se crea/elimina/modifica solo si
    // es de una persona

    // @PostMapping("/crear")
    public Proyecto crearProyecto(@RequestBody Proyecto proyecto) {
        return proyectoServiceImpl.createProyecto(proyecto);
    }

    // @GetMapping("/traer")
    public List<Proyecto> traerlistaProyecto() {
        return proyectoServiceImpl.listProyecto();
    }

    // @GetMapping("/traer/{id}")
    public Proyecto traerProyecto(@PathVariable Long id) {
        return proyectoServiceImpl.findProyectoById(id);
    }

    // @DeleteMapping("/delete/{id}")
    public void eliminarProyecto(@PathVariable Long id) {
        proyectoServiceImpl.deleteProyectoById(id);
    }

    // @PostMapping("/modificar/{id}")
    public Proyecto modificarProyecto(@PathVariable Long id,
            @RequestParam("nombreProy") String nNombreProy,
            @RequestParam("descripcion") String nPuesto,
            @RequestParam("fecStart") String nfecStart,
            @RequestParam("proyImg") String NproyImg) {
        Proyecto proyLocal = proyectoServiceImpl.findProyectoById(id);
        proyLocal.setNombreProyecto(nNombreProy);
        proyLocal.setDescripcion(nPuesto);
        proyLocal.setFecStart(nfecStart);
        proyLocal.setProyectImg(NproyImg);
        proyectoServiceImpl.createProyecto(proyLocal);
        return proyLocal;
    }
}
