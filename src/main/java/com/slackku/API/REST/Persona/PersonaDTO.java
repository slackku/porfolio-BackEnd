package com.slackku.API.REST.Persona;

import java.util.Set;

import com.slackku.API.REST.Educacion.Educacion;

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
    private String bannerImg;
    private String pais;
    private String provincia;
    private String ocupacion;
    private String sobreMi;
    private Set<Educacion> educacion;
}
