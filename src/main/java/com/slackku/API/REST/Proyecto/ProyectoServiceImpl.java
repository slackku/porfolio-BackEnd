package com.slackku.API.REST.Proyecto;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slackku.API.REST.Exception.ResourceNotFoundException;

@Service
public class ProyectoServiceImpl implements ProyectoService {

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Override
    public Proyecto createProyecto(Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    @Override
    public void deleteProyectoById(Long id) {
        proyectoRepository.deleteById(id);
    }

    @Override
    public List<Proyecto> listProyecto() {
        return proyectoRepository.findAll();
    }

    @Override
    public Optional<Proyecto> findProyectoById(Long id) {
        return proyectoRepository.findById(id);
    }

    @Override
    public Boolean hasChanges(Long id, Proyecto proyecto) throws Exception {
        Proyecto originalProyect = proyectoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe proyecto de id: " + id));
        return (!originalProyect.getNombreProyecto().equals(proyecto.getNombreProyecto())) ||
                (!originalProyect.getDescripcion().equals(proyecto.getDescripcion())) ||
                (!originalProyect.getFecStart().equals(proyecto.getFecStart())) ||
                (!originalProyect.getProyectImg().equals(proyecto.getProyectImg()));
    }

    @Override
    public Boolean hasNullInputs(Proyecto proyecto) {
        return (proyecto.getNombreProyecto().isBlank() || proyecto.getNombreProyecto().isEmpty()) &&
                (proyecto.getDescripcion().isBlank() || proyecto.getDescripcion().isEmpty()) &&
                (proyecto.getFecStart().isBlank() || proyecto.getFecStart().isEmpty());
    }
}
