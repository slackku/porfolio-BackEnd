package com.slackku.API.REST.Proyecto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Proyecto findProyectoById(Long id) {
        return proyectoRepository.findById(id).get();
    }

}
