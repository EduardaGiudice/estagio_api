package com.back.projeto.controller;

import com.back.projeto.dto.UsuarioLoginCountDTO;
import com.back.projeto.dto.UsuarioLoginDTO;
import com.back.projeto.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/logins")
public class LoginsController {

    @Autowired
    private LoginService loginService;

    @GetMapping
    public List<UsuarioLoginDTO> getLoginDetailsForRoleUser() {
        return loginService.getLoginDetailsForRoleUser();
    }

    @GetMapping("/counts")
    public List<UsuarioLoginCountDTO> getLoginCountsForRoleUser() {
        return loginService.getLoginCountsForRoleUser();
    }
}
