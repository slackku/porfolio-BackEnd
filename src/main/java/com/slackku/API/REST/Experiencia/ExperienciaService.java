package com.slackku.API.REST.Experiencia;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public interface ExperienciaService {

    public Experiencia createExperiencia(Experiencia experiencia);

    public void deleteExperiencia(Long id);

    public List<Experiencia> listExperiencia();

    public Optional<Experiencia> findExperienciaById(Long id);

    public Boolean hasChanges(Long id, Experiencia experiencia) throws Exception;
}
