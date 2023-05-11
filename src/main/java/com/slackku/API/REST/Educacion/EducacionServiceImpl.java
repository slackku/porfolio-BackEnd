package com.slackku.API.REST.Educacion;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slackku.API.REST.Exception.ResourceNotFoundException;

@Service
public class EducacionServiceImpl implements EducacionService {

    @Autowired
    private EducacionRepository educacionRepository;

    @Override
    public Educacion createEducacion(Educacion educacion) {
        return educacionRepository.save(educacion);
    }

    @Override
    public void deleteEducacion(Long id) {
        educacionRepository.deleteById(id);
    }

    @Override
    public Optional<Educacion> findEducacionById(Long id) {
        return educacionRepository.findById(id);
    }

    @Override
    public List<Educacion> listEducacions() {
        return educacionRepository.findAll();
    }

    @Override
    public Boolean hasChanges(Long id, Educacion educacion) throws Exception {
        Educacion originalEducacion = educacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe educacion de id: " + id));
        return (!originalEducacion.getCarrera().equals(educacion.getCarrera())) ||
                (!originalEducacion.getNombreInst().equals(educacion.getNombreInst())) ||
                (!originalEducacion.getFecStart().equals(educacion.getFecStart())) ||
                (!originalEducacion.getFecEnd().equals(educacion.getFecEnd()));
    }

}
