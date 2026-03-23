package com.portfolio.Carlos.Controller;

import com.portfolio.Carlos.repository.PerfilRepository;
import com.portfolio.Carlos.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    @GetMapping
    public String admin(Model model) {
        model.addAttribute("perfis", perfilRepository.findAll());
        model.addAttribute("projetos", projetoRepository.findAll());
        return "admin/admin";
    }
}