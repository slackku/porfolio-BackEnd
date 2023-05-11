package com.slackku.API.REST.Experiencia;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slackku.API.REST.Exception.ResourceNotFoundException;

@Service
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
    public Optional<Experiencia> findExperienciaById(Long id) {
        return experienciaRepository.findById(id);
    }

    @Override
    public Boolean hasChanges(Long id, Experiencia experiencia) throws Exception {
        Experiencia originalExperiencia = experienciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe experiencia de id: " + id));
        return (!originalExperiencia.getNombreEmp().equals(experiencia.getNombreEmp())) ||
                (!originalExperiencia.getPuesto().equals(experiencia.getPuesto())) ||
                (!originalExperiencia.getFecStart().equals(experiencia.getFecStart())) ||
                (!originalExperiencia.getFecEnd().equals(experiencia.getFecEnd()));
    }
}
