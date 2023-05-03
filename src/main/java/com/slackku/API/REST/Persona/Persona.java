package com.slackku.API.REST.Persona;

import java.io.Serializable;

import java.util.List;


import com.slackku.API.REST.Educacion.Educacion;
import com.slackku.API.REST.Experiencia.Experiencia;
import com.slackku.API.REST.Proyecto.Proyecto;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Persona implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @NotNull
    private String username;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String name;
    @NotNull
    private String ocupacion;
    @NotNull
    private String pais;
    @NotNull
    private String provincia;
    @NotNull
    private String sobreMi;
    @Basic
    private String profileImg;
    private String bannerImg;

    @OneToMany(mappedBy = "pers", cascade = CascadeType.ALL)
    private List<Educacion> educacion;

    @OneToMany(mappedBy = "pers", cascade = CascadeType.ALL)
    private List<Experiencia> experiencia;

    @OneToMany(mappedBy = "pers", cascade = CascadeType.ALL)
    private List<Proyecto> proyecto;

    public void setEducacion(List<Educacion> educacion) {
        this.educacion = educacion;
        for (Educacion educaciones : educacion) {
            educaciones.setPers(this);
        }
    }

    public void setExperiencia(List<Experiencia> experiencia) {
        this.experiencia = experiencia;
        for (Experiencia experiencias : experiencia) {
            experiencias.setPers(this);
        }
    }

    public void setProyecto(List<Proyecto> proyecto) {
        this.proyecto = proyecto;
        for (Proyecto proyectos : proyecto) {
            proyectos.setPers(this);
        }
    }
}
