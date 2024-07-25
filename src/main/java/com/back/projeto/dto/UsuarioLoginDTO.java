package com.back.projeto.dto;

import java.time.LocalDateTime;

public class UsuarioLoginDTO {
    private Long id;
    private Long usuarioId;
    private LocalDateTime create_at;
    private String ra_matricula;
    private String nome;

    public UsuarioLoginDTO(Long id, Long usuarioId, LocalDateTime create_at, String ra_matricula, String nome) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.create_at = create_at;
        this.ra_matricula = ra_matricula;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public LocalDateTime getCreate_at() {
        return create_at;
    }

    public void setCreate_at(LocalDateTime create_at) {
        this.create_at = create_at;
    }

    public String getRa_Matricula() {
        return ra_matricula;
    }

    public void setRa_Matricula(String ra_matricula) {
        this.ra_matricula = ra_matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
