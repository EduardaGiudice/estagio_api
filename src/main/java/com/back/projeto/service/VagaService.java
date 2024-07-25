package com.back.projeto.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.back.projeto.entity.Usuario;
import com.back.projeto.entity.Vagas;
import com.back.projeto.repository.UsuarioRepository;
import com.back.projeto.repository.VagasRepository;

@Service
public class VagaService {

    @Autowired 
    private VagasRepository vagasRepo;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Vagas criarVaga(Vagas vaga) {
      
        if (vaga == null ||
                vaga.getLocal_dado() == null || vaga.getLocal_dado().isBlank() ||
                vaga.getNome_vaga() == null || vaga.getNome_vaga().isBlank() ||
                vaga.getLocalizacao() == null || vaga.getLocalizacao().isBlank() ||
                vaga.getTipo_vaga() == null || vaga.getTipo_vaga().isBlank() ||
                vaga.getArea() == null || vaga.getArea().isBlank() ||
                vaga.getEmpresa() == null || vaga.getEmpresa().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados inválidos");
        }

        if (vaga.getUsuario() != null) {
            Usuario usuario = usuarioRepository.findById(vaga.getUsuario().getId())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            vaga.setUsuario(usuario);
        }

        vaga.setCreate_at(LocalDateTime.now());
        vaga.setUpdate_at(LocalDateTime.now());
        return vagasRepo.save(vaga);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public List<Vagas> listarVagasCriadasPorAdmins() {
        return vagasRepo.findByUsuarioTipoUsuario("ROLE_ADMIN");
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public List<Vagas> buscarTodasVagas() {
        return vagasRepo.findAllOrderByCreate_atDesc();
    }


    public List<Vagas> buscarVagasPorNomeComUsuario(String nomeVaga) {
        return vagasRepo.findByNomeVagaContainingIgnoreCaseAndUsuarioIsNotNullOrderByNomeVagaAsc(nomeVaga);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public Vagas buscarVagaPorId(Long id) {
        return vagasRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vaga não encontrada"));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public Vagas atualizarVaga(Long id, Map<String, Object> atualizacoes) {
    Vagas vagaExistente = vagasRepo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vaga não encontrada"));

    if (atualizacoes.containsKey("local_dado")) {
        vagaExistente.setLocal_dado((String) atualizacoes.get("local_dado"));
    }
    if (atualizacoes.containsKey("nome_vaga")) {
        vagaExistente.setNome_vaga((String) atualizacoes.get("nome_vaga"));
    }
    if (atualizacoes.containsKey("localizacao")) {
        vagaExistente.setLocalizacao((String) atualizacoes.get("localizacao"));
    }
    if (atualizacoes.containsKey("tipo_vaga")) {
        vagaExistente.setTipo_vaga((String) atualizacoes.get("tipo_vaga"));
    }
    if (atualizacoes.containsKey("area")) {
        vagaExistente.setArea((String) atualizacoes.get("area"));
    }
    if (atualizacoes.containsKey("empresa")) {
        vagaExistente.setEmpresa((String) atualizacoes.get("empresa"));
    }
    if (atualizacoes.containsKey("descricao")) {
        vagaExistente.setDescricao((String) atualizacoes.get("descricao"));
    }
    if (atualizacoes.containsKey("link")) {
        vagaExistente.setLink((String) atualizacoes.get("link"));
    }
    
    vagaExistente.setUpdate_at(LocalDateTime.now());

    return vagasRepo.save(vagaExistente);
}


    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public void excluirVaga(Long id) {
        if (!vagasRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vaga não encontrada");
        }
        vagasRepo.deleteById(id);
    }

    public List<Vagas> searchVagas(String nomeVaga, String localizacao, String tipoVaga, String area, String empresa, String descricao, String link, Long usuarioId) {
        return vagasRepo.findByFilters(nomeVaga, localizacao, tipoVaga, area, empresa, descricao, link, usuarioId);
    }

    public List<String> listarTodasAsAreas() {
        return vagasRepo.findAllAreas();
    }
}
