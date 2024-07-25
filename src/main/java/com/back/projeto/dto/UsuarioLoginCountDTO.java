package com.back.projeto.dto;

public class UsuarioLoginCountDTO {
    private String ra_matricula;
    private String nome;
    private Long loginCount;

    public UsuarioLoginCountDTO(String ra_matricula, String nome, Long loginCount) {
        this.ra_matricula = ra_matricula;
        this.nome = nome;
        this.loginCount = loginCount;
    }

    public String getRa_matricula() {
        return ra_matricula;
    }

    public void setRa_matricula(String ra_matricula) {
        this.ra_matricula = ra_matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Long loginCount) {
        this.loginCount = loginCount;
    }

}
