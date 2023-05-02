package com.slackku.API.REST.Experiencia;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ExperienciaService {

    public Experiencia createExperiencia(Experiencia experiencia);

    public void deleteExperiencia(Long id);

    public List<Experiencia> listExperiencia();

    public Experiencia findExperienciaById(Long id);
}
