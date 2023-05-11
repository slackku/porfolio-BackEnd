package com.slackku.API.REST.Proyecto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slackku.API.REST.Exception.ResourceNotFoundException;

@RestController
@RequestMapping("/proy")
@CrossOrigin(origins = "https://portfolio-slacku.web.app")
public class ProyectoController {
    @Autowired
    private ProyectoServiceImpl proyectoServiceImpl;

    // @PostMapping("/crear")
    public Proyecto crearProyecto(@RequestBody Proyecto proyecto) {
        return proyectoServiceImpl.createProyecto(proyecto);
    }

    // @GetMapping("/traer")
    public List<Proyecto> traerlistaProyecto() {
        return proyectoServiceImpl.listProyecto();
    }

    @GetMapping("/traer/{id}")
    public Proyecto traerProyecto(@PathVariable Long id) throws Exception {
        return proyectoServiceImpl.findProyectoById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe proyecto de id: " + id));
    }

    // @DeleteMapping("/delete/{id}")
    public void eliminarProyecto(@PathVariable Long id) {
        proyectoServiceImpl.deleteProyectoById(id);
    }

    @PutMapping("/modificar/{id}")
    public ResponseEntity<Proyecto> modificarProyecto(@PathVariable Long id,
            @RequestBody Proyecto updatedProyecto) throws Exception {
        Proyecto originalProyecto = proyectoServiceImpl.findProyectoById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe proyecto de id: " + id));

        if (proyectoServiceImpl.hasChanges(id, updatedProyecto)) {
            originalProyecto.setNombreProyecto(updatedProyecto.getNombreProyecto());
            originalProyecto.setDescripcion(updatedProyecto.getDescripcion());
            originalProyecto.setFecStart(updatedProyecto.getFecStart());
            originalProyecto.setProyectImg(updatedProyecto.getProyectImg());
            proyectoServiceImpl.createProyecto(originalProyecto);
            return ResponseEntity.ok(originalProyecto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(null);
        }
    }

}
