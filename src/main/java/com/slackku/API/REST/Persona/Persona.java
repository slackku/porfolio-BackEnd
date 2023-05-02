package com.slackku.API.REST.Persona;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.slackku.API.REST.Educacion.Educacion;
import com.slackku.API.REST.Experiencia.Experiencia;

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
    private Set<Educacion> educacion = new HashSet<Educacion>();

    @OneToMany(mappedBy = "pers", cascade = CascadeType.ALL)
    private Set<Experiencia> experiencia = new HashSet<Experiencia>();

    public void setEducacion(Set<Educacion> educacion) {
        this.educacion = educacion;
        for (Educacion educaciones : educacion) {
            educaciones.setPers(this);
        }
    }

    public void setExperiencia(Set<Experiencia> experiencia) {
        this.experiencia = experiencia;
        for (Experiencia experiencias : experiencia) {
            experiencias.setPers(this);
        }
    }
}
