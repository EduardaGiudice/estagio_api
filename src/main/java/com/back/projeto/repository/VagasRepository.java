package com.back.projeto.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.back.projeto.entity.Vagas;

import jakarta.transaction.Transactional;

public interface VagasRepository extends JpaRepository <Vagas, Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Vagas v WHERE v.create_at < :dateTime")
    void deleteByCreate_atBefore(@Param("dateTime") LocalDateTime dateTime);

    @Query("SELECT v FROM Vagas v ORDER BY v.create_at DESC")
    List<Vagas> findAllOrderByCreate_atDesc();

    List<Vagas> findByUsuarioId(Long usuarioId);

    @Query("SELECT v FROM Vagas v WHERE v.usuario.tipo_usuario = :tipoUsuario")
    List<Vagas> findByUsuarioTipoUsuario(@Param("tipoUsuario") String tipoUsuario);

    
    @Query("SELECT v FROM Vagas v WHERE LOWER(v.nome_vaga) LIKE LOWER(CONCAT('%', :nomeVaga, '%')) AND v.usuario IS NOT NULL ORDER BY v.nome_vaga ASC")
    List<Vagas> findByNomeVagaContainingIgnoreCaseAndUsuarioIsNotNullOrderByNomeVagaAsc(@Param("nomeVaga") String nomeVaga);

    @Query("SELECT v FROM Vagas v WHERE "
    + "(:nomeVaga IS NULL OR LOWER(v.nome_vaga) LIKE LOWER(CONCAT('%', :nomeVaga, '%'))) AND "
    + "(:localizacao IS NULL OR LOWER(v.localizacao) LIKE LOWER(CONCAT('%', :localizacao, '%'))) AND "
    + "(:tipoVaga IS NULL OR LOWER(v.tipo_vaga) LIKE LOWER(CONCAT('%', :tipoVaga, '%'))) AND "
    + "(:area IS NULL OR LOWER(v.area) LIKE LOWER(CONCAT('%', :area, '%'))) AND "
    + "(:empresa IS NULL OR LOWER(v.empresa) LIKE LOWER(CONCAT('%', :empresa, '%'))) AND "
    + "(:descricao IS NULL OR LOWER(v.descricao) LIKE LOWER(CONCAT('%', :descricao, '%'))) AND "
    + "(:link IS NULL OR LOWER(v.link) LIKE LOWER(CONCAT('%', :link, '%'))) AND "
    + "(:usuarioId IS NULL OR v.usuario.id = :usuarioId)")
List<Vagas> findByFilters(
    @Param("nomeVaga") String nomeVaga,
    @Param("localizacao") String localizacao,
    @Param("tipoVaga") String tipoVaga,
    @Param("area") String area,
    @Param("empresa") String empresa,
    @Param("descricao") String descricao,
    @Param("link") String link,
    @Param("usuarioId") Long usuarioId
);

@Query("SELECT DISTINCT v.area FROM Vagas v")
    List<String> findAllAreas();
}
