package com.slackku.API.REST.Proyecto;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public interface ProyectoService {
    public Proyecto createProyecto(Proyecto proyecto);

    public void deleteProyectoById(Long id);

    public List<Proyecto> listProyecto();

    public Optional<Proyecto> findProyectoById(Long id);

    public Boolean hasChanges(Long id, Proyecto proyecto) throws Exception;

    public Boolean hasNullInputs(Proyecto proyecto);
}
