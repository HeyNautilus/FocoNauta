package com.simpleform.controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.simpleform.model.UserModel;
import com.simpleform.service.UserService;
import com.simpleform.util.Util;

@Controller
public class UserController {

    // Injeção de dependência do serviço de usuário (UserService)
    private final UserService userService;

    // Construtor que recebe o UserService como parâmetro e o injeta no controlador
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Método que mapeia a URL "/register" para a página de registro
    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        // Adiciona um objeto UserModel vazio ao modelo para ser preenchido na página de registro
        model.addAttribute("registerRequest", new UserModel());
        // Retorna o nome da página de registro (normalmente associado a um template HTML)
        return "register_page";
    }

    // Método que lida com o envio de dados do formulário de registro
    @PostMapping("/register")
    public String register(@ModelAttribute UserModel userModel) throws NoSuchAlgorithmException, Exception {
        System.out.println("register request: " + userModel);
        // Chama o serviço para registrar um novo usuário com base nos dados do formulário
        UserModel registeredUser = userService.registerUser(userModel.getLogin(), Util.md5(userModel.getPassword()), userModel.getEmail());
        // Se o registro for bem-sucedido, redireciona para a página de login; caso contrário, exibe uma página de erro
        return registeredUser == null ? "error_page" : "redirect:/login";
    }

    // Método que mapeia a URL "/login" para a página de login
    @GetMapping("/login")
    public String getLoginPage(Model model) {
        // Adiciona um objeto UserModel vazio ao modelo para ser preenchido na página de login
        model.addAttribute("loginRequest", new UserModel());
        // Retorna o nome da página de login (normalmente associado a um template HTML)
        return "login_page";
    }

    // Método que lida com o envio de dados do formulário de login
    @PostMapping("/login")
    public String Login(@ModelAttribute UserModel userModel, Model model) {
        System.out.println("login request: " + userModel);
        // Autentica o usuário com base nos dados do formulário
        UserModel authenticated = userService.authenticate(userModel.getEmail(), userModel.getPassword());
        if (authenticated != null) {
            model.addAttribute("userLogin", authenticated.getLogin());
            return "personal_page"; // Redireciona para a página pessoal se a autenticação for bem-sucedida
        } else {
            return "error_page"; // Exibe uma página de erro se a autenticação falhar
        }
    }
}
