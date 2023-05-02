package com.slackku.API.REST.Educacion;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface EducacionService {
    public Educacion createEducacion(Educacion educacion);

    public void deleteEducacion(Long id);

    public List<Educacion> listEducacions();

    public Educacion findEducacion(Long id);
}
