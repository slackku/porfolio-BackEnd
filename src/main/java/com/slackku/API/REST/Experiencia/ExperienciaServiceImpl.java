package com.slackku.API.REST.Experiencia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class ExperienciaServiceImpl implements ExperienciaService {

    @Autowired
    private ExperienciaRepository experienciaRepository;

    @Override
    public Experiencia createExperiencia(Experiencia experiencia) {
       return experienciaRepository.save(experiencia);
    }

    @Override
    public void deleteExperiencia(Long id) {
        experienciaRepository.deleteById(id);
    }

    @Override
    public List<Experiencia> listExperiencia() {
        return experienciaRepository.findAll();
    }

    @Override
    public Experiencia findExperienciaById(Long id) {
        return experienciaRepository.findById(id).get();
    }

}
