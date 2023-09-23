package com.projeto.senac.controller;

import java.security.NoSuchAlgorithmException;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projeto.senac.model.Usuario;
import com.projeto.senac.repository.UsuarioRepository;
import com.projeto.senac.service.ServiceExcn;
import com.projeto.senac.service.ServiceUsuario;
import com.projeto.senac.util.Util;

@SpringBootApplication
@ComponentScan(basePackages = "com.projeto.senac.service")

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ServiceUsuario serviceUsuario;
	
	

	@RequestMapping("/")
	public ModelAndView index() {
		return null;
	}

	@GetMapping("/login")
	public ModelAndView cadastro() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("usuario", new Usuario());
		mv.setViewName("Login/login");
		return mv;
	}

	@PostMapping("/salvarUsuario")
	public ModelAndView cadastro(Usuario user) throws Exception {
		ModelAndView mv = new ModelAndView();
		serviceUsuario.salvarUsuario(user);
		mv.setViewName("redirect:/login");
		return mv;
	}

	@PostMapping("/")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("usuario", new Usuario());
		mv.setViewName("Login/login");
		return mv;
	}

	@PostMapping("/login")
	public ModelAndView login(@Valid Usuario usuario, BindingResult br, HttpSession session)
			throws NoSuchAlgorithmException, ServiceExcn {
		ModelAndView mv = new ModelAndView();
		mv.addObject("usuario", new Usuario());

		if (br.hasErrors()) {
			mv.setViewName("Login/login");
		}
		
		Usuario userLogin = serviceUsuario.loginUser(usuario.getEmail(), Util.md5(usuario.getSenha()));
		if (userLogin == null) {
			mv.addObject("msg", "Usuário ou senha incorreta!");
			mv.setViewName("Login/login");
		} else {
			session.setAttribute("usuarioLogado", userLogin);
			return index();
		}

		return mv;

	}
}
