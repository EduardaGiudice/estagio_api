package com.back.projeto.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.back.projeto.dto.UsuarioLoginCountDTO;
import com.back.projeto.dto.UsuarioLoginDTO;
import com.back.projeto.entity.Logins;

import jakarta.transaction.Transactional;

public interface LoginsRepository extends JpaRepository<Logins, Long> {

    @Query("SELECT new com.back.projeto.dto.UsuarioLoginDTO(l.id, u.id, l.create_at, u.ra_matricula, u.nome) "
            + "FROM Logins l JOIN l.usuario u " +
            "WHERE u.tipo_usuario = 'ROLE_USER'")
    List<UsuarioLoginDTO> findLoginDetailsForRoleUser();

    @Query("SELECT new com.back.projeto.dto.UsuarioLoginCountDTO(u.ra_matricula,u.nome, COUNT(l)) "
            + "FROM Logins l JOIN l.usuario u " +
            "WHERE u.tipo_usuario = 'ROLE_USER' " + "GROUP BY u.ra_matricula, u.nome")
    List<UsuarioLoginCountDTO> countLoginsByUsuarioWithRoleUser();

    @Modifying
    @Transactional
    @Query("DELETE FROM Logins l WHERE l.create_at < :dateTime")
    void deleteByCreate_atBefore(@Param("dateTime") LocalDateTime dateTime);

}