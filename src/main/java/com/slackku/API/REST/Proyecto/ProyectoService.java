package com.slackku.API.REST.Proyecto;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ProyectoService {
    public Proyecto createProyecto(Proyecto proyecto);

    public void deleteProyectoById(Long id);

    public List<Proyecto> listProyecto();

    public Proyecto findProyectoById(Long id);
}
