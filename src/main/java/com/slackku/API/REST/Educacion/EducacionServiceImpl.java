package com.slackku.API.REST.Educacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Educacion findEducacion(Long id) {
        return educacionRepository.findById(id).get();
    }

    @Override
    public List<Educacion> listEducacions() {
        return educacionRepository.findAll();
    }

}
