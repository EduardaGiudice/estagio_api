package com.back.projeto.service;

import com.back.projeto.dto.UsuarioLoginCountDTO;
import com.back.projeto.dto.UsuarioLoginDTO;
import com.back.projeto.repository.LoginsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {

    @Autowired
    private LoginsRepository loginsRepo;

    public List<UsuarioLoginDTO> getLoginDetailsForRoleUser() {
        return loginsRepo.findLoginDetailsForRoleUser();
    }

        public List<UsuarioLoginCountDTO> getLoginCountsForRoleUser() {
        return loginsRepo.countLoginsByUsuarioWithRoleUser();
    }
}

