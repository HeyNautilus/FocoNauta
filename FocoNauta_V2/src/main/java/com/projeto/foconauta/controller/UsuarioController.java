package com.projeto.foconauta.controller;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projeto.foconauta.exceptions.ServiceExc;
import com.projeto.foconauta.model.Usuario;
import com.projeto.foconauta.repository.UsuarioRepository;
import com.projeto.foconauta.service.ServiceEmail;
import com.projeto.foconauta.service.ServiceUsuario;
import com.projeto.foconauta.util.Util;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ServiceUsuario serviceUsuario;
	@Autowired
	private ServiceEmail serviceEmail;

	@GetMapping("/")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("usuario", new Usuario());
		mv.setViewName("Login/login");
		return mv;
	}

	@GetMapping("/home")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Login/index");
		return mv;
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
		if (!user.getSenha().equals(user.getSenha2())) {

		mv.addObject("msg", "Senhas não conferem");
		mv.addObject("usuario", new Usuario());
		mv.setViewName("Login/login");
		return mv;
		}
		String out = serviceUsuario.salvarUsuario(user);
		if (out!= null) {
			mv.addObject("msg", out);
			mv.addObject("usuario", new Usuario());
			mv.setViewName("Login/login");
		} else {
			mv.setViewName("redirect:/");
		}
		return mv;
	}

	@PostMapping("/login")
	public ModelAndView login(@Valid Usuario usuario, BindingResult br, HttpSession session)
			throws NoSuchAlgorithmException, ServiceExc {
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

	@GetMapping("/atualizar")
	public ModelAndView atualizar(Usuario user) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("usuario", user);
		mv.setViewName("Login/atualizar");
		return mv;
	}

	@PostMapping("/atualizarUsuario")
	public ModelAndView atualizarUser(Usuario user) throws NoSuchAlgorithmException {
		ModelAndView mv = new ModelAndView();
		Usuario aux = usuarioRepository.findByEmail(user.getEmail());
		if (aux == null || !user.getToken().equals(aux.getToken())) {
			mv.addObject("msg", "Token não encontrado!");
			mv.addObject("usuario", user);
			mv.setViewName("Login/atualizar");
		} else {
			aux.setToken("");// garantir que o token não seja mais usado
			aux.setSenha(Util.md5(user.getSenha()));
			usuarioRepository.save(aux);
			mv.addObject("usuario", new Usuario());
			mv.setViewName("Login/login");

		}

		return mv;
	}

	@GetMapping("/recuperarSenha")
	public ModelAndView recuperarSenha() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("usuario", new Usuario());
		mv.setViewName("Login/recuperar");
		return mv;
	}

	@GetMapping("/atualizarSenha")
	public ModelAndView DefinirSenha(Usuario user) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("usuario", user);
		mv.setViewName("Login/atualizar");
		return mv;
	}

	@PostMapping("/recuperarSenha")
	public ModelAndView recuperarSenha(Usuario user) throws Exception {
		ModelAndView mv = new ModelAndView();
		Usuario aux = usuarioRepository.findByEmail(user.getEmail());
		if (aux == null) {
			mv.addObject("msg", "Email Não encontrado!");
			mv.setViewName("Login/recuperar");
		} else {
			aux.setToken(Util.generateToken());
			usuarioRepository.save(aux);
			String corpo = "Use o seguinte token para redefinir a senha: " + aux.getToken();
			aux.setToken("");
			mv.addObject("usuario", aux);
			// serviceEmail.sendEmail("senaclpoo@gmail.com", aux.getEmail(), "Recuperação de
			// Senha", corpo);
			mv.setViewName("Login/atualizando");
			// return DefinirSenha(aux);
		}
		return mv;
	}

}
