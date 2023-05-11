package com.slackku.API.REST.Persona;

import java.util.List;

import com.slackku.API.REST.Educacion.Educacion;
import com.slackku.API.REST.Experiencia.Experiencia;
import com.slackku.API.REST.Proyecto.Proyecto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonaDTO {
    private Long id;
    private String nombre;
    private String email;
    private String profileImg;
    private String pais;
    private String provincia;
    private String ocupacion;
    private String sobreMi;
    private List<Educacion> educacion;
    private List<Experiencia> experiencias;
    private List<Proyecto> proyectos;
}
