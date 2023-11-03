package com.projeto.foconauta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.foconauta.exceptions.CriptoExistsException;
import com.projeto.foconauta.exceptions.EmailExistsException;
import com.projeto.foconauta.exceptions.ServiceExc;
import com.projeto.foconauta.model.Usuario;
import com.projeto.foconauta.repository.UsuarioRepository;
import com.projeto.foconauta.util.Util;

@Service
public class ServiceUsuario {

	@Autowired
	UsuarioRepository usuarioRepository; 
	
	public String salvarUsuario(Usuario user) throws Exception{
		try {
			if(usuarioRepository.findByEmail(user.getEmail()) != null)
			{
				
				return ("Existe um usuário cadastrado para "+user.getEmail());
			}
			user.setSenha(Util.md5(user.getSenha()));
			
		} catch (Exception e) {
			throw new CriptoExistsException("Erro na criptografia da senha!");
		}
		usuarioRepository.save(user);
		return null;
	}

	public Usuario loginUser(String login, String senha) throws ServiceExc{
		Usuario userLogin= usuarioRepository.buscarLogin(login, senha);
		return userLogin;
}

	
	
	
	
	
}//fim classe
