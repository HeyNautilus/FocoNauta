package com.projeto.senac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.senac.exceptions.CriptoExistsException;
import com.projeto.senac.exceptions.EmailExistsException;
import com.projeto.senac.model.Usuario;
import com.projeto.senac.repository.UsuarioRepository;
import com.projeto.senac.util.Util;

@Service
public class ServiceUsuario {
	@Autowired
	private UsuarioRepository usuarioRepository;

	public void salvarUsuario(Usuario user) throws Exception {
		try {
			if (usuarioRepository.findByEmail(user.getEmail()) != null) {
				throw new EmailExistsException("Existe um email cadastrado para :" + user.getEmail());
			}
			user.setSenha(Util.md5(user.getSenha()));
		} catch (Exception e) {
			throw new CriptoExistsException("Erro na criptografia da senha!");
		}
		usuarioRepository.save(user);
	}
	
	public Usuario loginUser(String login, String senha) throws ServiceExcn{
		Usuario userLogin= usuarioRepository.buscarLogin(login, senha);
		return userLogin;
	}

}
