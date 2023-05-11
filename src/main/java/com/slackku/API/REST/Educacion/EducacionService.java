package com.slackku.API.REST.Educacion;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public interface EducacionService {
    public Educacion createEducacion(Educacion educacion);

    public void deleteEducacion(Long id);

    public List<Educacion> listEducacions();

    public Optional<Educacion> findEducacionById(Long id);

    public Boolean hasChanges(Long id, Educacion educacion) throws Exception;
}
