package com.projeto.foconauta.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class Util {
	
	public static String md5(String senha) throws NoSuchAlgorithmException {
		MessageDigest messagedig = MessageDigest.getInstance("MD5");
		BigInteger hash = new BigInteger(1, messagedig.digest(senha.getBytes()));
		return hash.toString(16);
	}
	public static String generateToken() {
		// Gere um UUID aleatório
		UUID uuid = UUID.randomUUID();
		// Converta o UUID em uma string removendo os hifens e convertendo para letras minúsculas
		String token = uuid.toString().replaceAll("-", "").toLowerCase();
		return token;
}


}
